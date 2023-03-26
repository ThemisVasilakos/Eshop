package gr.mindthecode.eshop.repository;

import gr.mindthecode.eshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findByCategoryDescription(String description);
}
