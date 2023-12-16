package Lab4;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        while (true){
            System.out.println("Введіть кількість змінних у СЛАР:");
            int n = in.nextInt();
            double[][] m = generateSLAR(n);
            System.out.println("Введіть кільксть потоків (ціле число):");
            int threadAmount = in.nextInt();
            long startTime = System.currentTimeMillis();
            double[] solutions = MethodGausa.calculateMethodGausa(m, threadAmount);
            long endTime = System.currentTimeMillis();
            System.out.println("Кількість потоків: " + threadAmount + "; Time: " + (endTime - startTime) + " milliseconds");
            System.out.println("Рішення в багатопоточності: ");
            for (double s:solutions) {
                System.out.print(Math.round(s) + " ");
            }
            System.out.println();
        }
    }

    public static double[][] generateSLAR(int n) {
        Random random = new Random();
        double[] ans = new double[n];
        double[][] matrix = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            ans[i] = random.nextInt(51);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(51);
            }
            for (int j = 0; j < n; j++) {
                matrix[i][n] += ans[j] * matrix[i][j];
            }
        }

        System.out.print("Фактичні розв'язки випадкової системи:\n");
        for (int i = 0; i < ans.length; i++) {
            System.out.print(Math.round(ans[i]) + " ");
        }
        System.out.println();

        return matrix;
    }
}
