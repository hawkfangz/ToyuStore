/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import Entity.Manufacturer;
import Util.HibernateUtil;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author phanh
 */
public class ManufacturerManager {

    public List<Manufacturer> getManufacturersList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
//            Get object list from table, Query should be from class name 
            Query<Manufacturer> query = session.createQuery("FROM Manufacturer");

            List<Manufacturer> manufacturers = query.list();

            return manufacturers;
        } finally {

            session.close();

        }
    }

    public void add(String name, String country, int status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            Manufacturer manufacturer = new Manufacturer();

            manufacturer.setName(name);
            manufacturer.setCountry(country);
            manufacturer.setStatus(status);
            session.save(manufacturer);

        } finally {
            session.close();
        }
    }

    public void toggle(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Manufacturer manufacturer = session.load(Manufacturer.class, id);

            if (manufacturer.getStatus()== 0) {
                manufacturer.setStatus(1);
            }else{
                manufacturer.setStatus(0);
            }

            session.update(manufacturer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

}
