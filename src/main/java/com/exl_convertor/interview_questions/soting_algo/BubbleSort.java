package com.exl_convertor.interview_questions.soting_algo;

public class BubbleSort {

    public static void main(String[] args){
        int[] arr = {800, 2, 300, 19};
        printBubbleSort(arr);
    }

    private static void printBubbleSort(int[] arr) {
        int n = arr.length;
        int temp = 0;

        for(int i=0; i<n; i++){

            for(int j=1; j<n-i; j++){

                if(arr[j-1] > arr[j]){
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;

                    printArrayNumber(arr);
                }
            }
        }
    }

    private static void printArrayNumber(int[] array) {
        for(int i=0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
