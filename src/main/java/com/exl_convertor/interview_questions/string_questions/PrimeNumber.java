package com.exl_convertor.interview_questions.string_questions;

public class PrimeNumber {

    public static void main(String[] args){
        int number_checked = 19;
        boolean checkedNumber = checkPrimeNumber(number_checked);
        System.out.println(checkedNumber);
    }

    private static boolean checkPrimeNumber(int n){
        if(n == 0 || n == 1){
            return false;
        }
        if(n == 2){
            return true;
        }
        for (int i=2; i <= n/2; i++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }
}
