import org.w3c.dom.Node;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

            //METODO POS ORDEM
            void posOrdem(Node node) {
                if (node == null)
                    return;

                posOrdem(node.left);
                posOrdem(node.right);
                System.out.print(node.valor + " ");
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


            //METODO PRE-ORDEM (NAO RECURSIVO)
            void preOrdemNaoRecursivo(Node root) {
                if (root == null)
                    return;

                Stack<Node> pilha = new Stack<>();
                pilha.push(root);

                while (!pilha.isEmpty()) {
                    Node atual = pilha.pop();
                    System.out.print(atual.valor + " ");

                    if (atual.right != null) {
                        pilha.push(atual.right);
                    }
                    if (atual.left != null) {
                        pilha.push(atual.left);
                    }
                }
            }

            //METODO EM-ORDEM (NAO RECURSIVO)
            void emOrdemNaoRecursivo(Node root) {
                Stack<Node> stack = new Stack<>();
                Node atual = root;

                while (atual != null || !stack.isEmpty()) {
                    while (atual != null) {
                        stack.push(atual);
                        atual = atual.left;
                    }

                    atual = stack.pop();
                    System.out.print(atual.valor + " ");

                    atual = atual.right;
                }
            }

            //METODO POS-ORDEM (NAO RECURSIVO)
            void posOrdemNaoRecursivo(Node root) {
                if (root == null)
                    return;

                Stack<Node> stack1 = new Stack<>();
                Stack<Node> stack2 = new Stack<>();

                stack1.push(root);

                while (!stack1.isEmpty()) {
                    Node node = stack1.pop();
                    stack2.push(node);

                    if (node.left != null)
                        stack1.push(node.left);
                    if (node.right != null)
                        stack1.push(node.right);
                }

                while (!stack2.isEmpty()) {
                    System.out.print(stack2.pop().valor + " ");
                }
            }

            // CONTAR OS NOS (NAO RECURSIVO)
            int contaNosNaoRecursivo(Node root) {
                if (root == null)
                    return 0;

                Stack<Node> stack = new Stack<>();
                stack.push(root);
                int count = 0;

                while (!stack.isEmpty()) {
                    Node node = stack.pop();
                    count++;

                    if (node.right != null)
                        stack.push(node.right);
                    if (node.left != null)
                        stack.push(node.left);
                }

                return count;
            }
            //METODO PARA CONTAR NOS FOLHAS
            static int contarNosFolhas(Node root) {

                if (root == null) {
                    return 0;
                }
                if (root.left == null && root.right == null) {
                    return 1;
                }
                return contarNosFolhas(root.left)
                        + contarNosFolhas(root.right);
            }

            //METODO CONTAR NOS (COM FILA)
            int contaNosComFila(Node root) {
                if (root == null)
                    return 0;

                Queue<Node> fila2 = new LinkedList<>();
                fila2.add(root);
                int count2 = 0;

                while (!fila2.isEmpty()) {
                    Node atual = fila2.poll();
                    count2++;

                    if (atual.left != null) {
                        fila2.add(atual.left);
                    }
                    if (atual.right != null) {
                        fila2.add(atual.right);
                    }
                }
                return count2;
            }

      }
        ArvoreBinaria arvore = new ArvoreBinaria();

        arvore.root = new Node(10);
        arvore.root.left = new Node(5);
        arvore.root.right = new Node(15);
        arvore.root.left.left = new Node(3);
        arvore.root.left.right = new Node(7);
        arvore.root.right.right = new Node(20);

        System.out.println("Pré-Ordem Recursiva:");
        arvore.preOrdem(arvore.root);
        System.out.println("\nPré-Ordem Não Recursiva:");
        arvore.preOrdemNaoRecursivo(arvore.root);

        System.out.println("\n\nEm-Ordem Recursiva:");
        arvore.emOrdem(arvore.root);
        System.out.println("\nEm-Ordem Não Recursiva:");
        arvore.emOrdemNaoRecursivo(arvore.root);

        System.out.println("\n\nPós-Ordem Recursiva:");
        arvore.posOrdem(arvore.root);
        System.out.println("\nPós-Ordem Não Recursiva:");
        arvore.posOrdemNaoRecursivo(arvore.root);

        System.out.println("\n\nPor Nível:");
        arvore.porNivel(arvore.root);

        System.out.println("\n\nTotal de nós (recursivo): " + arvore.getCount());
        System.out.println("Total de nós (não recursivo): " + arvore.contaNosNaoRecursivo(arvore.root));
        System.out.println("Total de nós (com Fila): " + arvore.contaNosComFila(arvore.root));
        System.out.println("Total de folhas: " + ArvoreBinaria.contarNosFolhas(arvore.root));

        //ARVORE AVL
        ArvoreAVL avrAVL = new ArvoreAVL();

        int[] valores = { 10, 20, 30, 40, 50, 25 };
        for (int v : valores) avrAVL.insert(v);

        System.out.print("In-order antes da remoção: ");
        avrAVL.Representacao(avrAVL.root);

        avrAVL.remove(30);

        System.out.print("\nIn-order após remover 30: ");
        avrAVL.Representacao(avrAVL.root);

        ArvoreRB tree = new ArvoreRB();

        int[] keys = {10, 20, 30, 40, 50, 25 };
        for (int key : keys) {
            tree.insertRB(key);
        }

        System.out.println("Arvore após inserçoes (in-order");
        tree.inorder();

        tree.delete(15);
        tree.delete(10);

        System.out.println("Arvore após remocoes (in-order");
        tree.inorder();

    }

}
