package main;

import accounts.AccountService;
import accounts.UserProfile;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;

/**
 * ﻿﻿Задача:
 Написать сервер с двумя сервлетами:
 SignUpServlet для обработки запросов на signup и
 SignInServlet для обработки запросов на signin

 Сервлеты должны слушать POST запросы с параметрами
 login
 password

 При получении POST запроса на signup сервлет SignUpServlet должн запомнить логин и пароль в AccountService.
 После этого польователь с таким логином считается зарегистрированным.
 При получении POST запроса на signin, после регистрации, SignInServlet проверяет,
 логин/пароль пользователя. Если пользователь уже зарегистрирован, север отвечает

 Status code (200)
 и текст страницы:
 Authorized: login

 если нет:
 Status code (401)
 текст страницы:
 Unauthorized
 */
public class Main {
    public static void main(String[] args) throws Exception {
        AccountService accountService = new AccountService();

        accountService.addNewUser(new UserProfile("admin"));
        accountService.addNewUser(new UserProfile("test"));

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
    }

}
