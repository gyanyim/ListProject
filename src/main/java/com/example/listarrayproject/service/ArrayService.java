package com.example.listarrayproject.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.xml.bind.ValidationException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArrayService implements Validator {

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
}