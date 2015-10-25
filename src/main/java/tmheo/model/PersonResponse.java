package tmheo.model;

import lombok.Data;
import tmheo.entity.Person;
import tmheo.util.BeanUtils;

import java.io.Serializable;

/**
 * Created by taemyung on 2015. 10. 25..
 */
@Data
public class PersonResponse implements Serializable {

    private String id;
    private String email;
    private String firstName;
    private String lastName;

    public PersonResponse() {
    }

    public PersonResponse(Person person) {
        BeanUtils.copyNotNullProperties(person, this);
    }

}
