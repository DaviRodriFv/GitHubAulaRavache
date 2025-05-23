import org.w3c.dom.Node;

public class Main {
    public static void main(String[] args) {

        class Node{
            int num;
            Node left;
            Node right;

            public Node(int num){
                this.num = num;
            }

        }
        class ArvoreBinaria{
            Node root;

            int contarNos(Node node) {
                if (node == null)
                    return 0;
                return 1 + contarNos(node.left) + contarNos(node.right);
            }

            int getCount() {
                return contarNos(root);
            }
        }

    }
}