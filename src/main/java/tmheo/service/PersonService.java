package tmheo.service;

import tmheo.entity.Person;
import tmheo.model.PersonResponse;

/**
 * Created by taemyung on 2015. 10. 25..
 */
public interface PersonService {

    PersonResponse createPerson(Person person);

    PersonResponse getPerson(String id);

}
