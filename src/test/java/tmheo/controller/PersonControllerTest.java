package tmheo.controller;

import com.jayway.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import tmheo.MyDynamoTestApplication;
import tmheo.model.PersonRequest;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 * Created by taemyung on 2015. 10. 25..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MyDynamoTestApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
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
                .post("/persons")
                        // Then
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body("id", notNullValue())
                .body("email", is(personRequest.getEmail()));

    }

}
