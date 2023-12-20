package Lab6;

public class FloydMethod {
    private static long [][] A;
    private static long [][] C;
    private static long [][] P;

    public static long[][] getA() {
        return A;
    }

    public static void setA(long[][] a) {
        A = a;
        C = new long[A.length][A.length];
        P = new long[A.length][A.length];
    }

    public static long[][] getC() {
        return C;
    }

    public static long[][] getP() {
        return P;
    }

    public static void findShortestPaths() {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j];
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (C[i][k] + C[k][j] < C[i][j]) {
                        C[i][j] = C[i][k] + C[k][j];
                        P[i][j] = k + 1;
                    }
                }
            }
        }
    }
}
