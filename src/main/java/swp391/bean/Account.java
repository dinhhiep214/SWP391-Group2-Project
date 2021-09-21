package swp391.bean;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Account {
    private int accountId;
    private int personId;
    private int roleId;
    private String phoneNumber;
    private String email;
    private String password;
    private String status;
    private Date createdDate;
    private Date updatedDate;
}
