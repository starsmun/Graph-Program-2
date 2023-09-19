package nz.ac.massey.a2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Wireframe {

    public int numTriangles = 0;
    public int numVertices = 0;

    // Vertex coordinates in the world scene for each vertex of the triangle
    public double[][] vertArray;
    public double[][] transVertices;

    // Triangle mapping
    public int[][] triArray, triDrawable;

    Integer[] drawIndices;


    // Here we apply the rotations and scaling before drawing the display
    public void toView(double[][] tmx, double scale) {
        transVertices = new double[numVertices][3];
        double sum;
        for (int i=0; i<numVertices; ++i) {
            for (int j=0; j<3; ++j) {
                sum = 0;
                for (int k=0; k <3; ++k) {
                    sum += vertArray[i][k] * tmx[k][j];
                }
                transVertices[i][j] = sum;
            }
        }

    }

    public void calculateDrawable() {
        // Calculates and orders the drawable triangles using the Back-face  Culling and Painterly methods //
        double[] zComponent = new double[numTriangles];
        Double[] zDrawable;

        int drawNum = 0;


        // Calculate the z components of every normal vector
        for (int i = 0; i < numTriangles; i++ ) {
            double uX = transVertices[triArray[i][1]][0] - transVertices[triArray[i][0]][0];
            double uY = transVertices[triArray[i][1]][1] - transVertices[triArray[i][0]][1];
            double vX = transVertices[triArray[i][2]][0] - transVertices[triArray[i][0]][0];
            double vY = transVertices[triArray[i][2]][1] - transVertices[triArray[i][0]][1];
            zComponent[i] = (uX * vY) - (uY * vX); // The normal is u x v

            // Determine how many triangles can actually be drawn
            if (zComponent[i] < 0) { // Remember view = (0, 0, -1)
                drawNum++;
            }
        }

        // Initialize drawable arrays
        triDrawable = new int[drawNum][3];
        zDrawable = new Double[drawNum];
        // Put drawable triangles in the drawable array

        // Put drawable triangles in the drawable array
        int k = 0;
        int l = 0;
        for (int i = 0; i < numTriangles; ++i) {
            if (zComponent[i] < 0) {
                zDrawable[k] = zComponent[i];
                for (int j = 0; j < 3; ++j) {
                    triDrawable[k][l] = triArray[i][j];
                    l++;
                }
                k++;
                l = 0;
            }
        }


        // Sort drawable triangles for Painterly using precalculated zcomponents
        ArrayIndexComparator comparator = new ArrayIndexComparator(zDrawable);
        drawIndices = comparator.createIndexArray();
        Arrays.sort(drawIndices, Collections.reverseOrder(comparator)); // Needs to be reversed for correct effect
    }
}


class ArrayIndexComparator implements Comparator<Integer> {
    /* A class to allow sorting of an array of doubles in order to get the
   correct order of triangles for Painterly
    */
    private final Double[] array;
    public ArrayIndexComparator(Double[] array)
    {
        this.array = array;
    }
    public Integer[] createIndexArray() {
        Integer[] indices = new Integer[array.length];
        for (int i = 0; i < array.length; i++)
        {
            indices[i] = i; // Autoboxing
        }
        return indices;
    }
    public int compare(Integer index1, Integer index2) {
        // Auto-unbox from Integer to int to use as array indices
        return array[index1].compareTo(array[index2]);
    }
}


