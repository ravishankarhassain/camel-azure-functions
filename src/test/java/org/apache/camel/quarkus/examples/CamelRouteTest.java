/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.quarkus.examples;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class CamelRouteTest
{
    private static String requestBody = "{\"name\" : \"Ravishankar\"}";
    private static String responseBody = "Hello Ravishankar ! How are you? from GreetService";

    @BeforeAll
    public static void setup() {
        RestAssured.registerParser("text/plain", Parser.TEXT);
    }

    @Test
    public void testGreetPost() {
        
        given()
            .body(requestBody)
            .header("Content-Type", "application/json")
            .header("Accept", "text/plain")
            .when().post("/greet")
            .then()
            .assertThat()
            .statusCode(200)
            .body(containsString(responseBody));

    }
}