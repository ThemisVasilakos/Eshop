package gr.mindthecode.eshop.service;

import gr.mindthecode.eshop.dto.ProductDTO;
import gr.mindthecode.eshop.model.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductDTO createProduct(ProductDTO product);
    void deleteProduct(Integer id);
    public abstract Product getProductById(Integer id);
    Page<Product> getProducts(String description,
                              int page,
                              int size,
                              String sort);
}
