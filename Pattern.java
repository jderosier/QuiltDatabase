public class Pattern {
    private String name;
    private String designer;
    private String sizes;
    private int fatQuarter;

    public Pattern(String name, String designer, String sizes, int fatQuarter) {
        this.name = name;
        this.designer = designer;
        this.sizes = sizes;
        this.fatQuarter = fatQuarter;
    }

    public String getName() {
        return name;
    }
    public String getDesigner() {
        return designer;
    }
    public String getSizes() {
        return sizes;
    }
    public boolean canMakeSize(String size) {
        if(size.equals("b")) {
            return sizes.charAt(0) == 'y';
        }
        else if(size.equals("c")) {
            return sizes.charAt(1) == 'y';
        }
        else if(size.equals("th")) {
            return sizes.charAt(2) == 'y';
        }
        else if(size.equals("tw")) {
            return sizes.charAt(3) == 'y';
        }
        else if(size.equals("f")) {
            return sizes.charAt(4) == 'y';
        }
        else if(size.equals("q")) {
            return sizes.charAt(5) == 'y';
        }
        else if(size.equals("k")) {
            return sizes.charAt(6) == 'y';
        }
        else {
            return false;
        }
    }
    public int getFatQuarter() {
        return fatQuarter;
    }

    @Override
    public String toString() {
        return name + "," + designer;
    }
}
