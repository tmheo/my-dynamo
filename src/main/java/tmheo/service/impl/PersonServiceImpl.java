package tmheo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tmheo.entity.Person;
import tmheo.model.PersonResponse;
import tmheo.repository.PersonRepository;
import tmheo.service.PersonService;

/**
 * Created by taemyung on 2015. 10. 25..
 */
@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public PersonResponse createPerson(Person person) {

        log.debug("create person request : {}", person);

        personRepository.save(person);

        PersonResponse personResponse = new PersonResponse(person);

        log.debug("create person response : {}", personResponse);

        return personResponse;

    }

    @Override
    public PersonResponse getPerson(String id) {

        log.debug("get person request by id[{}]", id);

        PersonResponse personResponse = new PersonResponse(personRepository.findOne(id));

        log.debug("get person response by id[{}] : {}", id, personResponse);

        return personResponse;

    }

}
