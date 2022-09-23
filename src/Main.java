import java.io.*;

public class Main {

    public Main() {

    }

    public static void main(String[] args) throws FileNotFoundException {

        HashTable.SinglyLinkedList sll = null;
        SicInfo si = null;
        HashTable ht = new HashTable();
        String name = "";
        int value = 0;
        int collisionCounter = 0;
        int numOfElems = 0;
        int storedElems = 0;
        int numOfSearches = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));

            String line;
            while((line = br.readLine()) != null) {
                String[] tok = line.split("\t");



                if(tok.length == 2) {
                    numOfElems++;   //increment number of elements read
                    name = tok[0];
                    value = Integer.parseInt(tok[1]);
                    //System.out.println(name + "\t" + value);
                    int hashValue = ht.hashFunc(name);

                    si = new SicInfo(name, value);
                    if(ht.find(name) == -1) {
                        ht.insert(si.getName(), si.getValue());
                        System.out.println("[Stored] " + name + " " + value + " at location " + hashValue + ".");
                        bw.write("[Stored] " + name + " " + value + " at location " + hashValue + "\n");
                    } // end if
                    else {
                        System.out.println("Error: Collision detected. Cannot store " + name + " " + value + " at location " + hashValue + ".");
                        System.out.println("\t--> " + ht.lookUp(name) + " already stored at location " + hashValue + ".");
                        bw.write("Error: Collision detected. Cannot store " + name + "." + "\n");
                        bw.write("\t--> " + ht.lookUp(name) + " already stored at location " + hashValue + "." + "\n");
                            collisionCounter++; //increment number of collisions

                    } // end else
                } // end if

                if(tok.length == 1) {
                    numOfSearches++;
                    name = tok[0];
                    //System.out.println(name);

                    if(ht.find(name) == -1) {
                        System.out.println("(SEARCH)- " + name + " WAS NOT found.");
                        bw.write("(SEARCH)- " + name + " WAS NOT found.\n");
                    } // end if
                    else {
                        System.out.println("(SEARCH)- " + name + " WAS found at location " + ht.hashFunc(name) + ".");
                        bw.write("(SEARCH)- " + name + " WAS found at location " + ht.hashFunc(name) + ".\n");
                    } // end else
                } // end if


            }  // end while

            storedElems = numOfElems - collisionCounter;    //Number of elements in the hash table

            System.out.println("\nThere was a total of " + collisionCounter + " collisions detected.");
            System.out.println("Only " + storedElems + " elements were stored out of a total " + numOfElems + " elements.");

            ht.printEmptyAndCollidedCells();
            System.out.println("\nA total of " + numOfSearches + " searches were conducted.\n");

            bw.write("\nThere was a total of " + collisionCounter + " collisions detected." + "\n");
            bw.write("Only " + storedElems + " elements were stored out of a total " + numOfElems + " elements." + "\n");
            bw.write("A total of " + numOfSearches + " searches were conducted.");

            br.close();
            bw.close();
        } catch(IOException e) {
            System.err.println("This is an IOException error!!!!");
            e.printStackTrace();
        } //end catch
        System.out.println("_______________________");
        System.out.println("|   Symbol  |  value  |");
        System.out.println("_______________________");
        ht.display();
        System.out.println("_______________________");
    }

    public static void printTokens(String tok[]) {
        for (String string : tok) {
            System.out.println(string);
        }
    }


}

