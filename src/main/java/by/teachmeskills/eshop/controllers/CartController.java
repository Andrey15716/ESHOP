package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.entities.Cart;
import by.teachmeskills.eshop.services.CartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import static by.teachmeskills.eshop.utils.EshopConstants.PRODUCT_ID_PARAM;
import static by.teachmeskills.eshop.utils.EshopConstants.SHOPPING_CART;

@RestController
@SessionAttributes({SHOPPING_CART})
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ModelAndView openCartPage(@ModelAttribute(SHOPPING_CART) Cart cart) {
        return cartService.getCartData(cart);
    }

    @GetMapping("/add")
    public ModelAndView addProductToCart(@RequestParam(PRODUCT_ID_PARAM) int productId,
                                         @ModelAttribute(SHOPPING_CART) Cart shopCart) {
        return cartService.addProductToCart(productId, shopCart);
    }

    @GetMapping("/buy")
    public ModelAndView buyProduct(@ModelAttribute(SHOPPING_CART) Cart shopCart) {
        return cartService.buyProduct(shopCart);
    }

//    @GetMapping("/delete")
//    public ModelAndView deleteProduct(@ModelAttribute(SHOPPING_CART) Cart shopCart, int productId) {
//        return cartService.deleteProductFromCart(shopCart, productId);
//    }

    @GetMapping("/increase")
    public ModelAndView increaseProduct(@ModelAttribute(SHOPPING_CART) Cart shopCart, int productId) {
        return cartService.increaseProductInCart(shopCart, productId);
    }

    @GetMapping("/decrease")
    public ModelAndView decreaseProduct(@ModelAttribute(SHOPPING_CART) Cart shopCart, int productId) {
        return cartService.decreaseProductInCart(shopCart, productId);
    }

    @GetMapping("/clear")
    public ModelAndView clearProducts(@ModelAttribute(SHOPPING_CART) Cart shopCart) {
        return cartService.clearCart(shopCart);
    }

    @ModelAttribute(SHOPPING_CART)
    public Cart shoppingCart() {
        return new Cart();
    }
}