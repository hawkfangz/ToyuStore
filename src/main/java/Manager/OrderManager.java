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
import org.hibernate.Session;

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

}
