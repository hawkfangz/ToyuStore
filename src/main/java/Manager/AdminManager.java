/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import Entity.Admin;
import Util.HibernateUtil;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Admin> criteriaQuery = criteriaBuilder.createQuery(Admin.class);
            Root<Admin> root = criteriaQuery.from(Admin.class);
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("account"), acc),
                    criteriaBuilder.equal(root.get("password"), hashMD5(pass)),
                    criteriaBuilder.equal(root.get("status"), 1)
            ));

            admin = session.createQuery(criteriaQuery).uniqueResult();
            return admin;
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
