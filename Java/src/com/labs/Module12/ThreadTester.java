package com.labs.Module12;

public class ThreadTester {
    public static void main(String[] args) {
        Runnable prog = new PrintMe();
        Thread t1 = new Thread(prog);
        Thread t2 = new Thread(prog);
        Thread t3 = new Thread(prog);

        t1.setName("Thread 1 - John");
        t2.setName("Thread 2 - Jane");
        t3.setName("Thread 3 - Jake");

        t3.setPriority(Thread.MAX_PRIORITY);
        t1.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
        t3.start();
    }
}
