/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phanh
 */
public class Cart {

    private List<CartItem> cart = null;

    public Cart() {
        cart = new ArrayList<>();
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    public float getCartPrice() {
        float total = 0;

        for (CartItem item : cart) {
            total += item.getPrice(); //* item.getQuantity();
        }

        return total;
    }
    
    
//  addQuantity add 1 to item quantity

    public void addItem(CartItem item) {
        int itemPosition = itemExisted(item);
        if (itemPosition == -1) {
            cart.add(item);
        } else {
            cart.get(itemPosition).addQuantity();
            cart.get(itemPosition).setPrice();
        }
    }

    public int itemExisted(CartItem checkingItem) {

        for (int index = 0; index < cart.size(); index++) {
            if (cart.get(index).getProduct().getId() == checkingItem.getProduct().getId()) {
                return index;
            }
        }
        return -1;
    }

}
