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
public class PersonResponse implements Serializable {

    @ApiModelProperty(position = 1, notes = "Id", required = true)
    private String id;

    @ApiModelProperty(position = 2, notes = "Email Address", required = true)
    private String email;

    @ApiModelProperty(position = 3, notes = "First Name", required = true)
    private String firstName;

    @ApiModelProperty(position = 4, notes = "Last Name", required = true)
    private String lastName;

    public PersonResponse() {
    }

    public PersonResponse(Person person) {
        BeanUtils.copyNotNullProperties(person, this);
    }

}
