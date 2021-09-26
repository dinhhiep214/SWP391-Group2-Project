package swp391.dao;

import swp391.bean.Account;
import swp391.constant.Constant;
import swp391.jdbc.MySQLConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
            preparedStatement.setBoolean(6, account.isGender());
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
            preparedStatement.setBoolean(5, account.isGender());
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

    public List<Account> findAll() throws Exception {
        connection = new MySQLConnection().getConnection();
        List<Account> accountList = null;
        int accountId;
        boolean gender;
        Date birthDay, createdDate, updatedDate;
        String email, password, firstName, lastName, role, phoneNumber, address, status;

        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM account");
            resultSet = preparedStatement.executeQuery();
            accountList = new ArrayList<>();

            while (resultSet.next()) {
                accountId = resultSet.getInt("account_id");
                email = resultSet.getString("email");
                password = resultSet.getString("password");
                firstName = resultSet.getString("firstname");
                lastName = resultSet.getString("lastname");
                birthDay = resultSet.getDate("birthday");
                gender = resultSet.getBoolean("gender");
                role = resultSet.getString("role");
                phoneNumber = resultSet.getString("phone_number");
                address = resultSet.getString("address");
                status = resultSet.getString("status");
                createdDate = resultSet.getDate("created_date");
                updatedDate = resultSet.getDate("updated_date");

                accountList.add(new Account(accountId, email, password, firstName, lastName,
                        birthDay, gender, role, phoneNumber, address, status, createdDate, updatedDate));
            }

            return accountList;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public Account findById(int accountId) throws Exception {
        connection = new MySQLConnection().getConnection();
        Account account = null;
        boolean gender;
        Date birthDay, createdDate, updatedDate;
        String email, password, firstName, lastName, role, phoneNumber, address, status;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM account WHERE account_id = ?");
            preparedStatement.setInt(1, accountId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                email = resultSet.getString("email");
                password = resultSet.getString("password");
                firstName = resultSet.getString("firstname");
                lastName = resultSet.getString("lastname");
                birthDay = resultSet.getDate("birthday");
                gender = resultSet.getBoolean("gender");
                role = resultSet.getString("role");
                phoneNumber = resultSet.getString("phone_number");
                address = resultSet.getString("address");
                status = resultSet.getString("status");
                createdDate = resultSet.getDate("created_date");
                updatedDate = resultSet.getDate("updated_date");

                account = new Account(accountId, email, password, firstName, lastName, role, birthDay, gender,
                        phoneNumber, address, status, createdDate, updatedDate);
            }

            return account;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public Account findByEmail(String email) throws Exception {
        connection = new MySQLConnection().getConnection();
        Account account = null;
        int accountId;
        boolean gender;
        Date birthDay, createdDate, updatedDate;
        String password, firstName, lastName, role, phoneNumber, address, status;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM account WHERE account_id = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                accountId = resultSet.getInt("account_id");
                password = resultSet.getString("password");
                firstName = resultSet.getString("firstname");
                lastName = resultSet.getString("lastname");
                birthDay = resultSet.getDate("birthday");
                gender = resultSet.getBoolean("gender");
                role = resultSet.getString("role");
                phoneNumber = resultSet.getString("phone_number");
                address = resultSet.getString("address");
                status = resultSet.getString("status");
                createdDate = resultSet.getDate("created_date");
                updatedDate = resultSet.getDate("updated_date");

                account = new Account(accountId, email, password, firstName, lastName, role, birthDay, gender,
                        phoneNumber, address, status, createdDate, updatedDate);
            }

            return account;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public boolean changePassword(int accountId, String newPassword) throws Exception {
        connection = new MySQLConnection().getConnection();

        try {
            preparedStatement = connection.prepareStatement("UPDATE account SET password = ? WHERE account_id = ?");
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, accountId);
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
