package com.exl_convertor.interview_questions.string_program;

public class FindDuplicate {

    static String[] arr = {"bcd", "abd", "jude", "bcd", "oiu", "gzw", "oiu"};
    public static void main(String[] args){
        for(int i=0; i<arr.length-1; i++){
            for(int j=i+1; j<arr.length; j++){
                if(arr[i].equals(arr[j])){
                    System.out.print(arr[i] + ", ");
                }
            }
        }
    }
}
