import java.util.ArrayList;


/**
 *
 */
public class HashTable {
    public SinglyLinkedList[] hashArray;
    private int arraySize = 59;
    SinglyLinkedList sll = new SinglyLinkedList();

    /**
     *
     */
    private class Node {
        String name;
        int value;
        Node nextNode;
        /**
         * The Node constructor allows us to create a node that takes the given parameters.
         *
         * @param name          this is the name of the state contained in the node
         */
        public Node(String name, int value) {
            this.name = name;
            this.value = value;

        }
        /**
         *
         */
        public void printNode() {
            String s = "|";
            System.out.printf("%-7s %-4s %-4d |\n", name, s, value);
        }
    }

    /**
     * The class SinglyLinkedList creates a list for our Nodes that will be incorporated
     * hashArray.
     *
     * @author bgerk
     */
    public class SinglyLinkedList {

        private Node first;
        private Node last;

        /**
         * Constructor for the singly linked list.
         */
        public SinglyLinkedList() {
            first = null;
        }

        /**
         * Insert method for inserting nodes into the END of the list.
         *
         * @param node    Node to be inserted into the list.
         */
        public void insertToList(Node node) {
            //Code for END insertion

            if(isEmpty()) {
                first = node;
            }
            else {
                last.nextNode = node;
            }
            last = node;
        }

        /**
         * Method for
         *
         * @return    true if first == null, false if otherwise.
         */
        public boolean isEmpty() {
            return first == null;
        }

        /**
         * Method that deletes node from list.
         *
         * @param key    used as a parameter to find which node we must delete
         */
        public void deleteFromList(int key) {


            Node current = first;
            Node previous = first;
            while (true) {

                if(current.name == findNode(key).name) {
                    //System.out.println(current.name + " has been deleted from the hash table.");
                    first = first.nextNode;
                    break;
                }

                else {
                    previous = current;
                    current = current.nextNode;
                    if (current.name == findNode(key).name) {
                        //System.out.println(current.name + " has been deleted from the hash table.");
                        previous.nextNode = current.nextNode;
                    }
                    else {
                        continue;
                    }
                }
            }
        }
        /**
         * Method findNode will find a node in the preferred list within the hash index.
         *
         * @param key      Used as a parameter to locate node in the list.
         * @return current   Node to be returned.
         */
        public Node findNode(int key) {
            Node current = first;

            while(current != null && hashFunc(current.name) <= key) {
                if(hashFunc(current.name) == key) {
                    return current;
                }
                current = current.nextNode;
            }
            return current;
        }

        /**
         * method displayList() will display the list
         */
        public void displayList() {
            String s = "-----";
            String s2 = "|";
            Node current = first;
            if (current == null) {
                System.out.printf("%-7s %-9s |\n", s, s2);
            }
            while (current != null) {
                current.printNode();
                current = current.nextNode;
            }
        }

        /**
         * method getFirstNode() will return the first node of the chosen list.
         * @return first     Node to be returned.
         */
        public Node getFirstNode() {
            return first;
        }
    }

    /**
     *
     */
    public HashTable() {
        hashArray = new SinglyLinkedList[arraySize];
        for (int j = 0; j < arraySize; j++) {
            hashArray[j] = new SinglyLinkedList();
        }
    }


    /**
     * insert() method will take parameters and insert them into a node and will
     * pass that node to the insertToList() method.
     *
     * @param name          Name of the node.
     */
    public void insert(String name, int value) {

        Node node = new Node(name, value);

        int hashVal = hashFunc(name);
        hashArray[hashVal].insertToList(node);
    }
    /**
     * find() method is used to search the linked list at the proper
     * position in the hash table for the state, and if found will return table index or -1 if not found
     *
     * @param name        The state's name is used as a parameter.
     * @return hashVal     The hash value is returned
     */
    public int find(String name) {


        int hashVal = hashFunc(name);
        if(hashArray[hashVal].findNode(hashVal) == null) {
            return -1;
        }
        else {
            return hashVal;
        }
    }

    public String lookUp(String name) {

        int hashVal = hashFunc(name);

        if(hashArray[hashVal].getFirstNode() == null) {
            return "empty.";
        }
        else {
            return hashArray[hashVal].getFirstNode().name;
        }
    }

    /**
     * delete() method is used to find and delete the state of the given
     * name from the hash table.
     *
     * @param name   state name passed in through the parameter.
     */
    public void delete(String name) {

        int hashVal = hashFunc(name);
        hashArray[hashVal].deleteFromList(hashVal);
    }

    /**
     * display() method will iterate through each hash table index and print all lists
     * for every non-empty index.
     */
    public void display() {
        //String s = ".) ";
        for (int j = 0; j < arraySize; j++) {

            System.out.printf("%-4d", j);
            hashArray[j].displayList();
        }
    }

    /**
     * printEmptyAndCollidedCells() method prints the number of
     * empty cells and the number of collided cells in the hash table array.
     */
    public void printEmptyAndCollidedCells() {
        int x = 0;
        int y = 0;

        for(int i = 0; i < arraySize; i++) {
            if(hashArray[i].getFirstNode() == null) {
                x++;
            }
            else {
                if(hashArray[i].getFirstNode().nextNode != null) {
                    y++;
                }
            }

        }

        System.out.println("There are " + x + " empty cells in the hash table.");
        System.out.println("");
    }

    /**
     *
      * @param name
     * @return
     */
    public int hashFunc(String name) {
        int hashValue;
        int sum = 0;
        int lastCharacter = name.length();

        for (int i = 0; i < name.length(); i++) {
            sum += (name.charAt(i));
        }
        hashValue = sum * lastCharacter % arraySize;

        return hashValue;
    }


}

