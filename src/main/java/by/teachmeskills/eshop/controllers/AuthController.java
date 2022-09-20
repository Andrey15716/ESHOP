package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import static by.teachmeskills.eshop.utils.PagesPathEnum.LOGIN_ERROR_PAGE;
import static by.teachmeskills.eshop.utils.PagesPathEnum.SIGN_IN_PAGE;

@RestController
@RequestMapping("/login")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView openLoginPage() {
        return new ModelAndView(SIGN_IN_PAGE.getPath());
    }

    @GetMapping("/profile")
    public ModelAndView getProfileData(@RequestParam(defaultValue = "0") int pageNumber,
                                       @RequestParam(defaultValue = "5") int pageSize) {
        return userService.getProfileAccount(pageNumber, pageSize);
    }
    @PostMapping("/loginError")
    public ModelAndView openLoginErrorPage() {
        return new ModelAndView(LOGIN_ERROR_PAGE.getPath());
    }
}