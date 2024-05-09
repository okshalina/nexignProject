package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ChangeAbonentTariff {
    String baseURI = "http://localhost:8765/api/";

    @Test
    public void testUpdateMonthlyTariffToClassicPositive() {

        String msisdn = "79002093745";
        int newTariffId = 11;
        String newConnectionDate = "2025-01-01";

        RestAssured.baseURI = baseURI;
        given()
                .contentType(ContentType.JSON)
                .formParam("tariffId", newTariffId)
                .formParam("connectionDate", newConnectionDate)
                .when()
                .patch("/abonents/" + msisdn + "/tariff")
                .then()
                .assertThat().statusCode(200)
                .body("connectionDate", equalTo(newConnectionDate))
                .body("tariffId", equalTo(newTariffId));
    }

    @Test
    public void testUpdateMonthlyTariffToClassicInvalidMsisdn() {

        int newTariffId = 11;
        String newConnectionDate = "2025-01-01";
        String msisdn = "79002093746";

        RestAssured.baseURI = baseURI;
        given()
                .contentType(ContentType.JSON)
                .formParam("tariffId", newTariffId)
                .formParam("connectionDate", newConnectionDate)
                .when()
                .patch("/abonents/" + msisdn + "/tariff")
                .then()
                .assertThat().statusCode(404);
    }
}
