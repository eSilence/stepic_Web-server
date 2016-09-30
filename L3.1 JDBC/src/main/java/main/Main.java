package main;


import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;

import java.util.ArrayList;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class Main {
    public static void main(String[] args) {
        DBService dbService = new DBService();
        dbService.printConnectInfo();
        String [] names = {"tully", "Willy", "Monny", "Addy", "Kelly"};
        try {
            for (String name: names) {
                dbService.addUser(name);
            }
            long userId = dbService.addUser("tully");
            System.out.println("Added user id: " + userId);
            userId = dbService.addUser("villy");
            System.out.println("Added user id: " + userId);
            userId = dbService.addUser("dally");
            System.out.println("Added user id: " + userId);

            UsersDataSet dataSet = dbService.getUser(userId);
            System.out.println("User data set: " + dataSet);

            ArrayList<UsersDataSet> dataSets = dbService.getAllUsers();
            System.out.println("Users: " + dataSets);

            dbService.cleanUp();
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}