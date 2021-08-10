package com.example.listarrayproject.service;

import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArrayService {

    public List<Integer> getFlattenedList(List<List<Integer>> nestedList) throws ValidationException {
        validateList(nestedList);
        return makeFlattenedList(nestedList);
    }

    private void validateList(List<List<Integer>> nestedList) throws ValidationException {
        if (nestedList.isEmpty()) {
            throw new ValidationException("The nestedList is empty!");
        }

        List<Integer> flattenedList = makeFlattenedList(nestedList);

        flattenedList.forEach(e -> {
            if (e == null) {
                throw new NullPointerException("The nestedList contains null!");
            }
        });
    }

    private List<Integer> makeFlattenedList(List<List<Integer>> nestedList) {
        return nestedList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}