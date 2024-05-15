package com.exl_convertor.interview_questions.array_Program;

import java.util.ArrayList;
import java.util.List;

public class DuplicateArrayFind {
    // find duplicate
    public static void main(String[] args){
        int[] arr = {10, 10, 20, 20, 30, 40};
        ArrayList<Integer> newList = new ArrayList<>();
        for(int i=0; i<arr.length-1; i++){
            for(int j=i+1; j<arr.length; j++){
                if(arr[i] == arr[j]){
                    newList.add(arr[j]);
                }
            }
        }
        System.out.println(List.of(newList));
    }
}
