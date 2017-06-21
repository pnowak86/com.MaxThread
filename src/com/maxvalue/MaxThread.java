package com.maxvalue;

import java.util.Random;

public class MaxThread extends Thread {
    private int lo, hi;
    private int[] arr;
    private int ans = 0;
    public static Random genarator = new Random();
    public MaxThread(int[] arr, int lo, int hi) {
        this.lo = lo;
        this.hi = hi;
        this.arr = arr;
    }

    @Override
    public void run() {
        for (int i=1; i<hi; i++) {
            if (ans < arr[i]) {
                ans = arr[i];
            }
        }

//        for (int i = lo; i < hi; i++) {
//            ans += arr[i];
//        }
    }


    public static int max(int[] arr) throws InterruptedException {
        int len = arr.length;
        int ans = 0;

        // Create and start 4 threads.
        MaxThread[] ts = new MaxThread[4];
        for (int i = 0; i < 4; i++) {
            ts[i] = new MaxThread(arr, (i * len) / 4, ((i + 1) * len / 4));
            ts[i].start();
        }

        // Wait for the threads to finish and max their results.
        for (int i = 0; i < 4; i++) {
            ts[i].join();
            if (ans < arr[i]) {
                ans = arr[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) throws InterruptedException {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = genarator.nextInt(200);
            System.out.println(arr[i]);
        }
        int max = max(arr);
        System.out.println("Max: " + max);
    }

}
