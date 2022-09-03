package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.dto.SearchParamsDto;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.repositories.ProductRepository;
import by.teachmeskills.eshop.repositories.ProductSearchSpecification;
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
import static by.teachmeskills.eshop.utils.EshopConstants.SEARCH_PARAM;
import static by.teachmeskills.eshop.utils.PagesPathEnum.PRODUCT_PAGE;
import static by.teachmeskills.eshop.utils.PagesPathEnum.SEARCH_PAGE;
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

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
        ModelMap modelMap = new ModelMap();
        ProductSearchSpecification productSearchSpecification = new ProductSearchSpecification(searchParamsDto);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(NAME));
        Page<Product> productsPage = productRepository.findAll(productSearchSpecification, pageable);
        List<Product> productListResult = productRepository.findAll(productSearchSpecification);
        modelMap.addAttribute(SEARCH_RESULT_PARAM.getValue(), productListResult);
        modelMap.addAttribute(NUMBER_OF_PAGES.getValue(), productsPage.getTotalPages());
        modelMap.addAttribute(PAGE_SIZE.getValue(), pageSize);
        modelMap.addAttribute(IS_FIRST_PAGE.getValue(), productsPage.isFirst());
        modelMap.addAttribute(PAGE_NUMBER.getValue(), pageNumber);
        modelMap.addAttribute(IS_LAST_PAGE.getValue(), productsPage.isLast());
        modelMap.addAttribute(SEARCH_PARAM, searchParamsDto);
        log.info("User got product by search request - " + searchParamsDto.getSearchKey());
        return new ModelAndView(SEARCH_PAGE.getPath(), modelMap);
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