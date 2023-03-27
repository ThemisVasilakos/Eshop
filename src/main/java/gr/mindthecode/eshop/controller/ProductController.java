package gr.mindthecode.eshop.controller;

import gr.mindthecode.eshop.model.Product;
import gr.mindthecode.eshop.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create-or-update")
    public Product createProduct(@RequestBody Product product){
        try {
            return productService.createOrUpdateProduct(product.getProductId(), product);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400), e.getMessage());
        }
    }

    @DeleteMapping ("/delete/{id}")
    public void deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Integer id) {
        return productService.getProductById(id);
    }

    @GetMapping("/all")
    public Page<Product> getProducts(
            @RequestParam(required = false) String productDescription,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort
    ){
        return productService.getProducts(productDescription,category,page,size,sort);
    }
}
