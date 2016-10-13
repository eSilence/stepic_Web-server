package servlets;

import accounts.AccountService;
import com.google.gson.Gson;
import dbService.DBException;
import dbService.dataSets.UsersDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignUpServlet extends HttpServlet {
    //@SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"}) //todo: remove after module 2 home work
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("Sign Up:");

        try {
            List<UsersDataSet> users = accountService.getAllUsers();
            response.getWriter().println(users);
        } catch (DBException e) {
            e.printStackTrace();
        }

    }

    //sign up
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        if (login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            //        UserProfile profile = accountService.getUserByLogin(login);
//        if (profile != null) {
//            response.setContentType("text/html;charset=utf-8");
//            response.getWriter().println("This login already exists");
//            response.setStatus(HttpServletResponse.SC_CONFLICT);
//            return;
//        }

            accountService.addNewUser(login, pass);
        } catch (DBException ex) {
            Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Gson gson = new Gson();
        //String json = gson.toJson(accountService.getUserByLogin(login));
        //response.setContentType("application/json; charset=utf-8");
        //response.getWriter().println(json);
        response.getWriter().println("Success registered");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    //change profile
    public void doPut(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
    }

    //unregister
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
    }
}
