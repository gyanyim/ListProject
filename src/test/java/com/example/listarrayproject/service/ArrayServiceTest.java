package com.example.listarrayproject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.bind.ValidationException;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ArrayServiceTest {

    private static final List<List<Integer>> ORIGINAL_LIST = asList(
            asList(1, 3, 5),
            asList(2, 11, 23),
            Collections.singletonList(41),
            asList(4, 9, 0));

    private static final List<List<Integer>> EMPTY_LIST = Collections.emptyList();

    private static final List<List<Integer>> LIST_WITH_NULL = asList(
            asList(null, 3, 5),
            asList(2, null, 23),
            Collections.singletonList(null),
            asList(4, 9, 0));

    private ArrayService arrayService;

    @BeforeEach
    void setUp() {
        this.arrayService = new ArrayService();
    }

    @Test
    void givenANestedList_whenGetFlattenedListMethod_thenReturnAFlattenList() throws ValidationException {
        List<Integer> flattenList = arrayService.getFlattenedList(ORIGINAL_LIST);
        List<Integer> expectedList = asList(1, 3, 5, 2, 11, 23, 41, 4, 9, 0);
        assertEquals(expectedList, flattenList);
    }

    @Test
    void givenANestedListWithoutElement_whenGetFlattenedListMethod_thenReturnValidationException() {
        Exception thrown = assertThrows(ValidationException.class, () -> arrayService.getFlattenedList(EMPTY_LIST));
        assertThat(thrown, notNullValue());
        assertThat(thrown.getMessage(), is("The nestedList is empty!"));
    }

    @Test
    void givenANestedListWithNullElement_whenGetFlattenedListMethod_thenReturnValidationException()  {
        Exception thrown = assertThrows(NullPointerException.class, () -> arrayService.getFlattenedList(LIST_WITH_NULL));
        assertThat(thrown, notNullValue());
        assertThat(thrown.getMessage(), is("The nestedList contains null!"));
    }
}