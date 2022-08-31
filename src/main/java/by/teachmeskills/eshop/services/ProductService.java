package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.dto.SearchParamsDto;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.exceptions.ServiceExceptions;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface ProductService extends BaseServices<Product> {
    List<Product> getAllProductsByCategoryId(int categoryId, int pageNumber, int pageSize) throws ServiceExceptions, RepositoryExceptions;

    ModelAndView getProductsBySearchRequest(SearchParamsDto searchParamsDto, int pageNumber, int pageSize) throws ServiceExceptions, RepositoryExceptions;

    ModelAndView getProductById(int id) throws ServiceExceptions, RepositoryExceptions;
}