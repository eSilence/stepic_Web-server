package main;


import accounts.AccountService;
import dbService.DBException;
import dbService.dataSets.UsersDataSet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        AccountService accountService = new AccountService();

        try {
            //accountService.addNewUser("a","a");
            //accountService.addNewUser("admin", "admin");
            //accountService.addNewUser("test", "test");

            List<UsersDataSet> listIds = accountService.getAllUsers(); //  getUserId("tully");
           // System.out.println("Added user: \n" + listIds);

            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
            context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");

            ResourceHandler resource_handler = new ResourceHandler();
            resource_handler.setResourceBase("public_html");

            HandlerList handlers = new HandlerList();
            handlers.setHandlers(new Handler[]{resource_handler, context});

            Server server = new Server(8080);
            server.setHandler(handlers);

            server.start();
            System.out.println("Server started");
            server.join();

//        DBService dbService = new DBService();
//        dbService.printConnectInfo();


//            long userId = dbService.addUser("tully", "sd");
//            System.out.println("Added user id: " + userId);
//
//            UsersDataSet dataSet = dbService.getUser(userId);
//            System.out.println("User data set: " + dataSet);
//
//            userId = dbService.addUser("tully", "s0d");
//            System.out.println("Added user2 id: " + userId);
//
//            ArrayList id = dbService.getUserId("tully");
//            System.out.println("id data set: " + id);

        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}