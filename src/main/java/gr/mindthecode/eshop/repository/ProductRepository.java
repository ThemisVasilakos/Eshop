package gr.mindthecode.eshop.repository;

import gr.mindthecode.eshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findByProductDescriptionContaining(String description, Pageable pageable);
    Page<Product> findByCategoryContaining(String category, Pageable pageable);
    Page<Product> findByCategoryContainingAndProductDescriptionContaining(String category,String description,Pageable pageable);
    Product findByProductId(Integer id);
    List<Product> findAll();
}
