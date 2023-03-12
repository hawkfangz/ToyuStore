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
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author phanh
 */
public class UserManager {

    public User login(String account, String password) {
        User user = null;
        password = hashMD5(password);
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
//            Get object list from table, Query should be from class name 
            Query<User> query = session.createQuery("FROM User WHERE account "
                    + "= :account AND password = :password AND status = 1");

            query.setParameter("account", account);
            query.setParameter("password", password);

            user = (User) query.uniqueResult();
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

    public String hashMD5(String input) {

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
