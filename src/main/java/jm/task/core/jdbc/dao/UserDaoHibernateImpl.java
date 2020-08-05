package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String SQL_CREATE = "CREATE TABLE IF NOT EXISTS USERS (id INT(10) NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL," +
                "age INT (3) NOT NULL, PRIMARY KEY (id))";
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery(SQL_CREATE);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        String SQL_DROP = "drop table if exists users";
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery(SQL_DROP);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
//        User user = session.load(User.class, id);
//        session.delete(user);
        User user = session.get(User.class, id);
        session.remove(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List <User>userList = (List<User>) session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String HQL_DELETE = "delete from User";
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(HQL_DELETE);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
