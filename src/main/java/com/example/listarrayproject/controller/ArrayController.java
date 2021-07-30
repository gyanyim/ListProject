package com.example.listarrayproject.controller;

import com.example.listarrayproject.service.ArrayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

import static java.util.Arrays.asList;

@RequiredArgsConstructor
@RestController
@RequestMapping("test")
public class ArrayController {

    @Autowired
    private final ArrayService arrayService;

    @GetMapping("/list1")
    public List<Integer> getFlattenedList(@RequestBody List<List<Integer>> nestedList) throws ValidationException {
        return arrayService.getFlattenedList(nestedList);
    }

    @GetMapping("/list2")
    public List<Integer> getFlattenedList(@RequestParam List<Integer> listOne,
                                          @RequestParam List<Integer> listTwo,
                                          @RequestParam List<Integer> listThree,
                                          @RequestParam List<Integer> listFour) throws ValidationException {
        List<List<Integer>> nestedList = asList(listOne, listTwo, listThree, listFour);
        return arrayService.getFlattenedList(nestedList);
    }
}
