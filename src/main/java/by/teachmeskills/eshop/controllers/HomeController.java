package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.entities.Category;
import by.teachmeskills.eshop.services.CategoryService;
import by.teachmeskills.eshop.services.OrderService;
import by.teachmeskills.eshop.services.UserService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.teachmeskills.eshop.utils.EshopConstants.ATTACHMENT_FILE_NAME_ORDER_CSV;
import static by.teachmeskills.eshop.utils.EshopConstants.CONTENT_DISPOSITION;
import static by.teachmeskills.eshop.utils.EshopConstants.TEXT_CSV;
import static by.teachmeskills.eshop.utils.EshopConstants.UTF8;
import static by.teachmeskills.eshop.utils.PagesPathEnum.DATA_PAGE;
import static by.teachmeskills.eshop.utils.PagesPathEnum.START_PAGE;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.CATEGORIES_PARAM;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final CategoryService categoryService;
    private final UserService userService;
    private final OrderService orderService;

    public HomeController(CategoryService categoryService, UserService userService, OrderService orderService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping()
    public ModelAndView getHomePage() {
        ModelMap model = new ModelMap();
        List<Category> categoriesList = categoryService.read();
        model.addAttribute(CATEGORIES_PARAM.getValue(), categoriesList);
        return new ModelAndView(START_PAGE.getPath(), model);
    }

    @GetMapping("/admin")
    public ModelAndView getUploadPage() {
        return new ModelAndView(DATA_PAGE.getPath());
    }

    @GetMapping("/download")
    public void downloadOrderCsv(HttpServletResponse response) throws IOException {
        response.setContentType(TEXT_CSV);
        response.setCharacterEncoding(UTF8);
        response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_FILE_NAME_ORDER_CSV);
        orderService.downloadAllOrdersToCsvFile(response.getWriter());
    }
}