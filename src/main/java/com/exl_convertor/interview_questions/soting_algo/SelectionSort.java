package com.exl_convertor.interview_questions.soting_algo;

public class SelectionSort {

    public static void main(String[] args){

        int[] arr = {800, 2, 300, 19};
        selectionSort(arr);
    }

    private static void selectionSort(int[] arr) {
        for(int i=0; i<arr.length-1; i++){
            int index = i;
            for(int j=i+1; j<arr.length; j++){
                if(arr[j] < arr[index]){
                    index = j;
                }
            }
            int smallestNumber = arr[index];
                arr[index] = arr[i];
            arr[i] = smallestNumber;
            printArrayNew(arr);
        }
    }

    private static void printArrayNew(int[] array) {
        for(int i=0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
