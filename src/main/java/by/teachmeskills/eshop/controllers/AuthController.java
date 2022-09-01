package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.exceptions.AuthorizationsExceptions;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.exceptions.ServiceExceptions;
import by.teachmeskills.eshop.services.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Objects;

import static by.teachmeskills.eshop.utils.EshopConstants.ERROR;
import static by.teachmeskills.eshop.utils.EshopConstants.PASSWORD;
import static by.teachmeskills.eshop.utils.EshopConstants.NAME;
import static by.teachmeskills.eshop.utils.EshopConstants.USER;
import static by.teachmeskills.eshop.utils.PagesPathEnum.SIGN_IN_PAGE;
import static by.teachmeskills.eshop.utils.PagesPathEnum.START_PAGE;

@RestController
@SessionAttributes({USER})
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

//    @PostMapping
//    public ModelAndView authenticate(@ModelAttribute(USER) @Valid User user,
//                                     BindingResult bindingResult, ModelAndView modelAndView) throws AuthorizationsExceptions, ServiceExceptions, RepositoryExceptions {
//        if (bindingResult.hasErrors()) {
//            fieldError(NAME, modelAndView, bindingResult);
//            fieldError(PASSWORD, modelAndView, bindingResult);
//            modelAndView.setViewName(START_PAGE.getPath());
//            return modelAndView;
//        }
//        return userService.authenticate(user);
//    }

    @GetMapping("/profile")
    public ModelAndView getProfileData(@ModelAttribute(USER) User user,
                                       @RequestParam(defaultValue = "0") int pageNumber,
                                       @RequestParam(defaultValue = "5") int pageSize) throws ServiceExceptions, RepositoryExceptions {
        return userService.getProfileAccount(user, pageNumber, pageSize);
    }

    @ModelAttribute(USER)
    public User setUpUser() {
        return new User();
    }

//    private void fieldError(String field, ModelAndView modelAndView, BindingResult bindingResult) {
//        if (bindingResult.hasFieldErrors(field)) {
//            modelAndView.addObject(field + ERROR,
//                    Objects.requireNonNull(bindingResult.getFieldError(field))
//                            .getDefaultMessage());
//        }
//    }
}