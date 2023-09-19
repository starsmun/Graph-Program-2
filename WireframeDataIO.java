package nz.ac.massey.a2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WireframeDataIO {
    public static Wireframe read(File myFile) {
        Wireframe wired = new Wireframe();
        String line;
        try {
            // Create the scanner, scan number of vertices, size the array
            Scanner scanner = new Scanner(myFile);
            line = scanner.nextLine();
            wired.numVertices = Integer.parseUnsignedInt(line);
            wired.vertArray = new double[wired.numVertices][3];
            // Collect all vertices from the file
            for (int i = 0; i < wired.numVertices; ++i) {
                line = scanner.nextLine();
                String[] lineTokens = line.split("\\s+");
                int k = 0;
                for (String token : lineTokens) {
                    if (token == lineTokens[0]) continue;
                    if ((!token.isEmpty()) && (k < 3)) {
                        wired.vertArray[i][k] = Double.parseDouble(token);
                        k++;
                    }
                }
            }
            // Take in the number of triangles and size the triangle array
            line = scanner.nextLine();
            wired.numTriangles = Integer.parseUnsignedInt(line);
            wired.triArray = new int[wired.numTriangles][3];
            // Collect all triangles from the file
            for (int i = 0; i < wired.numTriangles; ++i) {
                line = scanner.nextLine();
                String[] lineTokens = line.split("\\s+");
                for (int j = 1; j < 4; ++j) {
                    wired.triArray[i][j-1] = Integer.parseUnsignedInt(lineTokens[j]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return wired;




    }

}
