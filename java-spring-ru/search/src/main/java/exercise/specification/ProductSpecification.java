package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component
public class ProductSpecification {
    public Specification<Product> build(ProductParamsDTO params) {
        return withCategoryId(params.getCategoryId()).and(withPriceGr(params.getPriceGt())).and(withPriceLe(params.getPriceLt())).and(withRatingGt(params.getRatingGt())).and(withTitleCont(params.getTitleCont()));
    }

    public Specification<Product> withCategoryId(Long categoryId) {
        Specification<Product> productSpecification = (root, query, criteriaBuilder) -> categoryId == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("category").get("id"), categoryId);
        return productSpecification;
    }

    public Specification<Product> withPriceGr(Integer priceGr) {
        return ((root, query, criteriaBuilder) -> priceGr == null ? criteriaBuilder.conjunction() : criteriaBuilder.greaterThan(root.get("price"), priceGr));
    }

    public Specification<Product> withPriceLe(Integer priceLt) {
        return (root, query, criteriaBuilder) -> priceLt == null ? criteriaBuilder.conjunction() : criteriaBuilder.lessThan(root.get("price"), priceLt);
    }

    public Specification<Product> withRatingGt(Double ratingGt) {
        return (root, query, criteriaBuilder) -> ratingGt == null ? criteriaBuilder.conjunction() : criteriaBuilder.greaterThan(root.get("rating"), ratingGt);
    }

    public Specification<Product> withTitleCont(String titleCont) {
        return (root, query, criteriaBuilder) -> titleCont == null ? criteriaBuilder.conjunction() : criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + titleCont + "%");
    }
}
// END
