
//Liest die Blender Files
import GLOOP.GLVektor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class STLParser {
    public static ArrayList<GLVektor> importModel(File file) {
        ArrayList<GLVektor> vertices = new ArrayList<GLVektor>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("facet normal")) {
                    String[] parts = line.split("\\s+");
                    int i = 1;
                    if (parts.length == 5) {
                        i = 0;
                    }
                    vertices.add(new GLVektor(Double.parseDouble(parts[i + 2]), Double.parseDouble(parts[i + 3]), Double.parseDouble(parts[i + 4])));
                }//Made by TubYoub
                if (line.contains("vertex")) {
                    String[] parts = line.split("\\s+");
                    //System.out.println(parts.length);
                    int i = 1;
                    if (parts.length == 4) {
                        i = 0;
                    }
                    vertices.add(new GLVektor(Double.parseDouble(parts[i + 1]), Double.parseDouble(parts[i + 2]), Double.parseDouble(parts[i + 3])));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("Parsed successful");
        return vertices;
    }
}
