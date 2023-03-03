/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import Entity.Cart;
import Entity.User;
import Entity.CartItem;
import Entity.Order;
import Util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author phanh
 */
public class OrderManager {

    public Order CreateOrder(User customer, Cart cart) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Order order = new Order();
            order.setCustomerId(customer.getCustomerId());
            order.setPrice(cart.getCartPrice());
            order.setDate();
            session.save(order);
            return order;
        } finally {
            session.close();
        }
    }

    public void addItemsToOrder(Order order, Cart cart) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            order.setPrice(cart.getCartPrice());
            for (CartItem item : cart.getCart()) {
                item.setOrder(order);
                item.setPrice();
                session.save(item);
            }
        } finally {
            session.close();
        }

    }

    public List<Order> getUserOrders(User customer) {
        List<Order> userOrders = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Order> query = session.createQuery("FROM Order WHERE customerId "
                    + "= :id");
            query.setParameter("id", customer.getCustomerId());

            userOrders = query.list();

        } finally {
            session.close();
        }
        return userOrders;
    }

}
