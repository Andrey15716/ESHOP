package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.dto.SearchParamsDto;
import by.teachmeskills.eshop.entities.Category;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.repositories.CategoryRepository;
import by.teachmeskills.eshop.repositories.ProductRepository;
import by.teachmeskills.eshop.repositories.ProductSearchSpecification;
import by.teachmeskills.eshop.services.ProductService;
import by.teachmeskills.eshop.services.UserService;
import by.teachmeskills.eshop.utils.Assertions;
import by.teachmeskills.eshop.utils.CsvParser;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.io.Writer;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static by.teachmeskills.eshop.utils.EshopConstants.CSV_PARSER_NOT_PROVIDED;
import static by.teachmeskills.eshop.utils.EshopConstants.MAX_PRICE;
import static by.teachmeskills.eshop.utils.EshopConstants.MIN_PRICE;
import static by.teachmeskills.eshop.utils.EshopConstants.NAME;
import static by.teachmeskills.eshop.utils.EshopConstants.SEARCH_PARAM;
import static by.teachmeskills.eshop.utils.PagesPathEnum.PRODUCT_PAGE;
import static by.teachmeskills.eshop.utils.PagesPathEnum.SEARCH_PAGE;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.CATEGORY_PARAM;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.IS_FIRST_PAGE;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.IS_LAST_PAGE;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.NUMBER_OF_PAGES;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.PAGE_NUMBER;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.PAGE_SIZE;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.PRODUCT_PARAM;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.SEARCH_RESULT_PARAM;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product create(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public List<Product> read() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public void delete(int id) {
        productRepository.deleteProductById(id);
    }

    @Override
    public List<Product> getAllProductsByCategoryId(int categoryId, int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(NAME).ascending());
        Page<Product> products = productRepository.findAllByCategoryId(categoryId, paging);
        log.info("Products has been found");
        return products.getContent();
    }

    @Override
    public ModelAndView getProductsBySearchRequest(SearchParamsDto searchParamsDto, int pageNumber, int pageSize) {
        ModelMap model = new ModelMap();
        ModelAndView modelAndView = new ModelAndView();
        List<Category> categoriesList = categoryRepository.findAll();
        model.addAttribute(CATEGORY_PARAM.getValue(), categoriesList);
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(NAME).ascending());
        ProductSearchSpecification productSearchSpecification = new ProductSearchSpecification(searchParamsDto);
        Page<Product> requestProducts = productRepository.findAll(productSearchSpecification, paging);
        model.addAttribute(NUMBER_OF_PAGES.getValue(), requestProducts.getTotalPages());
        model.addAttribute(IS_FIRST_PAGE.getValue(), requestProducts.isFirst());
        model.addAttribute(IS_LAST_PAGE.getValue(), requestProducts.isLast());
        model.addAttribute(PAGE_NUMBER.getValue(), pageNumber);
        model.addAttribute(PAGE_SIZE.getValue(), pageSize);
        modelAndView.addAllObjects(model);
        List<Product> allProductsBySearch = productRepository.findAll(productSearchSpecification);
        model.addAttribute(MIN_PRICE, getMinPrice(allProductsBySearch, checkString(searchParamsDto.getMinPrice())));
        model.addAttribute(MAX_PRICE, getMaxPrice(allProductsBySearch, checkString(searchParamsDto.getMaxPrice())));
        model.addAttribute(SEARCH_PARAM, searchParamsDto);
        model.addAttribute(SEARCH_RESULT_PARAM.getValue(), requestProducts.getContent());
        model.addAttribute("totalElements", requestProducts.getTotalElements());
        return new ModelAndView(SEARCH_PAGE.getPath(), model);
    }


    private int getMinPrice(List<Product> products, int priceValue) {
        if (priceValue == 0) {
            Optional<Integer> min = products.stream().map(Product::getPrice).min(Comparator.naturalOrder());
            if (min.isPresent()) {
                return min.get();
            }
        }
        return priceValue;
    }

    private int getMaxPrice(List<Product> products, int priceValue) {
        if (priceValue == 0) {
            Optional<Integer> max = products.stream().map(Product::getPrice).max(Comparator.naturalOrder());
            if (max.isPresent()) {
                return max.get();
            }
        }
        return priceValue;
    }

    private int checkString(String str) {
        if (str.isBlank()) {
            return 0;
        } else {
            return Integer.parseInt(str);
        }
    }

    @Override
    public ModelAndView getProductById(int id) {
        ModelMap modelMap = new ModelMap();
        Product product = productRepository.getProductById(id);
        if (Optional.ofNullable(product).isPresent()) {
            modelMap.addAttribute(PRODUCT_PARAM.getValue(), product);
            log.info("User got product by id - " + id);
        }
        return new ModelAndView(PRODUCT_PAGE.getPath(), modelMap);
    }

    @Override
    public void downloadCsvFile(Writer writer) {
        List<Product> products = productRepository.findAll();
        try {
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(products);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void saveProductsFromCsvFile(InputStream inputStream) {
        Assertions.assertNonNull(inputStream, CSV_PARSER_NOT_PROVIDED);
        List<Product> productsParserCsv = CsvParser.productsParserCsv(inputStream);
        if (Optional.ofNullable(productsParserCsv).isPresent()) {
            productRepository.saveAll(productsParserCsv);
            log.info("Product csv has been saved");
        }
    }
}