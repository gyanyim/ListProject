package com.example.listarrayproject.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Service
public class ArrayService {

    // NestedList with Integers
    public List<Integer> getFlattenedList(List<List<Integer>> nestedList) throws ValidationException {
        validateIntegerList(nestedList);
        return makeFlattenedList(nestedList);
    }

    private void validateIntegerList(List<List<Integer>> nestedList) throws ValidationException {
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

    // NestedList with String
    public List<Integer> getFlattenedListString(String nestedString) throws ValidationException {
        validateStringList(nestedString);
        return makeFlattenedListString(nestedString);
    }

    private void validateStringList(String nestedString) throws ValidationException {
        if (!StringUtils.hasText(nestedString)) {
            throw new ValidationException("The nestedList is empty or null!");
        }

        Stack<String> tmpStack = new Stack();

        for (int i = 0; i < nestedString.length(); i++) {
            if (nestedString.charAt(i) == '(') {
                tmpStack.push(nestedString.substring(i, i + 1));
            }
            if (nestedString.charAt(i) == ')') {
                if (tmpStack.isEmpty()) {
                    throw new ValidationException("The nestedList contains wrong parentheses!");
                } else {
                    tmpStack.pop();
                }
            }
        }
        if (!tmpStack.isEmpty()) {
            throw new ValidationException("The nestedList contains wrong parentheses!");
        }
    }

    private List<Integer> makeFlattenedListString(String nestedString) {
        List<Integer> resultList = new ArrayList<>();

        for (int i = 0; i < nestedString.length() - 1; i++) {
            if (nestedString.charAt(i) != '(' || nestedString.charAt(i) == ')') {
                resultList.add(Integer.parseInt(nestedString.substring(i, i + 1)));
            }
        }
        return resultList;
    }

}
