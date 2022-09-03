import java.io.File;
import java.util.Scanner;

public class DatabaseCLI {
    public static String FILE_PATH = "pattern_data.txt";
    private Database database;
    private Scanner sqan;

    public DatabaseCLI() {
        database = new Database(FILE_PATH);
        sqan = new Scanner(System.in);
    }
    
    public String add() {
        System.out.print("Enter Attributes\nName: ");
        String name = sqan.nextLine();
        System.out.print("Designer: ");
        String designer = sqan.nextLine();

        System.out.print("Can the Pattern Make Baby (y/n): ");
        String sizeToAdd = sqan.nextLine();
        String sizes = "";
        if(sizeToAdd.toLowerCase().equals("y") || sizeToAdd.toLowerCase().equals("yes")) {
            sizes += "y";
        } else {
            sizes += "n";
        }
        System.out.print("                     Crib: ");
        sizeToAdd = sqan.nextLine();
        if(sizeToAdd.toLowerCase().equals("y") || sizeToAdd.toLowerCase().equals("yes")) {
            sizes += "y";
        } else {
            sizes += "n";
        }
        System.out.print("                     Throw: ");
        sizeToAdd = sqan.nextLine();
        if(sizeToAdd.toLowerCase().equals("y") || sizeToAdd.toLowerCase().equals("yes")) {
            sizes += "y";
        } else {
            sizes += "n";
        }
        System.out.print("                     Twin: ");
        sizeToAdd = sqan.nextLine();
        if(sizeToAdd.toLowerCase().equals("y") || sizeToAdd.toLowerCase().equals("yes")) {
            sizes += "y";
        } else {
            sizes += "n";
        }
        System.out.print("                     Full: ");
        sizeToAdd = sqan.nextLine();
        if(sizeToAdd.toLowerCase().equals("y") || sizeToAdd.toLowerCase().equals("yes")) {
            sizes += "y";
        } else {
            sizes += "n";
        }
        System.out.print("                     Queen: ");
        sizeToAdd = sqan.nextLine();
        if(sizeToAdd.toLowerCase().equals("y") || sizeToAdd.toLowerCase().equals("yes")) {
            sizes += "y";
        } else {
            sizes += "n";
        }
        System.out.print("                     King: ");
        sizeToAdd = sqan.nextLine();
        if(sizeToAdd.toLowerCase().equals("y") || sizeToAdd.toLowerCase().equals("yes")) {
            sizes += "y";
        } else {
            sizes += "n";
        }

        System.out.print("Free Text: ");
        String freeText = sqan.nextLine();

        System.out.println("Enter Absolute Path of Image 1: ");
        String img1 = sqan.nextLine();
        File file = new File(img1);
        while(!file.exists()) {
            System.out.println("File Not Found\nEnter Absolute Path of Image 1");
            img1 = sqan.nextLine();
            file = new File(img1);
        }

        System.out.println("Enter Absolute Path of Image 2 (or N/A): ");
        String img2 = sqan.nextLine();
        file = new File(img2);
        while((!file.exists()) && (!img2.equals("N/A"))) {
            System.out.println("File Not Found\nEnter Absolute Path of Image 2 (or N/A)");
            img1 = sqan.nextLine();
            file = new File(img1);
        }

        Pattern pattern = new Pattern(name, designer, sizes, freeText, img1, img2);
        int res = database.add(pattern);
        if(res == 0) {
            return "Success";
        }
        else if(res == -1) {
            return "Error - Pattern Already in Database";
        }
        else if(res == -2) {
            return "Error - There was a Problem Adding Pattern";
        }

        return "Error - the program has no idea what happened";
    }

    public String remove() {
        System.out.print("Enter Name of Pattern to Remove: ");
        int res = database.remove(sqan.nextLine());

        if(res == 0) {
            return "Success";
        }
        else if(res == -1) {
            return "Error - Pattern not in Database";
        }
        else if(res == -2) {
            return "Error - There was a Problem Removing Pattern";
        }

        return "Error - the program has no idea what happened";
    }

    public String find() {
        System.out.println("n - Name\nd - Designer\ns - Size\nf - Free Text");
        System.out.print("Enter What Attribute to Search (h for help): ");
        String cmd = sqan.nextLine().toLowerCase();

        String toFind = "";
        if(cmd.equals("n")) {
            System.out.print("Enter Full or Part of Name: ");
            toFind = sqan.nextLine();
            return database.findName(toFind).toString();
        }
        else if(cmd.equals("d")) {
            System.out.print("Enter Full or Part of Designer: ");
            toFind = sqan.nextLine();
            return database.findDesigner(toFind).toString();
        }
        else if(cmd.equals("s")) {
            System.out.println("b - Baby\nc - Crib\nth - Throw\ntw - Twin\nf - Full\nq - Queen\nk - King");
            System.out.print("Enter Size to Find: ");
            toFind = sqan.nextLine().toLowerCase();
            return database.findSize(toFind).toString();
        }
        else if(cmd.equals("f")) {
            System.out.print("Enter Free Text to Search For: ");
            toFind = sqan.nextLine().toLowerCase();
            return database.findFreeText(toFind).toString();
        }
        else {
            return "Invalid Search Query";
        }
    }


    public void startCLI() {
        System.out.print("Enter Command (or h for help): ");
        String cmd = sqan.nextLine().toLowerCase();

        while(!cmd.toLowerCase().equals("q")) {
            if(cmd.toLowerCase().equals("h")) {
                System.out.println("a - add\nr - remove\nh - brings up this menu\nl - list\nf - find pattern\nq - quit");
            }
            else if(cmd.toLowerCase().equals("a")) {
                System.out.println(add());
            }
            else if(cmd.toLowerCase().equals("r")) {
                System.out.println(remove());
            }
            else if(cmd.toLowerCase().equals("l")) {
                database.listAll();
            }
            else if(cmd.toLowerCase().equals("f")) {
                System.out.println(find());
            }
            else {
                System.out.println("Invalid Command (h for help menu)");
            }
            System.out.print("Enter Command: ");
            cmd = sqan.nextLine().toLowerCase();
        }
        sqan.close();
        System.out.println("Bye!");
    }
    
    public static void main(String[] args) {
        DatabaseCLI cli = new DatabaseCLI();
        cli.startCLI();
    }
}
