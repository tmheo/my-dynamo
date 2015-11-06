package tmheo.controller;

import com.jayway.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tmheo.model.PersonRequest;
import tmheo.model.PersonResponse;
import tmheo.service.PersonService;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 * Created by taemyung on 2015. 10. 25..
 */
@Slf4j
public class PersonControllerTest extends AbstractControllerTest {

    @Autowired
    private PersonService personService;

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

    @Test
    public void testGetPerson() throws Exception {

        // Given
        PersonRequest personRequest = new PersonRequest();

        personRequest.setEmail("test@test.com");
        personRequest.setFirstName("firstName");
        personRequest.setLastName("lastName");

        PersonResponse personResponse = personService.createPerson(personRequest.convertToPerson());

        given()
                .log().all()
                .contentType(ContentType.JSON)
                        // When
                .when()
                .get("/v1/persons/" + personResponse.getId())
                        // Then
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(personResponse.getId()))
                .body("email", is(personResponse.getEmail()));

    }

    @Test
    public void testUpdatePerson() throws Exception {

        // Given
        PersonRequest personRequest = new PersonRequest();

        personRequest.setEmail("test@test.com");
        personRequest.setFirstName("firstName");
        personRequest.setLastName("lastName");

        PersonResponse personResponse = personService.createPerson(personRequest.convertToPerson());

        personRequest.setEmail("test_updated@test.com");
        personRequest.setFirstName("updated firstName");
        personRequest.setLastName("updated lastName");

        given()
                .log().all()
                .body(personRequest)
                .contentType(ContentType.JSON)
                        // When
                .when()
                .put("/v1/persons/" + personResponse.getId())
                        // Then
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(personResponse.getId()))
                .body("email", is(personRequest.getEmail()));

    }

    @Test
    public void testDeletePerson() throws Exception {

        // Given
        PersonRequest personRequest = new PersonRequest();

        personRequest.setEmail("test@test.com");
        personRequest.setFirstName("firstName");
        personRequest.setLastName("lastName");

        PersonResponse personResponse = personService.createPerson(personRequest.convertToPerson());

        given()
                .log().all()
                .contentType(ContentType.JSON)
                        // When
                .when()
                .delete("/v1/persons/" + personResponse.getId())
                        // Then
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NO_CONTENT);

    }

}
