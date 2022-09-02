package by.teachmeskills.eshop.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
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

//    public void deleteProduct(Product product) {
//        int price = product.getPrice();
//        products.remove(price, product);
//        totalPrice = totalPrice - price;
//    }
//
//    public void decreaseQuantity(Product product) {
//        int countProducts = products.size();
//        if (countProducts > 1) {
//            countProducts = countProducts - 1;
//            products.put(countProducts, product);
//        } else {
//            products.remove(product.getId(), product);
//        }
//        totalPrice -= product.getPrice();
//    }

    public void clearCart() {
        products.clear();
    }

    public Cart(int totalPrice) {
        this.totalPrice = totalPrice * quantity;
    }
}