package tmheo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tmheo.entity.Person;
import tmheo.model.PersonRequest;
import tmheo.model.PersonResponse;
import tmheo.service.PersonService;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by taemyung on 2015. 10. 25..
 */
@RestController
@RequestMapping(value = "/v1/persons", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@Api(value = "person controller", description = "Person REST API")
@Slf4j
public class PersonController {

    @Autowired
    private PersonService personService;

    @ApiOperation(value = "Create a person", response = PersonResponse.class)
    @RequestMapping(method = RequestMethod.POST)
    public PersonResponse createPerson(@RequestBody PersonRequest personRequest) {

        log.debug("create person request : {}", personRequest);

        PersonResponse personResponse = personService.createPerson(personRequest.convertToPerson());

        log.debug("create person response : {}", personResponse);

        return personResponse;

    }

    @ApiOperation(value = "Get a person", response = PersonResponse.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PersonResponse getPerson(@PathVariable String id) {

        log.debug("get person request by id[{}]", id);

        PersonResponse personResponse = personService.getPerson(id);

        log.debug("get person response by id[{}] : {}", id, personResponse);

        return personResponse;

    }

    @ApiOperation(value = "Update a person", response = PersonResponse.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public PersonResponse updatePerson(@PathVariable String id, @RequestBody PersonRequest personRequest) {

        log.debug("update person request by id[{}] : {}", id, personRequest);

        Person person = personRequest.convertToPerson();
        person.setId(id);

        PersonResponse personResponse = personService.updatePerson(person);

        log.debug("update person response by id[{}] : {}", id, personResponse);

        return personResponse;

    }

    @ApiOperation(value = "Delete a person", response = PersonResponse.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletePerson(@PathVariable String id, HttpServletResponse httpServletResponse) {

        log.debug("delete person request by id[{}]", id);

        boolean result = personService.deletePerson(id);

        log.debug("delete person response by id[{}] : {}", id, result);

        if (result) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

}
