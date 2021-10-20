package by.hunar.graphql.converter;

import by.hunar.graphql.entity.ProductEntity;
import by.hunar.graphql.model.Product;
import by.hunar.graphql.repository.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public record ProductConverter(
    ProductRepository repository,
    ProductCategoryConverter categoryConverter) {

    public Product convertToDto(ProductEntity entity) {
        return new Product(entity.getId(), entity.getName(), entity.getPrice(), categoryConverter.convertToDto(entity.getCategory()), null);
    }

    public ProductEntity convertToEntity(Product product) {
        ProductEntity entity;
        if(product.id() != null && repository.findById(product.id()).isPresent()) {
            entity = repository.findById(product.id()).get();

        } else {
            entity = new ProductEntity();
            entity.setId(product.id());
        }
        entity.setName(product.name());
        entity.setPrice(product.price());
        entity.setCategory(categoryConverter.repository().findById(product.category().id()).get());
        return entity;
    }
}
