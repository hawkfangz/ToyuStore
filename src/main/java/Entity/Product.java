/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author Hau
 */
@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "description")
    private String des;
    @Column(name = "price")
    private float price;
    @Column(name = "status")
    private int status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "type_product",
            joinColumns = {
                @JoinColumn(name = "product_id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "type_id")
            }
    )
    private Set<ProductType> types;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_manufacturer",
            joinColumns = {
                @JoinColumn(name = "product_id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "manufacturer_id")
            }
    )
    private Set<Manufacturer> manufacturers;

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPrice() {
        
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedPrice = formatter.format(this.price);
        return formattedPrice;
    }
    public float getPriceValue(){
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public Set<ProductType> getTypes() {
        return types;
    }

    public void setTypes(Set<ProductType> types) {
        this.types = types;
    }


    public Set<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(Set<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
    }

}
