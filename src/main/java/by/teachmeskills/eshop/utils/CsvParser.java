package by.teachmeskills.eshop.utils;

import by.teachmeskills.eshop.dto.ProductDto;
import by.teachmeskills.eshop.dto.converter.ProductConverter;
import by.teachmeskills.eshop.entities.Category;
import by.teachmeskills.eshop.entities.Product;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CsvParser {
    private static ProductConverter productConverter;

    public CsvParser(ProductConverter productConverter) {
        CsvParser.productConverter = productConverter;
    }

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
        if (Optional.ofNullable(inputStream).isPresent()) {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream));
            CsvToBean<ProductDto> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(ProductDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(',')
                    .build();
            List<ProductDto> productDtos = csvToBean.parse();
            return Optional.ofNullable(productDtos)
                    .map(list -> list.stream().map(productConverter::fromDto)
                            .collect(Collectors.toList())).orElse(null);
        } else {
            log.info("Empty csv file presented");
        }
        return Collections.emptyList();
    }
}