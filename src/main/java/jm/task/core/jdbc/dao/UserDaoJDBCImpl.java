package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String SQL_CREATE = "CREATE TABLE IF NOT EXISTS USERS (" +
                "id INT(10) NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(45) NOT NULL," +
                "lastName VARCHAR(45) NOT NULL," +
                "age INT (3) NOT NULL, PRIMARY KEY (id))";
        try (PreparedStatement prepStat = Util.getConnect().prepareStatement(SQL_CREATE)) {
            prepStat.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public void dropUsersTable() {
        String SQL_DROP = "DROP TABLE IF EXISTS USERS";
        try (PreparedStatement prepStat = Util.getConnect().prepareStatement(SQL_DROP)) {
            prepStat.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL_INSERT = "INSERT INTO USERS (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement prepStat = Util.getConnect().prepareStatement(SQL_INSERT)) {
            prepStat.setString(1, name);
            prepStat.setString(2, lastName);
            prepStat.setByte(3, age);
            prepStat.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String SQL_DELETE = "DELETE FROM USERS WHERE id=id";
        try (PreparedStatement prepStat = Util.getConnect().prepareStatement(SQL_DELETE)) {
            prepStat.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> summList = new ArrayList<>();
        String SQL_SELECT = "SELECT * FROM USERS";
        try (PreparedStatement prepStat = Util.getConnect().prepareStatement(SQL_SELECT)) {
            ResultSet rs = prepStat.executeQuery();
                while (rs.next()) {
                User objUser = new User();
                objUser.setId(rs.getLong("id"));
                objUser.setName(rs.getString("name"));
                objUser.setLastName(rs.getString("lastName"));
                objUser.setAge(rs.getByte("age"));
                summList.add(objUser);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return summList;
    }

    public void cleanUsersTable() {
        dropUsersTable();
        createUsersTable();
/*        String SQL_TRUNCATE = "TRUNCATE TABLE USERS";
        try (PreparedStatement prepStat = Util.getConnect().prepareStatement(SQL_TRUNCATE)) {
            prepStat.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }*/
    }
}
