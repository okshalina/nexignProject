package org.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TopUpAbonentBalance {

    String baseURI = "http://localhost:8765/api/";

    @Test
    public void testTopUpAbonentBalance() {

        RestAssured.baseURI = baseURI;

        String msisdn = "79002093745";
        int newMoneyValue = 100;

        given()
                .contentType(ContentType.JSON)
                .body(newMoneyValue)
                .when()
                .patch("/abonents/" + msisdn + "/money")
                .then()
                .assertThat().statusCode(200)
                .body("money", equalTo(newMoneyValue));
    }
}
