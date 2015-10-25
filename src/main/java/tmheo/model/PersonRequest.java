package tmheo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tmheo.entity.Person;
import tmheo.util.BeanUtils;

import java.io.Serializable;

/**
 * Created by taemyung on 2015. 10. 25..
 */
@Data
public class PersonRequest implements Serializable {

    @ApiModelProperty(position = 1, notes = "Email Address", required = true)
    private String email;

    @ApiModelProperty(position = 2, notes = "First Name", required = true)
    private String firstName;

    @ApiModelProperty(position = 3, notes = "Last Name", required = true)
    private String lastName;

    public Person convertToPerson() {

        Person person = new Person();

        BeanUtils.copyNotNullProperties(this, person);

        return person;

    }
}
