enum Color {
    RED, BLACK
}

class NodeRB<T extends Comparable<T>> {
    T data;
    NodeRB<T> left, right, parent;
    Color color;

    NodeRB(T data) {
        this.data = data;
        this.color = Color.RED;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}

public class ArvoreRB<T extends Comparable<T>> {
    private NodeRB<T> root;
    private final NodeRB<T> TNULL;

    public ArvoreRB() {
        TNULL = new NodeRB<>(null);
        TNULL.color = Color.BLACK;
        TNULL.left = null;
        TNULL.right = null;
        root = TNULL;
    }

    private void rotacaoEsquerdaRB(NodeRB<T> x) {
        NodeRB<T> y = x.right;
        x.right = y.left;
        if (y.left != TNULL) y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;
        y.left = x;
        x.parent = y;
    }

    private void rotacaoDireitaRB(NodeRB<T> x) {
        NodeRB<T> y = x.left;
        x.left = y.right;
        if (y.right != TNULL) y.right.parent = x;
        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.right) x.parent.right = y;
        else x.parent.left = y;
        y.right = x;
        x.parent = y;
    }

    public void insertRB(T key) {
        NodeRB<T> node = new NodeRB<>(key);
        node.parent = null;
        node.left = TNULL;
        node.right = TNULL;
        node.color = Color.RED;

        NodeRB<T> y = null;
        NodeRB<T> x = this.root;

        while (x != TNULL) {
            y = x;
            if (node.data.compareTo(x.data) < 0) x = x.left;
            else x = x.right;
        }

        node.parent = y;
        if (y == null) root = node;
        else if (node.data.compareTo(y.data) < 0) y.left = node;
        else y.right = node;

        if (node.parent == null) {
            node.color = Color.BLACK;
            return;
        }

        if (node.parent.parent == null) return;

        fixInsert(node);
    }

    private void fixInsert(NodeRB<T> k) {
        NodeRB<T> u;
        while (k.parent.color == Color.RED) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == Color.RED) {
                    u.color = Color.BLACK;
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rotacaoDireitaRB(k);
                    }
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    rotacaoEsquerdaRB(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;
                if (u.color == Color.RED) {
                    u.color = Color.BLACK;
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        rotacaoEsquerdaRB(k);
                    }
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    rotacaoDireitaRB(k.parent.parent);
                }
            }
            if (k == root) break;
        }
        root.color = Color.BLACK;
    }

    private void transplant(NodeRB<T> u, NodeRB<T> v) {
        if (u.parent == null) root = v;
        else if (u == u.parent.left) u.parent.left = v;
        else u.parent.right = v;
        v.parent = u.parent;
    }

    private NodeRB<T> minimum(NodeRB<T> node) {
        while (node.left != TNULL) node = node.left;
        return node;
    }

    public void delete(T key) {
        NodeRB<T> z = searchTree(root, key);
        if (z == TNULL) return;

        NodeRB<T> y = z;
        Color yOriginalColor = y.color;
        NodeRB<T> x;

        if (z.left == TNULL) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == TNULL) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) x.parent = y;
            else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        if (yOriginalColor == Color.BLACK) deleteFix(x);
    }

    private void deleteFix(NodeRB<T> x) {
        NodeRB<T> w;
        while (x != root && x.color == Color.BLACK) {
            if (x == x.parent.left) {
                w = x.parent.right;
                if (w.color == Color.RED) {
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    rotacaoEsquerdaRB(x.parent);
                    w = x.parent.right;
                }
                if (w.left.color == Color.BLACK && w.right.color == Color.BLACK) {
                    w.color = Color.RED;
                    x = x.parent;
                } else {
                    if (w.right.color == Color.BLACK) {
                        w.left.color = Color.BLACK;
                        w.color = Color.RED;
                        rotacaoDireitaRB(w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    w.right.color = Color.BLACK;
                    rotacaoEsquerdaRB(x.parent);
                    x = root;
                }
            } else {
                w = x.parent.left;
                if (w.color == Color.RED) {
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    rotacaoDireitaRB(x.parent);
                    w = x.parent.left;
                }
                if (w.right.color == Color.BLACK && w.left.color == Color.BLACK) {
                    w.color = Color.RED;
                    x = x.parent;
                } else {
                    if (w.left.color == Color.BLACK) {
                        w.right.color = Color.BLACK;
                        w.color = Color.RED;
                        rotacaoEsquerdaRB(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    w.left.color = Color.BLACK;
                    rotacaoDireitaRB(x.parent);
                    x = root;
                }
            }
        }
        x.color = Color.BLACK;
    }

    private NodeRB<T> searchTree(NodeRB<T> node, T key) {
        if (node == TNULL || key.compareTo(node.data) == 0) return node;
        if (key.compareTo(node.data) < 0) return searchTree(node.left, key);
        return searchTree(node.right, key);
    }

    public void inorder() {
        inorderHelper(this.root);
        System.out.println();
    }

    private void inorderHelper(NodeRB<T> node) {
        if (node != TNULL) {
            inorderHelper(node.left);
            String cor = (node.color == Color.RED) ? "R" : "B";
            System.out.print(node.data + cor + " ");
            inorderHelper(node.right);
        }
    }
}
