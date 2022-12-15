package lesson08;

class Object {
    private final String id;
    public Object(String id) {
        this.id = id;
    }
    public String getID() {
        return this.id;
    }
}

public class HashTable {
    private static int hashTableSize;
    private static Object[][] hashTable;
    public static Object plug = new Object("*");

    public HashTable(int size) {
        hashTableSize = size;
        hashTable = new Object[hashTableSize][hashTableSize];
    }

    /**
     * Calculates has value that is less than hash table size using equation h(i+1)=(h(i) * p + h(char(i+1))) % q
     * @param name Object's ID
     * @param hashConst constant p used in equation
     * @return hash value
     */
    public static int hashFunction(String name, int hashConst){
        int result = 0;
        for (int i = 0; i < name.length(); i++) {
            int charNumber = name.charAt(i);
            result = result * hashConst + charNumber;
        }
        return result % hashTableSize;
    }

    /**
     * Inserts an object by its ID
     * @param object Object's ID
     */
    public static void insert(Object object) {
        int hashValue = hashFunction(object.getID(), 5);
        int nextInQueue = 0;
        while (hashTable[hashValue][nextInQueue] != null && hashTable[hashValue][nextInQueue] != plug) {
            nextInQueue++;
            if (nextInQueue >= hashTable.length) {
                System.out.println("Not enough place for this object.");
                return;
            }
        }
        hashTable[hashValue][nextInQueue] = object;
    }

    /**
     * Searches an object by its ID
     * @param objectID Object's ID
     */
    public static void search(String objectID) {
        int hashValue = hashFunction(objectID, 5);
        int nextInQueue = 0;
        int found = 0;
        while (hashTable[hashValue][nextInQueue] != null) {
            if (hashTable[hashValue][nextInQueue].getID().equals(objectID)) {
                System.out.println("\nThe object \"" + objectID + "\" has been successfully found at " + hashValue + "/" + nextInQueue + " position.");
                found++;
            }
            nextInQueue++;
            if (nextInQueue == hashTable.length) {
                break;
            }
        }
        if (found == 0) {
            System.out.println("\nThe object has not been found.");
        }
    }

    /**
     * Deletes an object by its ID
     * @param objectID Object's ID
     */
    public static void delete(String objectID) {
        int hashValue = hashFunction(objectID, 5);
        int nextInQueue = 0;
        while (hashTable[hashValue][nextInQueue] != null) {
            if (hashTable[hashValue][nextInQueue].getID().equals(objectID)) {
                hashTable[hashValue][nextInQueue] = plug;
                System.out.println("\nThe object \"" + objectID + "\" has been successfully deleted at " + hashValue + "/" + nextInQueue + " position.");
            }
            nextInQueue++;
        }
    }

    /**
     * Creates new hash table as a two-dimensional list of objects
     */
    public static void printHashTable() {
        System.out.println("\nHash table:\n");
        for (int i = 0; i < hashTable.length; i++) {
            for (int j = 0; j < hashTable.length; j++) {
                if (hashTable[i][j] != null) {
                    System.out.print(hashTable[i][j].getID() + "\t\t");
                } else {
                    System.out.print("-" + "\t\t");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        insert(new Object("some"));
        insert(new Object("years"));
        insert(new Object("ago"));
        insert(new Object("never"));
        insert(new Object("mind"));
        insert(new Object("how"));
        insert(new Object("long"));
        insert(new Object("precisely"));

        printHashTable();
        search("mind");
        delete("ago");
        insert(new Object("i thought"));
        printHashTable();
    }
}
