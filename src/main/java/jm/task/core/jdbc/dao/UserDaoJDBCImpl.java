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
        String SQL_CREATE = "CREATE TABLE USER NULLIF"
                + "("
                + " PRIMARY KEY (id) "
                + " name VARCHAR(45) NOT NULL, "
                + " lastName VARCHAR(45), "
                + " age VARCHAR (3) NOT NULL, "
                + ")";
        /*try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        try (PreparedStatement prepStat = Util.getConnect().prepareStatement(SQL_CREATE)) {
            prepStat.execute();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            System.out.println("Non-SQL exception on CREATE method" + e.getMessage());
        }
    }

    public void dropUsersTable() {

        String SQL_DROP = "DROP TABLE IF EXISTS USER";
        try (Connection dbConnection = Util.getConnect();
            PreparedStatement prepStat = dbConnection.prepareStatement(SQL_DROP)) {
            prepStat.execute();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            System.out.println("Non-SQL exception on DROP_TABLE method" + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL_INSERT = "INSERT INTO USER (1, 2, 3) VALUES (?,?,?)";
        try (Connection dbConnection = Util.getConnect();
            PreparedStatement prepStat = dbConnection.prepareStatement(SQL_INSERT)) {
            prepStat.setString(1, name);
            prepStat.setString(2, lastName);
            prepStat.setByte(3, age);
            prepStat.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            System.out.println("Non-SQL exception on SAVE_USER method" + e.getMessage());
        }
    }

    public void removeUserById(long thisId) {
        String SQL_DELETE = "DELETE FROM USER WHERE id=thisId";
        try (Connection dbConnection = Util.getConnect();
            PreparedStatement prepStat = dbConnection.prepareStatement(SQL_DELETE)) {
            prepStat.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            System.out.println("Non-SQL exception on REMOVE_USER method" + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> summary = new ArrayList<>();
        String SQL_SELECT = "Select * from USER";
        try (Connection dbConnection = Util.getConnect();
            PreparedStatement prepStat = dbConnection.prepareStatement(SQL_SELECT)) {
            ResultSet rs = prepStat.executeQuery();
            while (rs.next()) {
                User objUser = new User();
                objUser.setId(rs.getLong("id"));
                objUser.setName(rs.getString("name"));
                objUser.setLastName(rs.getString("lastName"));
                objUser.setAge(rs.getByte("age"));
                summary.add(objUser);
            }
            for (User u: summary) {
                System.out.println(u);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            System.out.println("Non-SQL exception on GET_ALL_USER method" + e.getMessage());
        }
        return summary;
    }

    public void cleanUsersTable() {
        String SQL_TRUNCATE = "TRUNCATE TABLE USER";
        try (Connection dbConnection = Util.getConnect();
            PreparedStatement prepStat = dbConnection.prepareStatement(SQL_TRUNCATE)) {
            prepStat.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            System.out.println("Non-SQL exception on CLEAN_USER method" + e.getMessage());
        }
    }
}
