package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.dto.SearchParamsDto;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.exceptions.ServiceExceptions;
import by.teachmeskills.eshop.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import static by.teachmeskills.eshop.utils.PagesPathEnum.SEARCH_PAGE;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final ProductService productService;

    public SearchController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ModelAndView openSearchPage() {
        return new ModelAndView(SEARCH_PAGE.getPath());
    }

    @PostMapping
    public ModelAndView getSearchPageResult(@ModelAttribute SearchParamsDto searchParamsDto,
                                            @RequestParam(defaultValue = "0") int pageNumber,
                                            @RequestParam(defaultValue = "3") int pageSize) throws RepositoryExceptions, ServiceExceptions {
        return productService.getProductsBySearchRequest(searchParamsDto, pageNumber, pageSize);
    }
}