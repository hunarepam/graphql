package by.hunar.graphql.converter;

import by.hunar.graphql.entity.ProductCategoryEntity;
import by.hunar.graphql.model.ProductCategory;
import by.hunar.graphql.repository.ProductCategoryRepository;
import org.springframework.stereotype.Component;

@Component
public record ProductCategoryConverter(ProductCategoryRepository repository) {
    public ProductCategory convertToDto(ProductCategoryEntity entity) {
        return new ProductCategory(entity.getId(), entity.getName());
    }

    public ProductCategoryEntity convertToEntity(ProductCategory productCategory) {
        ProductCategoryEntity entity;
        if (productCategory.id() != null && repository.findById(productCategory.id()).isPresent()) {
            entity = repository.findById(productCategory.id()).get();

        } else {
            entity = new ProductCategoryEntity();
            entity.setId(productCategory.id());
        }
        entity.setName(productCategory.name());
        return entity;
    }
}
