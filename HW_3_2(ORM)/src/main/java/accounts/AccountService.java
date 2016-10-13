package accounts;


import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountService {
    private DBService dbService;
    private final Map<String, UsersDataSet> sessionIdToProfile;

    public AccountService() {
        sessionIdToProfile = new HashMap<>();

        dbService = new DBService();
        dbService.printConnectInfo();
    }

    public void addNewUser(String name, String pass) throws DBException {
        dbService.addUser(name, pass);
    }

       public UsersDataSet getUser(long id) throws DBException {
        return dbService.getUser(id);
    }

    public UsersDataSet getUserByLogin(String name) throws DBException {
        return dbService.getUserId(name);
    }

    public List<UsersDataSet> getAllUsers() throws DBException {
        return dbService.getAllUsers();
    }

    public UsersDataSet getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UsersDataSet user) {
        sessionIdToProfile.put(sessionId, user);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}