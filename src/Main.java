import org.w3c.dom.Node;

public class Main {
    public static void main(String[] args) {

        class Node{
            int valor;
            Node left;
            Node right;

            public Node(int valor){
                this.valor = valor;
            }

        }
        class ArvoreBinaria{
            Node root;

            int contarNos(Node node) {
                if (node == null)
                    return 0;
                return 1 + contarNos(node.left) + contarNos(node.right);
            }


            //METODO PARA CONTAR OS NOS
            int getCount() {
                return contarNos(root);
            }

            //METODO PARA PRE-ORDEM
            void preOrdem(Node node){

                if (node == null)
                    return;

                System.out.print(node.valor + " ");

                preOrdem(node.left);

                preOrdem(node.right);
            }

            //METODO DE CONTAR IN-ORDER
            void emOrdem(Node node) {
                if (node == null)
                    return;
                emOrdem(node.left);
                System.out.print(node.valor + " ");
                emOrdem(node.right);
            }
        }

    }
}