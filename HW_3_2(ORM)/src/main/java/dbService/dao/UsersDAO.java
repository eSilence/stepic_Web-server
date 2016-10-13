package dbService.dao;

import dbService.dataSets.UsersDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class UsersDAO {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet get(long id) throws HibernateException {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public UsersDataSet getUserId(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        UsersDataSet users =  (UsersDataSet) criteria
                .add(Restrictions.eq("name", name))
                .addOrder(Order.asc("id"))
                .uniqueResult();

        return users;
    }

    public List<UsersDataSet> getAllUsers() throws HibernateException {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        return (List<UsersDataSet>) criteria.list();
    }

    public long insertUser(String name, String pass) throws HibernateException {
        return (Long) session.save(new UsersDataSet(name, pass));
    }
}
