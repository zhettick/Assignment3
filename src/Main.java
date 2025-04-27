import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            String id = String.valueOf(i);
            String name = "Student" + random.nextInt(1000);
            MyTestingClass key = new MyTestingClass(id, name);
            Student value = new Student(i, "Name" + random.nextInt(1000), random.nextDouble(1.0, 4.0));
            table.put(key, value);
        }

        for (int i = 0; i < table.getChainArray().length; i++) {
            int count = 0;
            var current = table.getChainArray()[i];
            while (current != null) {
                count++;
                current = current.getNext();
            }
            System.out.println("Bucket " + i + ": " + count + " elements");
        }

        BST<Integer, Character> bst = new BST<>();
        bst.put(10, 'A');
        bst.put(20, 'D');
        bst.put(5, 'S');

        for (BST.Entry<Integer, Character> entry : bst.iterator()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}