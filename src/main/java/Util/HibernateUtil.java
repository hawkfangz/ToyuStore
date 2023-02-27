/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import Entity.Admin;
import Entity.User;
import Entity.CartItem;
import Entity.Manufacturer;
import Entity.Order;
import Entity.ProductType;
import Entity.Product;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author phanh
 */
//Hibernate Configure Here
public class HibernateUtil {

    private final static SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();
        Properties pros = new Properties();
        pros.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        pros.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        pros.put(Environment.URL, "jdbc:mysql://localhost:3306/toyshop2");
        pros.put(Environment.USER, "root");
        pros.put(Environment.PASS, "admin");
//        pros.put(Environment.SHOW_SQL, "true");
        pros.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, "true");
//        pros.put(Environment.HBM2DDL_AUTO, "create-only");
        conf.setProperties(pros);

        conf.addAnnotatedClass(Admin.class);
        conf.addAnnotatedClass(ProductType.class);
        conf.addAnnotatedClass(Product.class);
        conf.addAnnotatedClass(Order.class);
        conf.addAnnotatedClass(Manufacturer.class);
        conf.addAnnotatedClass(CartItem.class);
        conf.addAnnotatedClass(User.class);
        conf.addAnnotatedClass(Admin.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();

        FACTORY = conf.buildSessionFactory(registry);
    }

    public static SessionFactory getSessionFactory() {
        return FACTORY;
    }

}
