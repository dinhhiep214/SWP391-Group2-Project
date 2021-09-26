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
    private String firstName;
    private String lastName;
    private String role;
    private Date birthDay;
    private boolean gender;
    private String phoneNumber;
    private String address;
    private String status;
    private Date createdDate;
    private Date updatedDate;

    public Account (){}

    public Account(int accountId, String email, String password, String firstName, String lastName, String role, Date birthDay,
                   boolean gender, String phoneNumber, String address, String status, Date createdDate, Date updatedDate) {
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.birthDay = birthDay;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Account(String email, String password, String firstName, String lastName, String role,
                   boolean gender, String status, Date createdDate) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.gender = gender;
        this.status = status;
        this.createdDate = createdDate;
    }
}
