package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.entities.Cart;
import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.services.CartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import static by.teachmeskills.eshop.utils.EshopConstants.PRODUCT_ID_PARAM;
import static by.teachmeskills.eshop.utils.EshopConstants.SHOPPING_CART;
import static by.teachmeskills.eshop.utils.EshopConstants.USER;

@RestController
@SessionAttributes({SHOPPING_CART})
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/add")
    public ModelAndView addProductToCart(@RequestParam(PRODUCT_ID_PARAM) int productId,
                                         @ModelAttribute(SHOPPING_CART) Cart shopCart) throws RepositoryExceptions {
        return cartService.addProductToCart(productId, shopCart);
    }

    @GetMapping("/buy")
    public ModelAndView buyProduct(@ModelAttribute(SHOPPING_CART) Cart shopCart,
                                   @SessionAttribute(USER) User user) throws RepositoryExceptions {
        return cartService.buyProduct(shopCart, user);
    }

    @GetMapping("/delete")
    public ModelAndView deleteProduct(@RequestParam(PRODUCT_ID_PARAM) int productId,
                                      @ModelAttribute(SHOPPING_CART) Cart shopCart) throws RepositoryExceptions {
        return cartService.deleteProductFromCart(productId, shopCart);
    }

    @GetMapping("/increase")
    public ModelAndView increaseProduct(@RequestParam(PRODUCT_ID_PARAM) int productId,
                                        @ModelAttribute(SHOPPING_CART) Cart shopCart) throws RepositoryExceptions {
        return cartService.increaseProductQuantity(productId, shopCart);
    }

    @GetMapping("/decrease")
    public ModelAndView decreaseProduct(@RequestParam(PRODUCT_ID_PARAM) int productId,
                                        @ModelAttribute(SHOPPING_CART) Cart shopCart) throws RepositoryExceptions {
        return cartService.decreaseProductQuantity(productId, shopCart);
    }

    @ModelAttribute(SHOPPING_CART)
    public Cart shoppingCart() {
        return new Cart();
    }
}