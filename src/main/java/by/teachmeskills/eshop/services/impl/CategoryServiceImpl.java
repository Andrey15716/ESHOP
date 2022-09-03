package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.entities.Category;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.repositories.CategoryRepository;
import by.teachmeskills.eshop.repositories.ProductRepository;
import by.teachmeskills.eshop.services.CategoryService;
import by.teachmeskills.eshop.services.ProductService;
import by.teachmeskills.eshop.utils.Assertions;
import by.teachmeskills.eshop.utils.CsvParser;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.io.Writer;
import java.util.List;
import java.util.Optional;

import static by.teachmeskills.eshop.utils.EshopConstants.CSV_PARSER_NOT_PROVIDED;
import static by.teachmeskills.eshop.utils.EshopConstants.NAME;
import static by.teachmeskills.eshop.utils.PagesPathEnum.CATEGORY_PAGE;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.CATEGORY_PARAM;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.IS_FIRST_PAGE;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.IS_LAST_PAGE;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.NUMBER_OF_PAGES;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.PAGE_NUMBER;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.PAGE_SIZE;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.PRODUCTS;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductService productService;
    private final ProductRepository productRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductService productService, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
        this.productRepository = productRepository;
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
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(NAME));
        Page<Product> pageProducts = productRepository.findAllByCategoryId(id, paging);
        List<Product> products = productService.getAllProductsByCategoryId(category.getId(), pageNumber, pageSize);
        category.setProductList(products);
        modelMap.addAttribute(PRODUCTS.getValue(), pageProducts.getContent());
        modelMap.addAttribute(CATEGORY_PARAM.getValue(), category);
        modelMap.addAttribute(PAGE_NUMBER.getValue(), pageNumber);
        modelMap.addAttribute(PAGE_SIZE.getValue(), pageSize);
        modelMap.addAttribute(NUMBER_OF_PAGES.getValue(), pageProducts.getTotalPages());
        modelMap.addAttribute(IS_FIRST_PAGE.getValue(), pageProducts.isFirst());
        modelMap.addAttribute(IS_LAST_PAGE.getValue(), pageProducts.isLast());
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
    public void saveCategoriesFromCsvFile(InputStream inputStream) {
        Assertions.assertNonNull(inputStream, CSV_PARSER_NOT_PROVIDED);
        List<Category> categoryParserCsv = CsvParser.categoryParserCsv(inputStream);
        if (Optional.ofNullable(categoryParserCsv).isPresent()) {
            categoryRepository.saveAll(categoryParserCsv);
            log.info("Category csv has been successfully saved");
        }
    }
}