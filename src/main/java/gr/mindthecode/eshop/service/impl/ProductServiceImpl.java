package gr.mindthecode.eshop.service.impl;

import gr.mindthecode.eshop.dto.ProductDTO;
import gr.mindthecode.eshop.model.Category;
import gr.mindthecode.eshop.model.Product;
import gr.mindthecode.eshop.repository.CategoryRepository;
import gr.mindthecode.eshop.repository.ProductRepository;
import gr.mindthecode.eshop.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO){

        Product newPro = new Product();
        Optional<Category> category = categoryRepository.findByCategoryDescription(productDTO.getCategoryDescription());
        Category newCat = new Category();
        newCat.setCategoryDescription(productDTO.getCategoryDescription());

        if(category.isEmpty()){
            categoryRepository.save(newCat);
        }

        newPro.setProductDescription(productDTO.getProductDescription());
        newPro.setProductPrice(productDTO.getProductPrice());
        newPro.setCategory(category.get());

        productRepository.save(newPro);

        return productDTO;
    }

    @Override
    public void deleteProduct(Integer id) {
        Product match = productRepository.findById(id).orElseThrow();
        productRepository.delete(match);
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findByProductId(id);
    }

    @Override
    public Page<Product> getProducts(String description, int page, int size, String sort) {
        PageRequest paging = PageRequest
                .of(page, size)
                .withSort(sort.equalsIgnoreCase("ASC") ?
                        Sort.by("productPrice").ascending() :
                        Sort.by("productPrice").descending());

        Page<Product> res;
        if (description == null) {
            res = productRepository.findAll(paging);
        } else {
            res = productRepository.findByProductDescriptionContaining(description,paging);
        }

        return res;
    }
}
