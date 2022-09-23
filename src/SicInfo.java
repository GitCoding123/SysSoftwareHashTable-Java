public class SicInfo {
    private String name;
    private int value;

    public SicInfo() {
        this.name = "";
        this.value = 0;
    }

    public SicInfo(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * getName() returns the name of the state object.
     *
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * setName() sets the name of the state object from the csv file
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
