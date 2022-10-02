package by.teachmeskills.eshop.dto.converter;

import by.teachmeskills.eshop.dto.ProductDto;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.repositories.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductConverter {
    private final CategoryRepository categoryRepository;

    public ProductConverter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ProductDto toDto(Product product) {
        return Optional.ofNullable(product).map(p -> ProductDto.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .description(p.getDescription())
                        .price(p.getPrice())
                        .categoryId(p.getCategory().getId())
                        .imageName(p.getImageName())
                        .build()).
                orElse(null);
    }

    public Product fromDto(ProductDto productDto) {
        return Optional.ofNullable(productDto).map(pd -> Product.builder()
                        .name(pd.getName())
                        .description(pd.getDescription())
                        .price(pd.getPrice())
                        .category(categoryRepository.getCategoryById(pd.getCategoryId()))
                        .imageName(pd.getImageName())
                        .build()).
                orElse(null);
    }
}