package swp391.constant;

public class Constant {
    public static final String INSERT_PERSON = "INSERT INTO PERSON VALUES(?,?,?,?,?,?,?,?,?)";
    public static final String UPDATE_PERSON = "UPDATE PERSON SET " +
            "firstname = ?, lastname = ?, birthday = ?, gender = ?, " +
            "email = ?, phone_number = ?, address = ?, updatedDate = ? " +
            "where person_id = ?";
}
