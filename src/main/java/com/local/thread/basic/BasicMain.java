package com.local.thread.basic;

public class BasicMain {
    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();
        Thread holder = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Holder acquired lock");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Holder: releasing lock");
            }
        }, "HolderThread");

        Thread waiter = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Waiter acquired lock");
            }
        }, "WaiterThread");

        holder.start();
        Thread.sleep(100);

        waiter.start();
        Thread.sleep(100);

        System.out.println("Waiter state: " + waiter.getState());
        holder.join();
        waiter.join();
    }
}
