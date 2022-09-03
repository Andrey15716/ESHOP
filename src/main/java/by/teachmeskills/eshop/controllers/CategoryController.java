package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.teachmeskills.eshop.utils.EshopConstants.ATTACHMENT_FILE_NAME_CATEGORY_CSV;
import static by.teachmeskills.eshop.utils.EshopConstants.CONTENT_DISPOSITION;
import static by.teachmeskills.eshop.utils.EshopConstants.FILE;
import static by.teachmeskills.eshop.utils.EshopConstants.TEXT_CSV;
import static by.teachmeskills.eshop.utils.EshopConstants.UTF8;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ModelAndView openCategoryPage(@PathVariable int id,
                                         @RequestParam(defaultValue = "0") int pageNumber,
                                         @RequestParam(defaultValue = "5") int pageSize) {
        return categoryService.getCategoryData(id, pageNumber, pageSize);
    }

    @GetMapping("/download")
    public void downloadCategoryCsv(HttpServletResponse response) throws IOException {
        response.setContentType(TEXT_CSV);
        response.setCharacterEncoding(UTF8);
        response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_FILE_NAME_CATEGORY_CSV);
        categoryService.downloadCsvFile(response.getWriter());
    }

    @PostMapping("/upload")
    public void uploadCategoryCsv(@RequestParam(FILE) MultipartFile file) throws IOException {
        categoryService.saveCategoriesFromCsvFile(file.getInputStream());
    }
}