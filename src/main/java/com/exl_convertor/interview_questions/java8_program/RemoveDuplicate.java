package com.exl_convertor.interview_questions.java8_program;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicate {

    public static void main(String[] args){
        List<String> listOfStrings = Arrays.asList("Java", "Python", "C#", "Java", "Kotlin", "Python");

        List<String> list = listOfStrings.stream().distinct().collect(Collectors.toList());
        System.out.println(Arrays.asList(list));
    }
}
