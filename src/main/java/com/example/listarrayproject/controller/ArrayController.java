package com.example.listarrayproject.controller;

import com.example.listarrayproject.service.ArrayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("test")
public class ArrayController {

    private final ArrayService arrayService;

    @GetMapping("/list")
    public List<Integer> getFlattenList(@RequestBody List<List<Integer>> nestedList) throws ValidationException {
        return arrayService.getFlattenList(nestedList);
    }
}
