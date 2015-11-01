package tmheo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tmheo.model.PersonRequest;
import tmheo.model.PersonResponse;
import tmheo.service.PersonService;

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

}
