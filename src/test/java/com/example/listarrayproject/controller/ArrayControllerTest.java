package com.example.listarrayproject.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ArrayControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void beforeAll() {
        RestAssured.port = port;
    }

    @Test
    void controllerListTest() {
        String nestedListResult = given().log().all()
                .param("nestedList", givenANestedListString())
                .when()
                .get("lists/flatten2")
                .then().log().all()
                .statusCode(200)
                .extract().asString();

    }

    private List<Integer> flattenedList() {
        return asList(1, 3, 5, 2, 11, 23, 41, 4, 9, 0);
    }

    private List<List<Integer>> givenANestedList() {
        return asList(
                asList(1, 3, 5),
                asList(2, 11, 23),
                Collections.singletonList(41),
                asList(4, 9, 0));
    }

    private String givenANestedListString() {
        return "1, 4, 6, 6, 7";

    }

}