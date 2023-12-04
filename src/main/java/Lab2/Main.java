package Lab2;

import Lab2.Task2.MultiplyMatricesThread;
import Lab2.Task3.SumAllElementsInMatrixThead;
import Lab2.Task3.SumElementsInRowsMatrix;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Thread> threadList;
        long startTime, endTime;
        int threadsAmount = 6;
        int N = 300;

        //Task1

        int threadsTask1 = Runtime.getRuntime().availableProcessors();
        System.out.println("Максимальна кількість потоків на пристрої: " + threadsTask1);
        Runnable printHelloWorldThread = () ->
                System.out.println(Thread.currentThread().getName() + ": Hello World!\n");

        for (int i = 0; i < threadsTask1; i++) {
            Thread printHelloWorld = new Thread(printHelloWorldThread);
            printHelloWorld.start();
        }
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Task2
        System.out.println("Task2: ");
        int [][] matrixA = new int[N][N];
        int [][] matrixB = new int[N][N];
        fillArrayWithRandom(matrixA);
        fillArrayWithRandom(matrixB);
        int [][] resultMatrix = new int[matrixA.length][matrixB[0].length];

        startTime = System.currentTimeMillis();
        threadList = new LinkedList<>();
        for (int i = 0; i < threadsAmount; i++) {
            Thread thread = new Thread(new MultiplyMatricesThread(matrixA, matrixB, resultMatrix,
                    i * resultMatrix.length/threadsAmount, (i+1) * resultMatrix.length/threadsAmount));
            thread.start();
            threadList.add(thread);
        }

        for (Thread t:threadList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("Multithreading: " + (endTime - startTime) + " milliseconds; Thread: " + threadsAmount);
        System.out.println("First row in resulted matrix:");
        for (int i = 0; i < resultMatrix[0].length; i++) {
            System.out.print(resultMatrix[0][i] + " ");
        }
        System.out.println();

        resultMatrix = new int[matrixA.length][matrixB[0].length];
        multiplyMatrices(matrixA, matrixB, resultMatrix);
        System.out.println("First row in resulted matrix with ONE thread to compare:");
        for (int i = 0; i < resultMatrix[0].length; i++) {
            System.out.print(resultMatrix[0][i] + " ");
        }
        System.out.println();

//        Task3 Part 1
        System.out.println("\nTask3 Part 1");
        int [][] matrixC = new int[N][N];
        int [] cRes = new int[matrixC.length];
        fillArrayWithRandom(matrixC);
        threadList = new LinkedList<>();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < threadsAmount; i++) {
            Thread thread = new Thread(new SumElementsInRowsMatrix(matrixC, i*matrixC.length/threadsAmount, (i+1)*matrixC.length/threadsAmount, cRes));
            thread.start();
            threadList.add(thread);
        }
        for (Thread t:threadList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        endTime = System.currentTimeMillis();
        System.out.println("Multithreading: " + (endTime - startTime) + " milliseconds; Thread: " + threadsAmount);
        for (int i = 0; i < cRes.length; i++) {
            System.out.print(cRes[i] + " ");
        }
        System.out.println();

        cRes = new int[matrixC.length];
        getSumElInRows(matrixC, cRes);
        System.out.println("Result with ONE thread to compare:");
        for (int i = 0; i < cRes.length; i++) {
            System.out.print(cRes[i] + " ");
        }


//        Task3 Part 2
        System.out.println("\n\nTask3 Part 2");
        int[][] matrixD = new int[N][N];
        fillArrayWithRandom(matrixD);
        threadList = new LinkedList<>();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < threadsAmount; i++) {
            Thread thread = new Thread(new SumAllElementsInMatrixThead(matrixD, i*matrixD.length/threadsAmount, (i+1)*matrixD.length/threadsAmount));
            thread.start();
            threadList.add(thread);
        }
        for (Thread t:threadList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("Multithreading: " + (endTime - startTime) + " milliseconds; Thread: " + threadsAmount);
        System.out.println(SumAllElementsInMatrixThead.getTotalSum());

        int sum = getSumAllElInMatrix(matrixD);
        System.out.println("Sum with ONE thread:");
        System.out.println(sum);
    }

    private static void fillArrayWithRandom(int[][] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = (int) (Math.random() * 100);
            }
        }
    }

    private static void multiplyMatrices(int[][] matrixA, int[][] matrixB, int[][] matrixRes) {
        for (int i = 0; i < matrixB.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                for (int k = 0; k < matrixB.length; k++)
                    matrixRes[i][j] += matrixA[i][k] * matrixB[k][j];
            }
        }
    }

    private static void getSumElInRows(int[][] matrix, int[] sums) {
        for (int i = 0; i < matrix.length; i++) {
            int sum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }
            sums[i] = sum;
        }
    }

    private static int getSumAllElInMatrix(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }
}
