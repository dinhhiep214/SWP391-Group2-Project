package swp391.bean;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class FoodComment {
    private int foodCommentId;
    private int foodId;
    private String status;
    private String content;
    private int rate;
    private Date createdDate;
    private Date updatedDate;
}
