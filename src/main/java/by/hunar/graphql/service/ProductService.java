package by.hunar.graphql.service;

import by.hunar.graphql.converter.ProductCategoryConverter;
import by.hunar.graphql.converter.ProductConverter;
import by.hunar.graphql.entity.ProductCategoryEntity;
import by.hunar.graphql.entity.ProductEntity;
import by.hunar.graphql.model.Product;
import by.hunar.graphql.model.ProductCategory;
import by.hunar.graphql.repository.ProductCategoryRepository;
import by.hunar.graphql.repository.ProductRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository categoryRepository;
    private final ProductConverter productConverter;

    public ProductService(ProductRepository productRepository, ProductCategoryRepository categoryRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productConverter = productConverter;
    }

    public Product storeProduct(String productName, Integer categoryId, Float price) {
        Optional<ProductCategoryEntity> categoryEntity = categoryRepository.findById(categoryId);
        if (categoryEntity.isEmpty()) {
            throw new RuntimeException("ProductCategory not found");
        }
        Product product = new Product(null, productName, price, new ProductCategory(categoryId, null), null);
        ProductEntity entity = productConverter.convertToEntity(product);
        entity.setCategory(categoryEntity.get());
        return productConverter.convertToDto(productRepository.save(entity));
    }

    public Set<Product> getAllProducts() {
        Set<Product> result = new HashSet<>();
        productRepository.findAll().forEach(entity -> result.add(productConverter.convertToDto(entity)));

        return result;
    }

    public ProductCategory storeProductCategory(ProductCategory category) {
        ProductCategoryConverter categoryConverter = productConverter.categoryConverter();
        ProductCategoryEntity storedCategory = categoryRepository.save(categoryConverter.convertToEntity(category));
        return categoryConverter.convertToDto(storedCategory);
    }
}
