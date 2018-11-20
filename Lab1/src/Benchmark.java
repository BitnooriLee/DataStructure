
import java.lang.reflect.InvocationTargetException;

public class Benchmark {
    static double runBenchmark(Class<IntSet> intSetClass, int size, int minSeconds) {
        IntSet set = null;
        try {
            set = intSetClass.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            System.out.println("Could not instantiate specified class, " + intSetClass.getName() + ".");
            System.out.println("Exception: " + e.toString());
            System.exit(1);
        }

        long starttime = System.currentTimeMillis();
        long nowtime = starttime;
        long n = 1;

        int i;
        for (i = 0; i < size && nowtime - starttime < 5000; ) {  // a five seconds timeout
            for (int j = 0; j < n && i < size; j++, i++) {
                set.add(1 + i * 2);
            }
            nowtime = System.currentTimeMillis();
            n *= 2;
        }
        if (i < size) {  // Constructing set took a lot of time. Try adding the opposite way.
            try {
                set = intSetClass.getDeclaredConstructor().newInstance();
            } catch (ReflectiveOperationException e) {
                System.out.println("Could not instantiate specified class, " + intSetClass.getName() + ".");
                System.out.println("Exception: " + e.toString());
                System.exit(1);
            }
            for (i = size - 1; i >= 0; i--) {
                set.add(1 + i * 2);
            }
        }

        long nop = 0;
        starttime = System.currentTimeMillis();
        nowtime = starttime;
        n = 1;
        while (nowtime - starttime < 1000 * minSeconds) {
            int j = 0;
            for (long k = 0; k < n; k++) {
                set.contains(j);
                j++;
                if (j > size * 2) j = 0;
                nop++;
            }
            nowtime = System.currentTimeMillis();
            n *= 2;
        }
        return (nowtime - starttime) / (1000.0 * nop);
    }

    private static class Timeout implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                return;
            }
            System.out.println("A minute has passed. Terminating.");
            System.exit(0);
        }

    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        if (args.length == 0 || args.length > 1) {
            System.out.println("The program needs one argument, the name of the class implementing IntSet that should be banchmarked.");
            System.exit(1);
        }

        Class<IntSet> intSetClass = null;
        try {
            intSetClass = (Class<IntSet>) Class.forName(args[0]);
        } catch (ClassNotFoundException e) {
            System.out.println("The specified class, " + args[0] + ", cannot be found.");
            System.exit(1);
        }

        new Thread(new Timeout()).start();

        System.out.println("Average execution time of method contains for different set sizes\n");
        System.out.println("set size  execution time (nanoseconds)");
        for (int size = 10; size <= 10000000; size *= 10) {
            double time = runBenchmark(intSetClass, size, 2);
            System.out.format("%8d  %14.1f\n", size, time * 1e9);
        }
        System.exit(0);
    }
}