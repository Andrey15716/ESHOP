package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.entities.Cart;
import by.teachmeskills.eshop.entities.Order;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.repositories.OrderRepository;
import by.teachmeskills.eshop.repositories.ProductRepository;
import by.teachmeskills.eshop.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

import static by.teachmeskills.eshop.utils.PagesPathEnum.CART_PAGE;
import static by.teachmeskills.eshop.utils.PagesPathEnum.ORDER_PAGE;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.ORDER_ID_PARAM;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.PRICE_ORDER_PARAM;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.PRODUCT_PARAM;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.SHOPPING_CART_PARAM;

@Service
@Slf4j
public class CartService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public CartService(ProductRepository productRepository, OrderRepository orderRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public ModelAndView getCartData(Cart cart) {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute(SHOPPING_CART_PARAM.getValue(), cart.getProducts());
        modelMap.addAttribute(PRICE_ORDER_PARAM.getValue(), cart.getTotalPrice());
        return new ModelAndView(CART_PAGE.getPath(), modelMap);
    }

    public ModelAndView addProductToCart(int productId, Cart shopCart) {
        ModelMap modelParams = new ModelMap();
        Product product = productRepository.getProductById(productId);
        shopCart.addProduct(product);
        modelParams.addAttribute(PRODUCT_PARAM.getValue(), product);
        modelParams.addAttribute(SHOPPING_CART_PARAM.getValue(), shopCart);
        return new ModelAndView(CART_PAGE.getPath(), modelParams);
    }

    public ModelAndView buyProduct(Cart shopCart) {
        List<Product> products = shopCart.getProducts();
        int priceOrder = shopCart.getTotalPrice();
        LocalDate date = LocalDate.now();
        ModelMap modelMap = new ModelMap();
        String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
        userRepository.getUserByName(loggedUser).ifPresent(user -> {
            Order createdOrder = orderRepository.save(Order.builder()
                    .priceOrder(priceOrder)
                    .date(date)
                    .user(user)
                    .productList(products)
                    .build());
            modelMap.addAttribute(PRICE_ORDER_PARAM.getValue(), shopCart.getTotalPrice());
            modelMap.addAttribute(ORDER_ID_PARAM.getValue(), createdOrder.getId());
            shopCart.clearCart();
            log.info("Order " + createdOrder.getId() + " was created with user id + " + user.getId());
        });
        return new ModelAndView(ORDER_PAGE.getPath(), modelMap);
    }

    public ModelAndView increaseProductInCart(Cart shopCart, int productId) {
        ModelMap modelMap = new ModelMap();
        Product product = productRepository.getProductById(productId);
        shopCart.increaseProduct(product);
        modelMap.addAttribute(SHOPPING_CART_PARAM.getValue(), shopCart);
        return new ModelAndView(CART_PAGE.getPath(), modelMap);
    }


    public ModelAndView decreaseProductInCart(Cart shopCart, int productId) {
        ModelMap modelMap = new ModelMap();
        Product product = productRepository.getProductById(productId);
        shopCart.decreaseProduct(product);
        if (shopCart.getTotalPrice() == 0) {
            shopCart.clearCart();
        }
        modelMap.addAttribute(SHOPPING_CART_PARAM.getValue(), shopCart);
        return new ModelAndView(CART_PAGE.getPath(), modelMap);
    }

    public ModelAndView clearCart(Cart shopCart) {
        ModelMap modelMap = new ModelMap();
        shopCart.clearCart();
        shopCart.setTotalPrice(0);
        modelMap.addAttribute(SHOPPING_CART_PARAM.getValue(), shopCart);
        return new ModelAndView(CART_PAGE.getPath(), modelMap);
    }
}