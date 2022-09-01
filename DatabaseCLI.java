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
        String sizes = sqan.nextLine();
        System.out.print("                     Crib: ");
        sizes += sqan.nextLine();
        System.out.print("                     Throw: ");
        sizes += sqan.nextLine();
        System.out.print("                     Twin: ");
        sizes += sqan.nextLine();
        System.out.print("                     Full: ");
        sizes += sqan.nextLine();
        System.out.print("                     Queen: ");
        sizes += sqan.nextLine();
        System.out.print("                     King: ");
        sizes += sqan.nextLine();

        System.out.print("How Many Fat Quarters (or 0): ");
        int fatQuarter = 0;
        try {
            fatQuarter = Integer.parseInt(sqan.nextLine());
        }
        catch (Exception e) {
            System.out.println("Error - Setting Fat Quarters to 0");
        }

        Pattern pattern = new Pattern(name, designer, sizes, fatQuarter);
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
        System.out.print("Enter What Attribute to Search (h for help): ");
        String cmd = sqan.nextLine();
        if(cmd.equals("h")) {
            System.out.println("n - Name\nd - Designer\ns - Size\nf - Fat Quarter");
            System.out.print("Enter What Attribute to Search (h for help): ");
            cmd = sqan.nextLine();
        }

        String toFind = "";
        if(cmd.equals("n")) {
            System.out.println("Enter Full or Part of Name: ");
            toFind = sqan.nextLine();
            return database.findName(toFind).toString();
        }
        else if(cmd.equals("d")) {
            System.out.println("Enter Full or Part of Designer: ");
            toFind = sqan.nextLine();
            return database.findDesigner(toFind).toString();
        }
        else if(cmd.equals("s")) {
            System.out.println("Enter Size to Find (h for help): ");
            toFind = sqan.nextLine();
            if(toFind.equals("h")) {
                System.out.println("b - Baby\nc - Crib\nth - Throw\ntw - Twin\nf - Full\nq - Queen\nk - King");
                toFind = sqan.nextLine();
            }
            return database.findSize(toFind).toString();
        }
        else if(cmd.equals("d")) {
            System.out.println("Enter # of Fat Quarters: ");
            toFind = sqan.nextLine();
            return database.findName(toFind).toString();
        }
        else {
            return "Invalid Search Query";
        }
    }


    public void startCLI() {
        System.out.print("Enter Command (or h for help): ");
        String cmd = sqan.nextLine();

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
            cmd = sqan.nextLine();
        }
        sqan.close();
        System.out.println("Bye!");
    }
    
    public static void main(String[] args) {
        DatabaseCLI cli = new DatabaseCLI();
        cli.startCLI();
    }
}
