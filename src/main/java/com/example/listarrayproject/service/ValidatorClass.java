package com.example.listarrayproject.service;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

public class ValidatorClass implements Validator {

    @Override
    public boolean supports(Class aClass) {
        return List.class.equals(aClass);
    }

    @Override
    public void validate(Object nestedList, Errors err) {
        ValidationUtils.rejectIfEmpty(err, "nestedList", "nestedList.empty");

//        List<Integer> flattenedList = makeFlattenedList((List<List<Integer>>) nestedList);
//
//        flattenedList.forEach(e -> {
//            if (e == null) {
//                err.rejectValue("nestedList", "The list contains null value.");
//            }
//        });
    }

}
