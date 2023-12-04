package Lab1;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MThread implements Runnable{

    private static Integer number = 250;
    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(false);
    private static boolean interrupt = false;

    private int dn, priority;


    public MThread(int dn, int priority) {
        this.dn = dn;
        this.priority = priority;
    }

    public static void setNumber(int number) {
        MThread.number = number;
        MThread.interrupt = false;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        readWriteLock.readLock().lock();
        int curNum = number;
        readWriteLock.readLock().unlock();

        while (curNum > 0 && curNum < 600 && !interrupt) {
            readWriteLock.writeLock().lock();
            number += dn;
            curNum = number;
            System.out.println(Thread.currentThread().getName() + "; term = " + this.dn + " priority = " + Thread.currentThread().getPriority() + "  : " + curNum);
            readWriteLock.writeLock().unlock();
        }
        interrupt = true;

    }
}
