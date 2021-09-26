package swp391.dao;

import swp391.bean.Account;
import swp391.constant.Constant;
import swp391.jdbc.MySQLConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AccountDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private CallableStatement callableStatement;
    private Statement statement;
    private ResultSet resultSet;

    public boolean save(Account account) throws Exception {
        connection = new MySQLConnection().getConnection();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        try{
            preparedStatement = connection.prepareStatement(Constant.INSERT_ACCOUNT);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getFirstName());
            preparedStatement.setString(4, account.getLastName());
            preparedStatement.setDate(5, account.getBirthDay());
            preparedStatement.setBoolean(6, account.getGender());
            preparedStatement.setString(7, account.getRole());
            preparedStatement.setString(8, account.getPhoneNumber());
            preparedStatement.setString(9, account.getAddress());
            preparedStatement.setString(10, "active");
            preparedStatement.setDate(11, Date.valueOf(dtf.format(localDate)));

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public boolean update(Account account) throws Exception {
        connection = new MySQLConnection().getConnection();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        try {
            preparedStatement = connection.prepareStatement(Constant.UPDATE_ACCOUNT);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getFirstName());
            preparedStatement.setString(3, account.getLastName());
            preparedStatement.setDate(4, account.getBirthDay());
            preparedStatement.setBoolean(5, account.getGender());
            preparedStatement.setString(6, account.getPhoneNumber());
            preparedStatement.setString(7, account.getAddress());
            preparedStatement.setDate(8, Date.valueOf(dtf.format(localDate)));
            preparedStatement.setInt(9, account.getAccountId());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public boolean delete(int accountId) throws Exception {
        connection = new MySQLConnection().getConnection();
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM account WHERE account_id = ?");
            preparedStatement.setInt(1, accountId);

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
}
