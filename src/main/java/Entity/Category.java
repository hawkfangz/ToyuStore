/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author Hau
 */
@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int id;

    @Column(name = "category_name")
    private String name;
    @Column(name = "description")
    private String des;
    @Column(name = "status")
    private int status;

    @ManyToMany(mappedBy = "categories")
    Set<Product> products;

    public Category() {
        this.status = 0;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Set<Product> getProducts() {
        return products;
    }
    public int getQuantity(){
        return products.size();
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return this.getId() + " " + this.name;
    }
}
