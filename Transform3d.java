package nz.ac.massey.a2;



public class Transform3d {
    private static final int SIZE = 3;

    // Transformation matrices
    private final double[][] tXY  = new double[SIZE][SIZE];
    private final double[][] tYZ  = new double[SIZE][SIZE];
    private final double[][] tXZ  = new double[SIZE][SIZE];
    private final double[][] temp = new double[SIZE][SIZE];

    // Transformation matrix that is visible to users of the class
    public double[][] tmx = new double[SIZE][SIZE];

    private static void setIdentity(double[][] mx) {
        for (int i=0; i<SIZE; ++i) {
            for (int j=0; j<SIZE; ++j) {
                mx[i][j] = 0.0;
            }
            mx[i][i] = 1.0;
        }
    }

    private static void multiply(double[][] mx1, double[][] mx2, double[][] result) {
        double sum;
        for (int i=0; i<SIZE; ++i) {
            for (int j=0; j<SIZE; ++j) {
                sum = 0;
                for (int k=0; k <SIZE; ++k) {
                    sum += mx1[i][k] * mx2[k][j];
                }
                result[i][j] = sum;
            }
        }
    }

    public Transform3d() {
        setIdentity(tXY);
        setIdentity(tXZ);
        setIdentity(tYZ);
        calculate();
    }

     void setXY(double angle) {
        tXY[0][0] = Math.cos(angle); tXY[0][1] = -Math.sin(angle); tXY[0][2] = 0;
        tXY[1][0] = Math.sin(angle); tXY[1][1] = Math.cos(angle) ; tXY[1][2] = 0;
        tXY[2][0] = 0              ; tXY[2][1] = 0               ; tXY[2][2] = 1;
        calculate();

    }
     void setXZ(double angle) {
        tXZ[0][0] = Math.cos(angle) ; tXZ[0][1] = 0               ; tXZ[0][2] = Math.sin(angle);
        tXZ[1][0] = 0               ; tXZ[1][1] = 1               ; tXZ[1][2] = 0;
        tXZ[2][0] = -Math.sin(angle); tXZ[2][1] = 0               ; tXZ[2][2] = Math.cos(angle);
        calculate();
    }
     void setYZ(double angle) {
        tYZ[0][0] = 1              ; tYZ[0][1] = 0               ; tYZ[0][2] = 0;
        tYZ[1][0] = 0              ; tYZ[1][1] = Math.cos(angle) ; tYZ[1][2] = -Math.sin(angle);
        tYZ[2][0] = 0              ; tYZ[2][1] = Math.sin(angle) ; tYZ[2][2] = Math.cos(angle);
        calculate();
    }

     void calculate() {
        multiply(tXY,tYZ,temp);
        multiply(tXZ,temp,tmx);
        multiply(tXZ,temp,tmx);
    }

}
