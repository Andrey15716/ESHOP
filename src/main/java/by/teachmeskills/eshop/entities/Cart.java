package by.teachmeskills.eshop.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class Cart extends BaseEntity {
    private Map<Integer, Product> products;
    private int totalPrice;
    private int quantity;

    public Cart() {
        this.products = new HashMap<>();
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products.values());
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
        totalPrice += product.getPrice();
    }

    public void increaseProduct(Product product) {
        products.put(product.getId(), product);
        totalPrice += product.getPrice();
    }

    public void decreaseProduct(Product product) {
        int quantity = products.size();
        if (quantity > 1) {
            products.remove(products.size() - 1);
        } else {
            products.remove(product.getId(), product);
        }


        totalPrice -= product.getPrice();
    }

    public void clearCart() {
        products.clear();
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Cart(int totalPrice) {
        this.totalPrice = totalPrice * quantity;
    }
}