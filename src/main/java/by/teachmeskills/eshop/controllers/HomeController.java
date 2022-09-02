package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.entities.Category;
import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.exceptions.ServiceExceptions;
import by.teachmeskills.eshop.services.CategoryService;
import by.teachmeskills.eshop.services.UserService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.teachmeskills.eshop.utils.EshopConstants.USER;
import static by.teachmeskills.eshop.utils.PagesPathEnum.DATA_PAGE;
import static by.teachmeskills.eshop.utils.PagesPathEnum.START_PAGE;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.CATEGORIES_PARAM;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final CategoryService categoryService;
    private final UserService userService;

    public HomeController(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping()
    public ModelAndView getHomePage(@SessionAttribute(USER) User user) throws ServiceExceptions, RepositoryExceptions {
        ModelMap model = new ModelMap();
        List<Category> categoriesList = categoryService.read();
        model.addAttribute(CATEGORIES_PARAM.getValue(), categoriesList);
        return new ModelAndView(START_PAGE.getPath(), model);
    }

    @GetMapping("/admin")
    public ModelAndView getUploadPage() {
        return new ModelAndView(DATA_PAGE.getPath());
    }

    @GetMapping("/order/download")
    public void downloadOrderCsv(HttpServletResponse response, int userId) throws IOException, RepositoryExceptions, ServiceExceptions {
        response.setContentType("text/csv");
        response.setCharacterEncoding("UTF8");
        response.addHeader("Content-Disposition", "attachment; filename=order.csv");
        userService.downloadOrderCsvFile(response.getWriter(), userId);
    }
}