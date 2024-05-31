/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import Entity.Manufacturer;
import Entity.Product;
import Entity.Category;
import Util.HibernateUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author phanh
 */
public class ProductManager {

    public List<Product> getActiveProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
//            Get object list from table, Query should be from class name 
            Query<Product> query = session.createQuery("FROM Product AS P WHERE P.status = 1");

            List<Product> products = query.list();

            products.sort((product1, product2) -> product2.getId() - product1.getId());

            return products;

        } finally {
            session.close();
        }
    }

    public List<Product> getAllProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
//            Get object list from table, Query should be from class name 
//            Query<Product> query = session.createQuery("FROM Product");
//            List<Product> products = query.list();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
            Root<Product> root = criteriaQuery.from(Product.class);
            criteriaQuery.select(root);
            List<Product> products = session.createQuery(criteriaQuery).getResultList();

            products.sort((product1, product2) -> product2.getId() - product1.getId());
            return products;

        } finally {
            session.close();
        }
    }

    public Product getProduct(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Product product = session.get(Product.class, id);

            Product returnProduct = product;

            returnProduct.setTypes(product.getCategories());

            return returnProduct;
        } finally {
            session.close();
        }
    }

    public List<Product> getProductByType(int typeId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
//          Lay tat ca product co type id
            Category type = session.get(Category.class, typeId);

            Set<Product> products = type.getProducts();

            List<Product> sortedList = new ArrayList<>(products);

            sortedList.sort((product1, product2) -> product1.getId() - product2.getId());

//            for (Product product : sortedList) {
//                System.out.println(product.getId() + " " + product.getName());
//            }
            return sortedList;
        } finally {
            session.close();

        }
    }

    public int addProduct(String name, String description, float Price, int[] selectedTypeId, int[] selectedManufacturers) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.getTransaction().begin();

            Set<Category> typeSet = new HashSet<>();
//          Get types from type table as object, then add to typeSet

            for (int typeId : selectedTypeId) {
                Category type = session.get(Category.class,
                        typeId);
                typeSet.add(type);
            }

            Set<Manufacturer> manufacturers = new HashSet<>();

//          Get manufacturers with ID then add to HashSet
            for (Integer manufacturerId : selectedManufacturers) {
                Manufacturer manufacturer = session.get(Manufacturer.class,
                        manufacturerId);
                manufacturers.add(manufacturer);
            }

//          Create new product
            Product product = new Product();

            product.setName(name);
            product.setDes(description);
            product.setPrice(Price);
            product.setTypes(typeSet);
            product.setManufacturers(manufacturers);

            session.save(product);
            session.getTransaction().commit();

            return product.getId();

        } finally {
            session.close();
        }
    }

    public void editProduct(int productId, String name, String description, float Price, int[] selectedTypeId, int[] selectedManufacturers) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.getTransaction().begin();

            Product product = session.load(Product.class,
                    productId);

//          Get type from type table as object, 111 is it ID, not line number
            Set<Category> typeSet = new HashSet<>();

            for (int typeId : selectedTypeId) {
                Category type = session.get(Category.class,
                        typeId);
                typeSet.add(type);
            }

            Set<Manufacturer> manufacturers = new HashSet<>();

//          Get manufacturers with ID then add to HashSet
            for (Integer manufacturerId : selectedManufacturers) {
                Manufacturer manufacturer = session.get(Manufacturer.class,
                        manufacturerId);
                manufacturers.add(manufacturer);
            }

//          Edit the product
            product.setName(name);
            product.setDes(description);
            product.setPrice(Price);
            product.setTypes(typeSet);
            product.setManufacturers(manufacturers);

            session.saveOrUpdate(product);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public List<Product> search(String keyword) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
            Root<Product> root = criteria.from(Product.class);

            criteria.select(root).where(
                    builder.or(
                            builder.like(root.get("name"), "%" + keyword + "%"),
                            builder.like(root.get("des"), "%" + keyword + "%")
                    )
            );

            return session.createQuery(criteria).getResultList();
        } finally {
            session.close();
        }
    }

    public void toggleProduct(int productId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();

            Product product = session.load(Product.class, productId);
            if (product.getStatus() == 0) {
                product.setStatus(1);
            } else {
                product.setStatus(0);
            }
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        } finally {
            session.close();
        }

    }
}
