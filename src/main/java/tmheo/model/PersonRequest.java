package tmheo.model;

import lombok.Data;
import tmheo.entity.Person;
import tmheo.util.BeanUtils;

import java.io.Serializable;

/**
 * Created by taemyung on 2015. 10. 25..
 */
@Data
public class PersonRequest implements Serializable {

    private String id;
    private String email;
    private String firstName;
    private String lastName;

    public Person convertToPerson() {

        Person person = new Person();

        BeanUtils.copyNotNullProperties(this, person);

        return person;

    }
}
