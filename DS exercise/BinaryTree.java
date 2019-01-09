/*class Node<E> {
 E value;
 Node<E> left, right;
 Node<E> parent;
}
class Tree<E> {
 E root;
}*/

public class Node {
    private int data;
    private Node left;
    private Node right;
 
    public Node(int data) {
        this.setData(data);
    }
 
    public int getData() {return data;}
    public void setData(int data) {this.data = data;}
    public Node getLeft() {return left;}
    public void setLeft(Node left) {this.left = left;}
    public Node getRight() {return right;}
    public void setRight(Node right) {this.right = right;}
}

public class BinaryTree{
 
    public static void main(String[] args) {
        // 노드 생성
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        
        // 트리에 노드 추가
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
    }
}
