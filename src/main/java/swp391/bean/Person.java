package swp391.bean;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Person {
    private int personId;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private Boolean gender;
    private String email;
    private String phoneNumber;
    private String address;
    private Date createdDate;
    private Date updatedDate;
}
