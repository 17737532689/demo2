package com.example.demo.test;

public class digui {

    public static long fac(int n) {
        if (n > 1) {

            return (n * fac(n - 1));

        } else {
            return 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(fac(6));
    }
}
