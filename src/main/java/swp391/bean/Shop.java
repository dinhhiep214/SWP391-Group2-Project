package swp391.bean;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Shop {
    private int shopId;
    private int personId;
    private String shopName;
    private String status;
    private String address;
    private String description;
    private int rate;
    private Date createdDate;
    private Date updatedDate;
}
