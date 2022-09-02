package by.teachmeskills.eshop.utils;

import by.teachmeskills.eshop.entities.Category;
import by.teachmeskills.eshop.entities.Product;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Component
public class CsvParser {

    public static List<Category> categoryParserCsv(InputStream inputStream) {
        Reader reader = new BufferedReader(new InputStreamReader(inputStream));
        CsvToBean<Category> csvToBean = new CsvToBeanBuilder(reader)
                .withType(Category.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSeparator(',')
                .build();
        return csvToBean.parse();
    }

    public static List<Product> productsParserCsv(InputStream inputStream) {
        Reader reader = new BufferedReader(new InputStreamReader(inputStream));
        CsvToBean<Product> csvToBean = new CsvToBeanBuilder(reader)
                .withType(Product.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSeparator(',')
                .build();
        return csvToBean.parse();
    }
}
