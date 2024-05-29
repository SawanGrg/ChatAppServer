package com.web.chatAppBackend.DAO;

import com.web.chatAppBackend.model.User;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public class UserRepository {

    SessionFactory sessionFactory;

    @Autowired
    public UserRepository(
        SessionFactory sessionFactory
    ) {
        this.sessionFactory = sessionFactory;
    }

    public boolean userExists(String username, String password) {

        Session session = sessionFactory.openSession();

        try{
                session.beginTransaction();
                String query = "SELECT * FROM user WHERE user_name = :username AND password = :password";

            Query<User> userTypedQuery = session.createNativeQuery(query, User.class);
            userTypedQuery.setParameter("username", username);
            userTypedQuery.setParameter("password", password);

            User user = userTypedQuery.getSingleResult();

            if (user != null) {
                session.getTransaction().commit();
                System.out.println("User exists");
                return true;
            } else {
                session.getTransaction().commit();
                System.out.println("User does not exist");
                return false;
            }
        } catch (Exception e) {
            //checking if the session is not null and then rollback the transaction
            //session is not null when the transaction is started but not committed
            if (session != null) {
                session.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }

        }

    }

    public long getUserId(String username) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String query = "SELECT user_id FROM user WHERE user_name = :username";
            Query<Long> userIdQuery = session.createNativeQuery(query, Long.class);
            userIdQuery.setParameter("username", username);
            long userId = userIdQuery.getSingleResult();
            session.getTransaction().commit();
            return userId;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<User> allUser() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String hql = "FROM User";  // HQL is more preferable than native SQL
            Query<User> userQuery = session.createQuery(hql, User.class);
            List<User> users = userQuery.getResultList();
            session.getTransaction().commit();
            return users;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();  // Log the exception
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }




}
