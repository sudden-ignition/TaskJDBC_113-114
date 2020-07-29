package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    private static UserServiceImpl usi= null;

    public static void main(String[] args) {
        usi = new UserServiceImpl();

        usi.createUsersTable();
        usi.saveUser("Johnny", "Smith", (byte) 1);
        printCurrentUserName();
        usi.saveUser("Chun", "Chon", (byte) 19);
        printCurrentUserName();
        usi.saveUser("Mohammed", "Abu", (byte) 49);
        printCurrentUserName();
        usi.saveUser("Vladimir", "Lenin", (byte) 127);
        printCurrentUserName();
        // ^^looks shitty, but it does everything according to the JavaMentor technical task :) ^^
        List<User> toPrint = usi.getAllUsers();
        for (User u: toPrint) {
            System.out.println(u.toString());
        }
        usi.cleanUsersTable();
        usi.dropUsersTable();
    }

    public static void printCurrentUserName () {
        List<User> list = usi.getAllUsers();
        System.out.println("User с именем - " + list.get(list.size()-1).getName() + " добавлен в базу данных");
    }

}


