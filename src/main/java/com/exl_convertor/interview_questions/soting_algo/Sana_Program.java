package com.exl_convertor.interview_questions.soting_algo;

public class Sana_Program {

    public static void main(String[] args){

        int[] a ={800, 3, 900, 2};
        int temp = 0;
        for(int i=0; i<a.length; i++){

            for (int j=1; j<a.length; j++){

                if(a[j-1] > a[j]){
                    temp = a[j-1];
                    a[j-1] = a[j];
                    a[j] = temp;

                }
            }
        }

        for(int k=0; k<a.length; k++){
            System.out.print(a[k] +", ");
        }
    }

}
