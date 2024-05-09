package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddNewAbonentTest {
    String baseURI = "http://localhost:8765/api/";

    @Test
    public void testAddNewMonthlyAbonentPositive() {


        String msisdn = "79002093745";
        int tariffId = 12;
        String connectionDate = "2024-01-01";
        int money = 100;

        RestAssured.baseURI = baseURI;

        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"msisdn\": \"" + msisdn + "\",\n" +
                        "  \"tariffId\": " + tariffId + ",\n" +
                        "  \"connectionDate\": \"" + connectionDate + "\",\n" +
                        "  \"money\": " + money + "\n" +
                        "}")
                .when()
                .post("/abonents")
                .then()
                .assertThat().statusCode(201)
                .body("connectionDate", equalTo(connectionDate))
                .body("tariffId", equalTo(tariffId))
                .body("money", equalTo(money));
    }

    @Test
    public void testAddNewClassicAbonentNegative() {


        String msisdn = "790020937451";
        int tariffId = 11;
        String connectionDate = "2024-12-28";
        int money = 100;

        RestAssured.baseURI = baseURI;

        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"msisdn\": \"" + msisdn + "\",\n" +
                        "  \"tariffId\": " + tariffId + ",\n" +
                        "  \"connectionDate\": \"" + connectionDate + "\",\n" +
                        "  \"money\": " + money + "\n" +
                        "}")
                .when()
                .post("/abonents")
                .then()
                .assertThat().statusCode(400);
    }
}



