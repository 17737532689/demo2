package com.example.demo.test;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;
    private volatile boolean cancelled = false;

    public PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    public void run() {
        BigInteger p = BigInteger.ONE.ONE;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                queue.put(p = p.nextProbablePrime());
            } catch (InterruptedException e) {

                //这里接受中断异常，然后就可以退出线程
                System.out.println(this.isInterrupted());
                this.interrupt();
                System.out.println(this.isInterrupted());
            }
        }
    }

    public void cancel() {
        interrupt();
    }

    public static void main(String[] args) {
        BlockingQueue<BigInteger> primes = new ArrayBlockingQueue<BigInteger>(4);
        PrimeProducer brokenPrimeProducer = new PrimeProducer(primes);
        brokenPrimeProducer.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        brokenPrimeProducer.cancel();
        System.out.println(brokenPrimeProducer.queue.size());
    }
}