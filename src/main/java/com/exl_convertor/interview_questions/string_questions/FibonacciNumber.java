package com.exl_convertor.interview_questions.string_questions;

public class FibonacciNumber {

    public static void main(String[] args){
        int count = 10;
        for (int i=0; i<count; i++){
            System.out.print(fibonacci_Number(i) + " ");
        }
    }

    private static int fibonacci_Number(int i) {
        if (i <= 1){
            return i;
        }
        int one = fibonacci_Number(i-1);
        int two = fibonacci_Number( i-2);
        return one + two;
    }
}
