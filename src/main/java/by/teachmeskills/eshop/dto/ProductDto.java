package by.teachmeskills.eshop.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@RequiredArgsConstructor
public class ProductDto {
    @CsvBindByName(column = "ID")
    private int id;
    @CsvBindByName(column = "NAME")
    private String name;
    @CsvBindByName(column = "DESCRIPTION")
    private String description;
    @CsvBindByName(column = "PRICE")
    private int price;
    @CsvBindByName(column = "CATEGORY_ID")
    private int categoryId;
    @CsvBindByName(column = "IMAGE_NAME")
    private String imageName;
}