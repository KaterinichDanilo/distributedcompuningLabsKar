package Lab1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("Set term to the first thread:");
            int d = in.nextInt();
            System.out.println("Set PRIORITY (1-10) to the first thread:");
            int priority = in.nextInt();

            Thread thread = new Thread(new MThread(d, priority));

            System.out.println("Set term to the second thread:");
            d = in.nextInt();
            System.out.println("Set PRIORITY (1-10) to the second thread:");
            priority = in.nextInt();

            Thread thread2 = new Thread(new MThread(d, priority));

            System.out.println("Program started: ");
            thread.start();
            thread2.start();
            try {
                thread.join();
                thread2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("STOP");
            System.out.println("\nRESTARTED");
            MThread.setNumber(300);
        }
    }
}
