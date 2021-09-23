package swp391.bean;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class TopicComment {
    private int topicCommentId;
    private int topicId;
    private String status;
    private String content;
    private int rate;
    private Date createdDate;
    private Date updatedDate;
}
