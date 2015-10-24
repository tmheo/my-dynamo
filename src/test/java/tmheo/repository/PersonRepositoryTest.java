package tmheo.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tmheo.MyDynamoTestApplication;
import tmheo.model.Person;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by taemyung on 2015. 10. 24..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MyDynamoTestApplication.class)
@ActiveProfiles("test")
@Slf4j
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testSaveAndGetPerson() throws Exception {

        // Given
        Person person = new Person("test@test.com", "firstName", "lastName");

        // When
        personRepository.save(person);

        Person result = personRepository.findOne(person.getId());

        log.debug("person from dynamodb : {}", result);

        // Then
        assertThat(result, is(person));

    }

    @Test
    public void testFindAll() throws Exception {

        // Given
        for (int i = 0; i < 10; i++) {
            personRepository.save(new Person("test" + i + "@test.com", "firstName" + i, "lastName" + i));
        }

        // sort not work for findAll
        Pageable pageRequest = new PageRequest(0, 10);

        // When
        Page<Person> page = personRepository.findAll(pageRequest);

        log.debug("find all result : {}", page.getContent());

        // Then
        assertThat(page.getContent().isEmpty(), is(false));

    }

}
