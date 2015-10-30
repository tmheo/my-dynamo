package tmheo.controller;

import com.jayway.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.Test;
import tmheo.model.PersonRequest;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 * Created by taemyung on 2015. 10. 25..
 */
@Slf4j
public class PersonControllerTest extends AbstractControllerTest {

    @Test
    public void testCreatePerson() throws Exception {

        // Given
        PersonRequest personRequest = new PersonRequest();

        personRequest.setEmail("test@test.com");
        personRequest.setFirstName("firstName");
        personRequest.setLastName("lastName");

        given()
                .log().all()
                .body(personRequest)
                .contentType(ContentType.JSON)
                        // When
                .when()
                .post("/v1/persons")
                        // Then
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body("id", notNullValue())
                .body("email", is(personRequest.getEmail()));

    }

}
