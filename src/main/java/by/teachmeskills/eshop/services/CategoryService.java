package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.entities.Category;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.exceptions.ServiceExceptions;
import org.springframework.web.servlet.ModelAndView;

public interface CategoryService extends BaseServices<Category> {
    ModelAndView getCategoryData(int id, int pageNumber, int pageSize) throws ServiceExceptions, RepositoryExceptions;
}