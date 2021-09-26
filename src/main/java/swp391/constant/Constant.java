package swp391.constant;

public class Constant {
    public static final String INSERT_ACCOUNT = "insert into account values(?,?,?,?,?,?,?,?)";
    public static final String UPDATE_ACCOUNT = "update account set " +
            "email = ?, firstname = ?, lastname = ?, birthday = ?, gender = ?, " +
            "phone_number = ?, address = ?, updatedDate = ? " +
            "where account_id = ?";
}
