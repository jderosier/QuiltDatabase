public class Pattern {
    private String name;
    private String designer;
    private String sizes;
    private String freeText;
    private String img1;
    private String img2;

    public Pattern(String name, String designer, String sizes, String freeText, String img1, String img2) {
        this.name = name;
        this.designer = designer;
        this.sizes = sizes;
        this.freeText = freeText;
        this.img1 = img1;
        this.img2 = img2;
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
    public String getFreeText() {
        return freeText;
    }
    public String getImg1() {
        return img1;
    }
    public String getImg2() {
        return img2;
    }

    public String databaseString() {
        return name + "," + designer + "," + sizes + "," + freeText + "," + img1 + "," + img2;
    }

    @Override
    public String toString() {
        return name + ", " + designer;
    }
}
