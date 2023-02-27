/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import Entity.Admin;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author phanh
 */
public class AdminManager {

    public Admin login(String acc, String pass) {
        Admin admin = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
//            Get object list from table, Query should be from class name 
            Query<Admin> query = session.createQuery("FROM Admin WHERE account = :account AND password = :password");

            query.setParameter("account", acc);
            query.setParameter("password", pass);

            admin = (Admin) query.uniqueResult();
            return admin;
        } finally {
            session.close();
        }
    }
}
