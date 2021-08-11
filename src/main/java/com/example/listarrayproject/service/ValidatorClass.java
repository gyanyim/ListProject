package com.example.listarrayproject.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.xml.bind.ValidationException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ValidatorClass implements Validator {

    @Override
    public boolean supports(Class aClass) {
        return List.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) throws NullPointerException {
        ValidationUtils.rejectIfEmpty(errors, "nestedList", "nestedList.empty");
        List<List<Integer>> nestedList = (List<List<Integer>>) target;

        if (nestedList.isEmpty()) {
            errors.rejectValue("nestedList", "NestedList is empty!");
            try {
                throw new ValidationException("NestedList is empty!");
            } catch (ValidationException e) {
                e.printStackTrace();
            }
        }

        List<Integer> flattenedList = makeFlattenedList((List<List<Integer>>) nestedList);

        flattenedList.forEach(e -> {
            if (e == null) {
                errors.rejectValue("nestedList", "The list contains null value.");
                throw new NullPointerException("The list contains null value.");
            }
        });
    }

    private List<Integer> makeFlattenedList(List<List<Integer>> nestedList) {
        return nestedList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
