package tmheo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tmheo.entity.Person;
import tmheo.model.PersonResponse;
import tmheo.repository.PersonRepository;
import tmheo.service.PersonService;
import tmheo.util.BeanUtils;

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

    @Override
    public PersonResponse updatePerson(Person person) {

        log.debug("update person request : {}", person);

        Person dbPerson = personRepository.findOne(person.getId());

        BeanUtils.copyNotNullProperties(person, dbPerson);

        personRepository.save(dbPerson);

        PersonResponse personResponse = new PersonResponse(dbPerson);

        log.debug("update person response : {}", personResponse);

        return personResponse;

    }

    @Override
    public boolean deletePerson(String id) {

        log.debug("delete person request by id[{}]", id);

        boolean result = false;

        if (personRepository.exists(id)) {
            personRepository.delete(id);
            result = true;
        }

        log.debug("delete person response by id[{}] : {}", id, result);

        return result;

    }

}
