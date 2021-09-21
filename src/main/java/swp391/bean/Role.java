package swp391.bean;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Role {
    private int roleId;
    private String name;
    private Date createdDate;
    private Date updatedDate;
}
