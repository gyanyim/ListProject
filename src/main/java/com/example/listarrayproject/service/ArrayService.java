package com.example.listarrayproject.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArrayService implements Validator {

    @Override
    public boolean supports(Class aClass) {
        return List.class.equals(aClass);
    }

    @Override
    public void validate(Object nestedList, Errors err) {
        ValidationUtils.rejectIfEmpty(err, "nestedList", "nestedList.empty");

        List<Integer> flattenedList = makeFlattenedList((List<List<Integer>>) nestedList);

        flattenedList.forEach(e -> {
            if (e == null) {
                err.rejectValue("nestedList", "The list contains null value.");
            }
        });
    }

    public List<Integer> getFlattenedList(List<List<Integer>> nestedList) {
        return makeFlattenedList(nestedList);
    }

    private List<Integer> makeFlattenedList(List<List<Integer>> nestedList) {
        return nestedList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }



}
