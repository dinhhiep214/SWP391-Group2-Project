package swp391.bean;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Food {
    private int foodId;
    private int shopId;
    private String foodName;
    private String status;
    private String description;
    private Date createdDate;
    private Date updatedDate;
}
