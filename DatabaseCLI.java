import java.util.Scanner;

public class DatabaseCLI {
    public static String FILE_PATH = "pattern_data.txt";
    
    public static String add(Database database, Scanner sqan) {
        Pattern pattern = new Pattern();
        System.out.print("Enter Attributes\nName: ");
        pattern.setName(sqan.nextLine());
        System.out.print("Designer: ");
        pattern.setDesigner(sqan.nextLine());

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

    public static String remove(Database database, Scanner sqan) {
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
    
    
    public static void main(String[] args) {
        Scanner sqan = new Scanner(System.in);
        Database database = new Database(FILE_PATH);
        System.out.print("Enter Command (or h for help): ");
        String cmd = sqan.nextLine();

        while(!cmd.toLowerCase().equals("q")) {
            if(cmd.toLowerCase().equals("h")) {
                System.out.println("a - add\nr - remove\nh - brings up this menu\nl - list\nf - find pattern\nq - quit");
            }
            else if(cmd.toLowerCase().equals("a")) {
                System.out.println(add(database, sqan));
            }
            else if(cmd.toLowerCase().equals("r")) {
                System.out.println(remove(database, sqan));
            }
            else if(cmd.toLowerCase().equals("l")) {
                database.listAll();
            }
            else if(cmd.toLowerCase().equals("f")) {
                System.out.println("Enter Full or Part of Name: ");
                System.out.println(database.find(sqan.nextLine()));
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
}
