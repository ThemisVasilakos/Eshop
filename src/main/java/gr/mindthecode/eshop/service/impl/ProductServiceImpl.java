package gr.mindthecode.eshop.service.impl;

import gr.mindthecode.eshop.model.Product;
import gr.mindthecode.eshop.repository.ProductRepository;
import gr.mindthecode.eshop.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createOrUpdateProduct(Integer id,Product product) throws Exception {
        if (id != null) {
            if (!id.equals(product.getProductId())) {
                throw new Exception("id in path does not patch id in body");
            }
        }
        return productRepository.save(product);
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
    public Page<Product> getProducts(String description,String category,int page, int size, String sort) {
        PageRequest paging = PageRequest
                .of(page, size)
                .withSort(sort.equalsIgnoreCase("ASC") ?
                        Sort.by("productPrice").ascending() :
                        Sort.by("productPrice").descending());

        Page<Product> res = null;
        if (description == null && category == null) {
            res = productRepository.findAll(paging);
        }
        if (description != null && category == null) {
            res = productRepository.findByProductDescriptionContaining(description, paging);
        }
        if (category != null && description == null) {
            res = productRepository.findByCategoryContaining(category,paging);
        }
        if (category != null && description != null) {
            res = productRepository.findByCategoryContainingAndProductDescriptionContaining(category, description, paging);
        }

        return res;
    }
}
