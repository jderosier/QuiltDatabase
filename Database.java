import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

class Database {
    private HashMap<String, Pattern> map;
    private File file;

    public Database(String filepath) {
        this.map = new HashMap<>();
        this.file = new File(filepath);

        if(!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }
        else {
            try {
                FileReader reader = new FileReader(file);
                BufferedReader buffer = new BufferedReader(reader);
                String line = buffer.readLine();
                while(line != null) {
                    String[] tokens = line.split(",");
                    Pattern pattern = new Pattern(tokens[0],tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4], tokens[5]);
                    map.put(tokens[0], pattern);
                    line = buffer.readLine();
                }
                reader.close();
            }
            catch (Exception e){
                System.out.println(e);;
            }
        }
    }

    public int addToFile(Pattern pattern) throws Exception{
        File tempFile = new File("temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            writer.write(currentLine + System.getProperty("line.separator"));
        }

        writer.write(pattern.toString());
        writer.close();
        reader.close();

        boolean oldDeleted = file.delete();
        boolean renamed = tempFile.renameTo(file);

        if(renamed && oldDeleted) {
            return 0;
        }
        else {
            throw new Exception("Not Even the Program knows what happened");
        }
    }

    public int add(Pattern pattern) {
        if(!map.containsKey(pattern.getName())) {
            map.put(pattern.getName(), pattern);
            try {
                addToFile(pattern);
            }
            catch (Exception e) {
                System.out.println(e);
                map.remove(pattern.getName());
                return -2;
            }
            return 0;
        }
        return -1;
    }

    private int removeFromFile(Pattern pattern) throws Exception{
        File tempFile = new File("temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(pattern.toString())) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close(); 
        reader.close(); 
        boolean oldDeleted = file.delete();
        boolean renamed = tempFile.renameTo(file);
        if(renamed && oldDeleted) {
            return 0;
        }
        else {
            throw new Exception("Problem XD");
        }
    }

    public int remove(String name) {
        if(this.map.containsKey(name)) {
            Pattern pattern = this.map.remove(name);
            try {
                removeFromFile(pattern);
            }
            catch (Exception e) {
                System.out.println(e);
                return -2;
            }
            return 0;
        }
        return -1;
    }

    public HashSet<String> findName(String toFind) {
        HashSet<String> set = new HashSet<String>();
        for(String key : map.keySet()) {
            if(key.contains(toFind)) {
                set.add(key);
            }
        }
        return set;
    }
    public HashSet<String> findDesigner(String toFind) {
        HashSet<String> set = new HashSet<String>();
        for(String key : map.keySet()) {
            if(map.get(key).getDesigner().contains(toFind)) {
                set.add(key);
            }
        }
        return set;
    }
    public HashSet<String> findSize(String toFind) {
        HashSet<String> set = new HashSet<String>();
        for(String key : map.keySet()) {
            if(map.get(key).canMakeSize(toFind)) {
                set.add(key);
            }
        }
        return set;
    }
    public HashSet<String> findFatQuarters(String toFind) {
        HashSet<String> set = new HashSet<String>();
        for(String key : map.keySet()) {
            if(map.get(key).getFatQuarter() == Integer.parseInt(toFind)) {
                set.add(key);
            }
        }
        return set;
    }

    public void listAll() {
        for(String name : map.keySet()) {
            System.out.println("\t" + map.get(name));
        }
    }
}