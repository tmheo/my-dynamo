package tmheo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tmheo.model.PersonRequest;
import tmheo.model.PersonResponse;
import tmheo.service.PersonService;

/**
 * Created by taemyung on 2015. 10. 25..
 */
@RestController
@RequestMapping(value = "/persons")
@Slf4j
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.POST)
    public PersonResponse createPerson(@RequestBody PersonRequest personRequest) {

        log.debug("create person request : {}", personRequest);

        PersonResponse personResponse = personService.createPerson(personRequest.convertToPerson());

        log.debug("create person response : {}", personResponse);

        return personResponse;

    }

}
