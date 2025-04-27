import java.util.Iterator;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> {
    private class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;
    private int size;

    public static class Entry<K, V> {
        private final K key;
        private final V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public void put(K key, V value) {
        Node current = root;

        if (current == null) {
            root = new Node(key, value);
            size++;
            return;
        }

        Node parent = null;
        while (current != null) {
            parent = current;
            int cmp = key.compareTo(current.key);
            if (cmp > 0) {
                current = current.right;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current.value = value;
                return;
            }
        }

        Node newNode = new Node(key, value);
        int cmp = key.compareTo(parent.key);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
    }

    public V get(K key) {
        Node current = root;

        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp > 0) {
                current = current.right;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                return current.value;
            }
        }
        return null;
    }

    public void delete(K key) {
        Node current = root;
        Node parent = null;

        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp > 0) {
                parent = current;
                current = current.right;
            } else if (cmp < 0) {
                parent = current;
                current = current.left;
            } else {
                if (current.right == null && current.left == null) {
                    if (parent == null) {
                        root = null;
                    } else {
                        if (parent.left == current) {
                            parent.left = null;
                        } else {
                            parent.right = null;
                        }
                    }
                } else if (current.right == null || current.left == null) {
                    Node child = (current.left != null) ? current.left : current.right;
                    if (parent == null) {
                        root = child;
                    } else {
                        if (parent.left == current) {
                            parent.left = child;
                        } else {
                            parent.right = child;
                        }
                    }
                } else {
                    Node replacerParent = current;
                    Node replacer = current.right;

                    while (replacer.left != null) {
                        replacerParent = replacer;
                        replacer = replacer.left;
                    }

                    current.key = replacer.key;
                    current.value = replacer.value;

                    if (replacerParent.left == replacer) {
                        replacerParent.left = replacer.right;
                    } else {
                        replacerParent.right = replacer.right;
                    }
                }
                break;
            }
        }
        size--;
    }

    public int size() {
        return size;
    }

    public Iterable<Entry<K, V>> iterator() {
        return new Iterable<Entry<K, V>>() {
            @Override
            public Iterator<Entry<K, V>> iterator() {
                return new Iterator<>() {
                    private final Stack<Node> stack = new Stack<>();
                    private Node current = root;

                    {
                        while (current != null) {
                            stack.push(current);
                            current = current.left;
                        }
                    }

                    @Override
                    public boolean hasNext() {
                        return !stack.isEmpty();
                    }

                    @Override
                    public Entry<K, V> next() {
                        Node node = stack.pop();
                        Entry<K, V> entry = new Entry<>(node.key, node.value);
                        Node right = node.right;
                        while (right != null) {
                            stack.push(right);
                            right = right.left;
                        }
                        return entry;
                    }
                };
            }
        };
    }
}