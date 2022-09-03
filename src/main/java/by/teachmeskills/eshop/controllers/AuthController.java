package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.teachmeskills.eshop.utils.EshopConstants.ATTACHMENT_FILE_NAME_ORDER_CSV;
import static by.teachmeskills.eshop.utils.EshopConstants.CONTENT_DISPOSITION;
import static by.teachmeskills.eshop.utils.EshopConstants.TEXT_CSV;
import static by.teachmeskills.eshop.utils.EshopConstants.UTF8;
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
}