package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class Util {

private static SessionFactory sessionFactory;
public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
        try {
            Configuration conf = new Configuration();
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            properties.put(Environment.URL, "jdbc:mysql://localhost:3306/mytestdb?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            properties.put(Environment.USER, "root");
            properties.put(Environment.PASS, "J-Mentor-root-91");
            properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            properties.put(Environment.HBM2DDL_AUTO, "update");//update - ???
            properties.put(Environment.SHOW_SQL, "false"); //hibernate's "red" messages
            properties.put(Environment.FORMAT_SQL, "false");
            //properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");//???

            conf.setProperties(properties);
            conf.addAnnotatedClass(User.class);
            ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
            sessionFactory = conf.buildSessionFactory(sr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return sessionFactory;
}






/*    private static final String URL = "jdbc:mysql://localhost:3306/mytestdb?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "J-Mentor-root-91";


    public static Connection getConnect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }*/


}
