public class Pattern {
    private String name;
    private String designer;

    public Pattern() {}

    public Pattern(String name, String designer) {
        //Have to specify all Attributes
        this.name = name;
        this.designer = designer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "," + designer;
    }
}
