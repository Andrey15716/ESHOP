package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.entities.Category;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.repositories.CategoryRepository;
import by.teachmeskills.eshop.services.CategoryService;
import by.teachmeskills.eshop.services.ProductService;
import by.teachmeskills.eshop.utils.Assertions;
import by.teachmeskills.eshop.utils.CsvParser;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.io.Writer;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static by.teachmeskills.eshop.utils.PagesPathEnum.CATEGORY_PAGE;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.CATEGORY_PARAM;

@Slf4j
@Service
public class CategoryServiceImpl<C> implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductService productService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    @Override
    public Category create(Category entity) {
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
    public ModelAndView getCategoryData(int id, int pageNumber, int pageSize) {
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

    @Override
    public void downloadCsvFile(Writer writer) {
        List<Category> categories = categoryRepository.findAll();
        try {
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(categories);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public List<Category> saveCategoriesFromCsvFile(InputStream inputStream) {
        Assertions.assertNonNull(inputStream, "CSV parser not provided");
        List<Category> categoryParserCsv = CsvParser.categoryParserCsv(inputStream);
        if (Optional.ofNullable(categoryParserCsv).isPresent()) {
            categoryRepository.saveAll(categoryParserCsv);
        }
        return Collections.emptyList();
    }
}