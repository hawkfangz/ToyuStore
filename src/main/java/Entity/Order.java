/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author phanh
 */
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "customer_id")
    private int customerId;

    private float price;

    private LocalDate date;

    private int status;

    @OneToMany(mappedBy = "order")
    private Set<CartItem> items;

    public Order() {
        this.status = 0;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getDate() {
        String date;
        if (this.date == null) {
            date = "Not Available";
        } else {
            date = this.date.toString();
        }
        return date.toString();
    }

    public void setDate() {
        this.date = LocalDate.now();
    }

//    public Set<CartItem> getItems() {
//
//        return items;
//    }

    public Set<CartItem> getItems() {
        TreeSet<CartItem> sortedItems = new TreeSet<>(Comparator.comparing(CartItem::getId));
        sortedItems.addAll(items);
        return sortedItems;
    }
    public List<CartItem> getItemsList(){
        List<CartItem> itemsList = items.stream().collect(Collectors.toCollection(ArrayList::new));
        
        return itemsList;
    }

    public void setItems(Set<CartItem> items) {
        this.items = items;
    }
    public int getStatus(){
        return this.status;
    }
    public String getStatusString() {
        String statusString = "N/A";

        if (this.status == -1) {
            statusString = "Cancelled";
        }
        if (this.status == 0) {
            statusString = "Placed";
        }
        if (this.status == 1) {
            statusString = "Prepared";
        }
        if (this.status == 2) {
            statusString = "Delivered";
        }
        //after customer review
        if (this.status == 3) {
            statusString = "Finished";
        }

        return statusString;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public int itemCount(){
        int size = 0;
        for (CartItem item : items) {
            size += item.getQuantity();
        }
        return size;
    }

    public String getPrice() {

        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedPrice = formatter.format(this.price);
        return formattedPrice;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
