package by.teachmeskills.eshop.repositories;

import by.teachmeskills.eshop.dto.SearchParamsDto;
import by.teachmeskills.eshop.entities.Category;
import by.teachmeskills.eshop.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import static by.teachmeskills.eshop.utils.EshopConstants.CATEGORY;
import static by.teachmeskills.eshop.utils.EshopConstants.CODE;
import static by.teachmeskills.eshop.utils.EshopConstants.NAME;
import static by.teachmeskills.eshop.utils.EshopConstants.PRICE;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductSearchSpecification implements Specification<Product> {
    private final SearchParamsDto searchParamsDto;

    public ProductSearchSpecification(SearchParamsDto searchParamsDto) {
        this.searchParamsDto = searchParamsDto;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (Optional.ofNullable(searchParamsDto.getSearchKey()).isPresent() && !searchParamsDto.getSearchKey().isBlank()) {
            predicates.add(criteriaBuilder.like(root.get(NAME), CODE + searchParamsDto.getSearchKey() + CODE));
        }

        if (!searchParamsDto.getMinPrice().isBlank()) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PRICE), searchParamsDto.getMinPrice()));
        }

        if (!searchParamsDto.getMaxPrice().isBlank()) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PRICE), searchParamsDto.getMaxPrice()));
        }

        if (Optional.ofNullable(searchParamsDto.getCategoryName()).isPresent()
                && !searchParamsDto.getCategoryName().isBlank()) {
            Join<Product, Category> productCategoryJoin = root.join(CATEGORY);
            predicates.add(criteriaBuilder.and(criteriaBuilder.equal(productCategoryJoin.get(NAME),
                    searchParamsDto.getCategoryName())));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}