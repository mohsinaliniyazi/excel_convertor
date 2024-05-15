package com.exl_convertor.interview_questions.array_Program;

import java.util.Arrays;

public class FindMissingElement {

    public static void main(String[] args){
        int[] arr = {3, 3, 3, 5, 1};
        Arrays.sort(arr);
        for(int i=0; i<arr.length; i++){
            if(arr[i] == i+1){
                arr[i] = arr[i]*-1;
            }
        }
        printElement(arr);
    }

    private static void printElement(int[] arr) {
        System.out.print("Missing Element is - ");
        for(int i=0; i<arr.length; i++){
            if(arr[i] > 1){
                System.out.print(i+1+ " ");
            }
        }
    }
}
