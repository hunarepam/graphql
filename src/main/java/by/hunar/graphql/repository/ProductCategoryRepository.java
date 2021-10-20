package by.hunar.graphql.repository;

import by.hunar.graphql.entity.ProductCategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends CrudRepository<ProductCategoryEntity, Integer> {
}
