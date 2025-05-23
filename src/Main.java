import org.w3c.dom.Node;

import java.util.LinkedList;
import java.util.Queue;

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

            //METODO DE CONTAR EM-ORDER
            void emOrdem(Node node) {
                if (node == null)
                    return;
                emOrdem(node.left);
                System.out.print(node.valor + " ");
                emOrdem(node.right);
            }

            //METODO POR NIVEL
            public void porNivel(Node root) {
                if (root == null) return;

                Queue<Node> fila = new LinkedList<>();
                fila.add(root);

                while (!fila.isEmpty()) {
                    Node atual = fila.poll();
                    System.out.print(atual.valor + " ");

                    if (atual.left != null) {
                        fila.add(atual.left);
                    }
                    if (atual.right != null) {
                        fila.add(atual.right);
                    }
                }
            }

        }

    }
}