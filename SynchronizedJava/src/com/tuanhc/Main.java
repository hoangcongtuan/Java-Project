package com.tuanhc;

class Sync {
    void display() {
        for(int i = 1; i < 10; i+= 2) {
            System.out.println(i + "");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MultiThread extends Thread {
    static Sync sync = new Sync();
    @Override
    public void run() {
        synchronized (sync) {
            sync.display();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MultiThread threadA = new MultiThread();
        MultiThread threadB = new MultiThread();

        threadA.start();
        threadB.start();
    }
}
