package com.example.listarrayproject.controller;

import com.example.listarrayproject.service.ArrayService;
import com.example.listarrayproject.service.ValidatorClass;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.List;

import static java.util.Arrays.asList;

@RequiredArgsConstructor
@RestController
@RequestMapping("lists")
public class ArrayController {

    @Autowired
    private final ArrayService arrayService;

    @InitBinder("nestedList")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new ValidatorClass());
    }

    @PostMapping("/flatten")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Integer> createFlattenList(@RequestBody List<List<Integer>> nestedList) throws ValidationException {
        return arrayService.createFlattenList(nestedList);
    }


    @GetMapping("/flatten1")
    public List<Integer> getFlattenedList(@Valid @RequestParam List<List<Integer>> nestedList) throws ValidationException {
        return arrayService.getFlattenedList(nestedList);
    }

    @GetMapping("/flatten2")
    public List<Integer> getFlattenedList(@RequestParam List<Integer> listOne,
                                          @RequestParam List<Integer> listTwo,
                                          @RequestParam List<Integer> listThree,
                                          @RequestParam List<Integer> listFour) throws ValidationException {
        List<List<Integer>> nestedList = asList(listOne, listTwo, listThree, listFour);
        return arrayService.getFlattenedList(nestedList);
    }

}
