package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.entities.Category;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.exceptions.ServiceExceptions;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;

public interface CategoryService extends BaseServices<Category> {
    ModelAndView getCategoryData(int id, int pageNumber, int pageSize) throws ServiceExceptions, RepositoryExceptions;

    void downloadCsvFile(Writer writer) throws RepositoryExceptions, ServiceExceptions;

    List<Category> saveCategoriesFromCsvFile(InputStream inputStream) throws IOException;
}