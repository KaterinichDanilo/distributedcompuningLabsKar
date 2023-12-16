package Lab3;

import java.util.Scanner;

public class Main {
    private static final double A = 1, B = 9;
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        IntegralCalculator integralCalculator = new IntegralCalculator();
        System.out.println("Підінтегральна функція: 3 * sqrt(t)dt\nМежі інтегрування: a = " + A + ", b = " + B);
        System.out.println("Метод лівих прямокутників");

        while (true){
            System.out.println("Введіть кількість інтервалів (ціле число):");
            int n = in.nextInt();
            System.out.println("Введіть кільксть потоків (ціле число):");
            int threadsAmount = in.nextInt();
            System.out.println(integralCalculator.countIntegral(A, B, n, threadsAmount));
        }
    }
}
