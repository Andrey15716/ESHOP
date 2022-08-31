package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.entities.Category;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.exceptions.ServiceExceptions;
import by.teachmeskills.eshop.repositories.CategoryRepository;
import by.teachmeskills.eshop.services.CategoryService;
import by.teachmeskills.eshop.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import static by.teachmeskills.eshop.utils.PagesPathEnum.CATEGORY_PAGE;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.CATEGORY_PARAM;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductService productService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    @Override
    public Category create(Category entity) throws ServiceExceptions, RepositoryExceptions {
        return categoryRepository.save(entity);
    }

    @Override
    public List<Category> read() {
        return categoryRepository.findAll();
    }

    @Override
    public Category update(Category entity) {
        return categoryRepository.save(entity);
    }

    @Override
    public void delete(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public ModelAndView getCategoryData(int id, int pageNumber, int pageSize) throws ServiceExceptions, RepositoryExceptions {
        ModelMap modelMap = new ModelMap();
        Category category = categoryRepository.getCategoryById(id);
        if (Optional.ofNullable(category).isPresent()) {
            List<Product> products = productService.getAllProductsByCategoryId(category.getId(), pageNumber, pageSize);
            category.setProductList(products);
            modelMap.addAttribute(CATEGORY_PARAM.getValue(), category);
            log.info("User got categoryData by id - " + id);
        }
        return new ModelAndView(CATEGORY_PAGE.getPath(), modelMap);
    }
}