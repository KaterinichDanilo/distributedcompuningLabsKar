package Lab6;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int threadsAmount = 4;
    public static void main(String[] args) {
        long inf = Integer.MAX_VALUE;
//        long [][] A = {
//                {0, 8, 5, inf},
//                {3, 0, inf, inf},
//                {inf, 2, 0, inf},
//                {inf, inf, inf, 0}
//        };

        long [][] A = getRandomGraph(700);
        int n = A.length;
        FloydMethod.setA(A);

        long startTime = System.currentTimeMillis();
        FloydMethod.findShortestPaths();
        long endTime = System.currentTimeMillis();

        System.out.println("Time: " + (endTime - startTime));

//        System.out.println("A");
//        printMatrix(FloydMethod.getA(), 4);
        System.out.println("C");
        printMatrix(FloydMethod.getC(), 5);
        System.out.println("P");
        printMatrix(FloydMethod.getP(), 5);



        long[][] P = getZeroMatrix(n);
        FloydMethodParallel floydMethodParallel = new FloydMethodParallel(A, P, threadsAmount);

        startTime = System.currentTimeMillis();
        floydMethodParallel.findShortestPaths();
        endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime));

        System.out.println("C");
        printMatrix(floydMethodParallel.getC(), 5);
        System.out.println("P");
        printMatrix(floydMethodParallel.getP(), 5);
    }

    private static void printMatrix(long [][] a, int l) {
        for (int i = 0; i < a.length && i < l; i++) {
            for (int j = 0; j < a[0].length && j < l; j++) {
                if (a[i][j] == Integer.MAX_VALUE) {
                    System.out.print("inf ");
                } else {
                    System.out.print(a[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    private static long[][] getZeroMatrix(int n) {
        long[][] matrix = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = 0;
            }
        }
        return matrix;
    }

    private static long[][] getRandomGraph(int n) {
        long [][] matrix = new long[n][n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    // Випадкові числа від 1 до 50 або Integer.MAX_VALUE
                    matrix[i][j] = random.nextBoolean() ? random.nextInt(50) + 1 : Integer.MAX_VALUE;
                }
            }
        }

        return matrix;
    }
}
