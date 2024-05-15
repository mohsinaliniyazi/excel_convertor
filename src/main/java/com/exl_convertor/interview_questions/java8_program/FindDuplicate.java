package com.exl_convertor.interview_questions.java8_program;

import java.util.*;
import java.util.stream.Collectors;

public class FindDuplicate {

    public static void main(String[] args){
        List<String> list = Arrays.asList("abc", "abc", "bcd", "bcd");
        Set<String> dset = new HashSet<>();
        List<String> duplicate = list.stream().filter(e -> !dset.add(e)).collect(Collectors.toList());
       // System.out.println(duplicate);


        List<Object> myList = List.of(0, 1, 1, 2, 3, 5, 6, 0, 0, 1, 5);
        HashSet<Object> uniqueItems = new HashSet<Object>();
        List<Object> duplicates = new ArrayList<Object>();

        for (Object item : myList) {
            if (!uniqueItems.add(item)) {
                duplicates.add(item);
            }
        }
        System.out.println(duplicates);
    }
}
