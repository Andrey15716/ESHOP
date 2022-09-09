package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.dto.SearchParamsDto;
import by.teachmeskills.eshop.entities.Product;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.io.Writer;
import java.util.List;

public interface ProductService extends BaseServices<Product> {
    List<Product> getAllProductsByCategoryId(int categoryId, int pageNumber, int pageSize);

    ModelAndView getProductsBySearchRequest(SearchParamsDto searchParamsDto, int pageNumber, int pageSize);

//    ModelAndView getSearchData(int pageNumber, int pageSize);

    ModelAndView getProductById(int id);

    void downloadCsvFile(Writer writer);

    void saveProductsFromCsvFile(InputStream inputStream);
}