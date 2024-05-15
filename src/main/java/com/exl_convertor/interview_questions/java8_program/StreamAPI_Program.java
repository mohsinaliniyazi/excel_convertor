package com.exl_convertor.interview_questions.java8_program;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamAPI_Program {

    public static void main(String[] args){
        List<Integer> listOfIntegers = Arrays.asList(71, 18, 42, 21, 67, 32, 95, 14, 56, 87);

        Map<Boolean, List<Integer>> map = listOfIntegers.stream().collect(Collectors.partitioningBy(i -> i%2==0));
        for(Map.Entry<Boolean, List<Integer>> entry : map.entrySet()){
            System.out.println();

            List<Integer> list = entry.getValue();
            System.out.println(List.of(list));
        }
    }
}
