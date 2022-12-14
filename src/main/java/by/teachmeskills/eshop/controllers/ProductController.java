package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.services.ProductService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import java.io.IOException;

import static by.teachmeskills.eshop.utils.EshopConstants.ATTACHMENT_FILE_NAME_PRODUCT_CSV;
import static by.teachmeskills.eshop.utils.EshopConstants.CONTENT_DISPOSITION;
import static by.teachmeskills.eshop.utils.EshopConstants.FILE;
import static by.teachmeskills.eshop.utils.EshopConstants.TEXT_CSV;
import static by.teachmeskills.eshop.utils.EshopConstants.UTF8;

@RestController
@Validated
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ModelAndView openProductPage(@NotNull(message = "ProductId should not be empty") @PathVariable int id) {
        return productService.getProductById(id);
    }

    @GetMapping("/download")
    public void downloadProductsCsv(HttpServletResponse response) throws IOException {
        response.setContentType(TEXT_CSV);
        response.setCharacterEncoding(UTF8);
        response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_FILE_NAME_PRODUCT_CSV);
        productService.downloadCsvFile(response.getWriter());
    }

    @PostMapping("/upload")
    public void uploadProductsCsv(@RequestParam(FILE) MultipartFile file) throws IOException {
        productService.saveProductsFromCsvFile(file.getInputStream());
    }
}