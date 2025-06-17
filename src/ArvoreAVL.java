    class Node {
        public boolean parent;
        int data;
        int height;
        Node left, right;

        Node(int data) {
        this.data = data;
        this.height = 1;
        }
    }

    public class ArvoreAVL {
        Node root;

        int height(Node n) {
            return (n == null) ? 0 : n.height;
        }

        int balanceFactor(Node n) {
            return (n == null) ? 0 : height(n.left) - height(n.right    );
        }

        Node rotacaoDireita(Node y) {
            Node x = y.left;
            Node T2 = x.right;

            x.right = y;
            y.left = T2;

            y.height = Math.max(height(y.left), height(y.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;

            return x;

        }
        Node rotacaoEsquerda(Node x) {
            Node y = x.right;
            Node T2 = y.left;

            y.left = x;
            x.right = T2;

            x.height = Math.max(height(x.left), height(x.right)) + 1;
            y.height = Math.max(height(y.right), height(y.right)) + 1;

            return y;
        }

        Node insert(Node node, int data) {
            if (node == null) return new Node(data);

            if (data < node.data) node.left = insert(node.left, data);

            else if (data > node.data) node.right = insert(node.right, data);

            else return node;

            node.height = 1 + Math.max(height(node.left), height(node.right));

            int fb = balanceFactor(node);

            if (fb > 1 && data < node.left.data)
                return rotacaoDireita(node);
            if (fb < -1 && data > node.right.data)
                return rotacaoEsquerda(node);
            if (fb > 1 && data > node.left.data) { // LR
                node.left = rotacaoEsquerda(node.left);
                return rotacaoDireita(node);
            }
            if (fb < -1 && data < node.right.data) { // RL
                node.right = rotacaoDireita(node.right);
                return rotacaoEsquerda(node);
            }

            return node;
        }

        void insert(int data) {
            root = insert(root, data);
        }

        void remove(int key) {
            root = remove(root, key);
        }

        void Representacao(Node node) {
            if (node != null) {
                Representacao(node.left);
                System.out.print(node.data + " ");
                Representacao(node.right);
            }
        }

        Node minValue(Node node) {
            Node atual = node;
            while (atual.left != null)
                atual = atual.left;
            return atual;
        }


        Node remove(Node node, int key) {
            if (node == null) return null;

            if (key < node.data)
                node.left = remove(node.left, key);
            else if (key > node.data)
                node.right = remove(node.right, key);
            else {
                if (node.left == null || node.right == null) {
                    Node temp = (node.left != null) ? node.left : node.right;
                    node = (temp == null) ? null : temp;
                } else {
                    Node successor = minValue(node.right);
                    node.data = successor.data;
                    node.right = remove(node.right, successor.data);
                }
            }

            if (node == null) return null;
            node.height = 1 + Math.max(height(node.left), height(node.right));

            int bf = balanceFactor(node);

            if (bf > 1 && balanceFactor(node.left) >= 0)
                return rotacaoDireita(node); // LL
            if (bf > 1 && balanceFactor(node.left) < 0) {
                node.left = rotacaoEsquerda(node.left); // LR
                return rotacaoDireita(node);
            }
            if (bf < -1 && balanceFactor(node.right) <= 0)
                return rotacaoEsquerda(node); // RR
            if (bf < -1 && balanceFactor(node.right) > 0) {
                node.right = rotacaoDireita(node.right); // RL
                return rotacaoEsquerda(node);
            }

            return node;
        }

    }


