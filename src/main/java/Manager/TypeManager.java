/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import Entity.Admin;
import Entity.Product;
import Entity.ProductType;
import Util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author phanh
 */
public class TypeManager {

    public List<ProductType> getAllType() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Query<ProductType> query;

            query = session.createQuery("FROM ProductType");

            List<ProductType> typeList = query.list();

            typeList.sort((t1, t2) -> t1.getId() - t2.getId());

            return typeList;
        }
    }

    public List<ProductType> getActiveTypes() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Query<ProductType> query;

            query = session.createQuery("FROM ProductType AS T WHERE T.status = 1");

            List<ProductType> typeList = query.list();

            typeList.sort((t1, t2) -> t1.getId() - t2.getId());

            return typeList;
        }
    }

    public ProductType getType(int id) {
        ProductType type = null;
        ProductType returnType = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            type = session.get(ProductType.class, id);

            returnType = type;

            returnType.setProducts(type.getProducts());

            return returnType;
        } finally {
            session.close();
        }
    }

    public List<ProductType> getRemainedTypes(List<ProductType> productTypes, List<ProductType> allTypes) {

        for (int i = 0; i < allTypes.size(); i++) {

            for (int j = 0; j < productTypes.size(); j++) {
                if (allTypes.get(i).getId() == productTypes.get(j).getId()) {
                    allTypes.remove(allTypes.get(i));
                    productTypes.remove(productTypes.get(j));
                }
                if (productTypes.isEmpty()) {
                    return allTypes;
                }
            }
        }

        return allTypes;
    }

    public void addType(String name, String des, int status) {
//      Add new Object, Insert Object to table, hibernate handle the auto increment
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            ProductType productType = new ProductType();

            productType.setName(name);
            productType.setDes(des);
            productType.setStatus(status);

            session.save(productType);

        } finally {
            session.close();
        }
    }

    public void updateType(int id, String name, String des, int status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
//          Tao Transaction roi commit neu muon update 
            transaction = session.beginTransaction();
//          Load row trong db duoi dang object            
            ProductType type = session.load(ProductType.class, id);

            type.setName(name);
            type.setDes(des);
            type.setStatus(status);
            session.update(type);
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

    public int toggleType(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
//          Tao Transaction roi commit neu muon update 
            transaction = session.beginTransaction();
//          Load row trong db duoi dang object            
            ProductType type = session.load(ProductType.class, id);

            System.out.println(type.getName());

            if (type.getStatus() == 1) {
                type.setStatus(0);
            } else {
                type.setStatus(1);
            }

            session.update(type);
            transaction.commit();

            return type.getStatus();

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
