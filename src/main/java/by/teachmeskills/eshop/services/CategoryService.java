package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.entities.Category;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.io.Writer;

public interface CategoryService extends BaseServices<Category> {
    ModelAndView getCategoryData(int id, int pageNumber, int pageSize);

    void downloadCsvFile(Writer writer);

    void saveCategoriesFromCsvFile(InputStream inputStream);
}