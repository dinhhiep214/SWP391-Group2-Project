package swp391.bean;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Topic {
    private int topicId;
    private String title;
    private String status;
    private String content;
    private int rate;
    private Date createdDate;
    private Date updatedDate;
}
