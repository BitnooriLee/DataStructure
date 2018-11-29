// Note: you do not need to read this code!

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class TestDijkstra {
    
    static int nopdone = 0;
    static int lastnopdone = -1;
    static StringBuilder log = new StringBuilder();

    static void showException(Exception e, StringBuilder log) {
        System.out.println("The operation on the last line of the following code causes an exception:\n");
        System.out.print(log.toString());
        System.out.println("\nThe exception is: " + e.toString());
        System.exit(1);
    }

    static <E> String show(E o) {
        if (o == null) {
            return "null";
        } else {
            return o.toString();
        }
    }
    
    static boolean validPath(HashMap<UnorderedPair<String>, Integer> edges, String start, String dest, Graph.Path path) {
        int totalDist = 0;
        Iterator<String> iter = path.vertices.iterator();
        if (!iter.hasNext()) return false;
        String curVert = iter.next();
        if (!curVert.equals(start)) return false;
        while (iter.hasNext()) {
            String nextVert = iter.next();
            if (!edges.containsKey(new UnorderedPair<>(curVert, nextVert))) return false;
            totalDist += edges.get(new UnorderedPair<>(curVert, nextVert));
            curVert = nextVert;
        }
        if (!curVert.equals(dest)) return false;
        return totalDist == path.totalDist;
    }
    
    static void test(Scanner in) {
        log = new StringBuilder();

        log.append("Graph g = new Graph();\n");
        Graph g = new Graph();
        HashMap<UnorderedPair<String>, Integer> edges = new HashMap<>();

        for (;;) {
            String s = in.next();
            if (s.equals("end")) break;
            
            if (s.equals("addVertex")) {
                String l = in.next();
                log.append("g.addVertex(\"" + l + "\");\n");
                try {
                    g.addVertex(l);
                } catch (Exception e) {
                    showException(e, log);
                }
            } else if (s.equals("addEdge")) {
                String from = in.next();
                String to = in.next();
                int dist = in.nextInt();
                if (dist < 0) {
                    System.out.println("Dijkstras algorithm does not work on graphs with negative weights(distances).");
                    System.exit(1);
                }
                if (edges.containsKey(new UnorderedPair<>(from, to))) {
                    System.out.println("This program does not handle testing multigraphs.");
                    System.exit(1);
                }
                edges.put(new UnorderedPair<>(from, to), dist);
                log.append("g.addEdge(\"" + from + "\", \"" + to + "\", " + dist + ");\n");
                try {
                    g.addEdge(from, to, dist);
                } catch (Exception e) {
                    showException(e, log);
                }
            } else if (s.equals("shortestPath")) {
                String start = in.next();
                String dest = in.next();

                Graph.Path expres = null;
                String expresStr = in.next();
                if (!expresStr.equals("null")) {
                    int totalDist = Integer.parseInt(expresStr);
                    int nvert = in.nextInt();
                    ArrayList<String> vertices = new ArrayList<>();
                    for (; nvert > 0; nvert--) {
                        vertices.add(in.next());
                    }
                    expres = new Graph.Path(totalDist, vertices);
                }

                log.append("g.shortestPath(\"" + start + "\", \"" + dest + "\");");
                Graph.Path res = null;
                try {
                    res = g.shortestPath(start, dest);
                } catch (Exception e) {
                    showException(e, log);
                }
                log.append("  // result: " + show(res) + "\n");
                if (res == null) {
                    if (expres != null) {
                        System.out.println("The result of the last line of the following code should not be null.\n");
                        System.out.print(log.toString());
                        System.out.println("It should be a path with total distance " + expres.totalDist + ",");
                        System.out.println("e.g. the path " + expres.vertices.toString());
                        System.exit(1);
                    }
                } else {
                    if (!validPath(edges, start, dest, res)) {
                        System.out.println("The result of the last line of the following code is not a valid path.\n");
                        System.out.print(log.toString());
                        System.out.println("Either there is no such path in the graph from the start to the destination vertex or the total distance is wrong.");
                        System.exit(1);
                    }
                    if (expres == null) {
                        System.out.println("The result of the last line of the following code should be null.\n");
                        System.out.print(log.toString());
                        System.exit(1);
                    } else {
                        if (res.totalDist != expres.totalDist) {
                            System.out.println("The result of the last line of the following code should be different.\n");
                            System.out.print(log.toString());
                            System.out.println("It should be a path with total distance " + expres.totalDist + ",");
                            System.out.println("e.g. the path " + expres.vertices.toString());
                            System.exit(1);
                        }
                    }
                }
                nopdone++;
            } else {
                System.err.println("invalid command: " + s);
                System.exit(1);
            }
        }
    }

    private static class Monitor implements Runnable {
        @Override
        public void run() {
            for (;;) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
                if (nopdone == lastnopdone) {
                    System.out.println("No new operation completed during the last second.");
                    System.out.println("Your program seems to loop (or be very slow) at the operation on the last line of the following code:\n");
                    System.out.print(log.toString());
                    System.exit(1);
                }
                lastnopdone = nopdone;
                System.out.println(nopdone + " calls to shortestPath executed. (No bugs found so far.)");
            }
        }
        
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("You must give as an argument the name of the test cases file to use.");
            System.exit(1);
        }
        new Thread(new Monitor()).start();

        Scanner in = null;
        try {
            in = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            System.out.println("The file " + args[0] + " is not found.");
        }

        for (;;) {
            test(in);
            if (!in.hasNext()) break;
        }

        System.out.println("Ran " + nopdone + " calls to shortestPath. No bugs found.");
        System.exit(0);
    }    
}

class UnorderedPair<T extends Comparable<? super T>> {
    final T smaller, bigger;

    UnorderedPair(T a, T b) {
        if (a.compareTo(b) <= 0) {
            this.smaller = a;
            this.bigger = b;
        } else {
            this.bigger = a;
            this.smaller = b;
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof UnorderedPair)) return false;
        UnorderedPair<?> p = (UnorderedPair<?>)o;
        return smaller.equals(p.smaller) && bigger.equals(p.bigger);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(smaller, bigger);
    }
}