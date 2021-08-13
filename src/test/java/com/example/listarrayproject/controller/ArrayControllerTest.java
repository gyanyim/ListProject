package com.example.listarrayproject.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
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
    void controllerListPostMapping() {
        given()
                .log()
                .all()
                .body(givenANestedList())
                .contentType(ContentType.JSON)
                .when()
                .post("lists/flatten")
                .then()
                .log()
                .all()
                .statusCode(201);
    }


    @Test
    public void controllerListPostMappingWithAssertion() {
        String listString = given()
                .log()
                .all()
                .body(givenANestedList())
                .contentType(ContentType.JSON)
                .when()
                .post("lists/flatten")
                .then()
                .log()
                .all()
                .statusCode(201)
                .assertThat()
                .extract().asString();

        List<Integer> listInteger = new ArrayList<>();
        String[] tmpArray = listString.split(",");

        // Cut the [ from the String
        tmpArray[0] = tmpArray[0].substring(1);

        // Cut the ] from the String
        tmpArray[tmpArray.length - 1] = tmpArray[tmpArray.length - 1].substring(0, 1);

        // Parse the number from String to Integer
        Arrays.asList(tmpArray).forEach(oneString -> listInteger.add(Integer.parseInt(oneString)));

        // Check the flattenedList numbers with the response numbers
        listInteger.forEach(oneInteger -> assertTrue(flattenedList().contains(oneInteger)));
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
}