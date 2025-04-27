public class MyHashTable<K, V> {
    public class HashNode<K, V> {
        private final K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }

        public HashNode<K, V> getNext() {
            return next;
        }
    }

    private HashNode<K, V>[] chainArray;
    private int size;

    public MyHashTable() {
        this(201);
    }

    public MyHashTable(int M) {
        chainArray = new HashNode[M];
    }

    public int hash(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % chainArray.length;
    }

    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];

        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = chainArray[index];
        chainArray[index] = newNode;
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];
        HashNode<K, V> previous = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (previous == null) {
                    chainArray[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return current.value;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    public boolean contains(V value) {
        for (HashNode<K, V> headNode : chainArray) {
            while (headNode != null) {
                if (headNode.value.equals(value)) {
                    return true;
                }
                headNode = headNode.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (HashNode<K, V> headNode : chainArray) {
            while (headNode != null) {
                if (headNode.value.equals(value)) {
                    return headNode.key;
                }
                headNode = headNode.next;
            }
        }
        return null;
    }

    public HashNode<K, V>[] getChainArray() {
        return chainArray;
    }
}