/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import Entity.User;
import Entity.Manufacturer;
import Util.HibernateUtil;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author phanh
 */
public class UserManager {

//    public User login(String account, String password) {
//        User user = null;
//        password = hashMD5(password);
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        try {
////            Get object list from table, Query should be from class name 
//            Query<User> query = session.createQuery("FROM User WHERE account "
//                    + "= :account AND password = :password AND status = 1");
//
//            query.setParameter("account", account);
//            query.setParameter("password", password);
//
//            user = (User) query.uniqueResult();
//            return user;
//        } finally {
//            session.close();
//        }
//    }

    public User login(String account, String password) {
        User user = null;
        password = hashMD5(password);
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("account"), account),
                    criteriaBuilder.equal(root.get("password"), password),
                    criteriaBuilder.equal(root.get("status"), 1)
            ));

            user = session.createQuery(criteriaQuery).uniqueResult();
            return user;
        } finally {
            session.close();
        }
    }

    public boolean signUp(String account, String password,
            String name, String phone, LocalDate dateOfBirth, String address, String email, String gender) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            User user = new User();
            user.setAccount(account);
            user.setPassword(hashMD5(password));
            user.setName(name);
            user.setPhone(phone);
            user.setEmail(email);
            user.setDob(dateOfBirth);
            user.setAddress(address);
            user.setGender(gender);

            session.save(user);
            return user.getCustomerId() > 0;
        } finally {
            session.close();
        }
    }

    public User update(int id, String name, String phone, LocalDate dateOfBirth,
            String address, String email, String gender) {
        User user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            user = session.load(User.class, id);
            user.setName(name);
            user.setPhone(phone);
            user.setEmail(email);
            user.setDob(dateOfBirth);
            user.setAddress(address);
            user.setGender(gender);

            session.update(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    public User toggle(int id) {
        User user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            user = session.load(User.class, id);
            if (user.getStatus() == 0) {
                user.setStatus(1);
            }
            if (user.getStatus() == 1) {
                user.setStatus(0);
            }

            session.update(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    public List<User> getAllUsers() {
        List<User> users;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<User> query = session.createQuery("FROM User");
            users = query.list();
            System.out.println("user: " + users.size());
            for (User user : users) {
                user.setPassword("");
            }
            return users;
        } finally {
            session.close();
        }
    }

    private String hashMD5(String input) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
