package com.example.listarrayproject.service;

import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArrayService {

    public static List<Integer> getFlattenList(List<List<Integer>> nestedList) throws ValidationException {
        validateList(nestedList);
        return nestedList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private static void validateList(List<List<Integer>> nestedList) throws ValidationException {
       if (nestedList.isEmpty()) {
           throw new ValidationException("The nestedList is empty!");
       }
       if (nestedList.stream().iterator().next().contains(null) ) {
            throw new NullPointerException("The nestedList contains null!");
        }
    }
}