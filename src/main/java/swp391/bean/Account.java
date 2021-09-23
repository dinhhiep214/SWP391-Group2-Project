package swp391.bean;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Account {
    private int accountId;
    private String email;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private Boolean gender;
    private String phoneNumber;
    private String address;
    private String status;
    private Date createdDate;
    private Date updatedDate;
}
