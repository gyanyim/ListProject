package com.example.listarrayproject.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.bind.ValidationException;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ArrayServiceTest {

    public static final List<List<Integer>> ORIGINAL_LIST = asList(
            asList(1, 3, 5),
            asList(2, 11, 23),
            Collections.singletonList(41),
            asList(4, 9, 0));

    public static final List<List<Integer>> EMPTY_LIST = Collections.emptyList();

    public static final List<List<Integer>> LIST_WITH_NULL = asList(
            asList(null, 3, 5),
            asList(2, 11, 23),
            Collections.singletonList(null),
            asList(4, 9, 0));

    @Mock
    private ArrayService arrayService;

    @Test
    public void givenANestedList_whenGetFlattenListMethod_thenReturnAFlattenList() throws ValidationException {
        List<Integer> flattenList = arrayService.getFlattenList(ORIGINAL_LIST);
        List<Integer> expectedList = asList(1, 3, 5, 2, 11, 23, 41, 4, 9, 0);

        assertEquals(expectedList, flattenList);
    }

    @Test
    public void givenANestedListWithoutElement_whenGetFlattenListMethod_thenReturnValidationException() {
        assertThrows(ValidationException.class, () -> arrayService.getFlattenList(EMPTY_LIST));
    }

    @Test
    public void givenANestedListWithNullElement_whenGetFlattenListMethod_thenReturnValidationException() {
        assertThrows(NullPointerException.class, () -> arrayService.getFlattenList(LIST_WITH_NULL));
    }
}