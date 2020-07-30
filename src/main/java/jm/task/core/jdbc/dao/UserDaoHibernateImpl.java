package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        /*String SQL_CREATE = "CREATE TABLE IF NOT EXISTS USERS (" +
                "id INT(10) NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(45) NOT NULL," +
                "lastName VARCHAR(45) NOT NULL," +
                "age INT (3) NOT NULL, PRIMARY KEY (id))";
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery(SQL_CREATE);
        transaction.commit();
        System.out.println("++++++++++++++++++ CREATE +++++++++++++++++++++++++++");
        session.close();*/
    }

    @Override
    public void dropUsersTable() {
        /*String SQL_DROP = "DROP TABLE IF EXISTS USERS";
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery(SQL_DROP);
        transaction.commit();
        System.out.println("++++++++++++++++++ DROP +++++++++++++++++++++++++++");
        session.close();*/
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        transaction.commit();
        System.out.println("++++++++++++++++++ SAVE +++++++++++++++++++++++++++");
        session.close();
        //rollback ????
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.load(User.class, id);
        session.delete(user);
        transaction.commit();
        System.out.println("++++++++++++++++++ REMOVE +++++++++++++++++++++++++++");
        //session.flush();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {

            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            List<User> userList = session.createQuery("SELECT a FROM User a", User.class).getResultList();
            //List<User> userList = (List<User>) session.createSQLQuery("SELECT * FROM users").list();
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
/*        List<User> listToClean = getAllUsers();
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        for (User u: listToClean) {
            System.out.println(u);
            session.delete(u);
        }
        transaction.commit();
        session.close();*/
    }
}
