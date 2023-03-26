package gr.mindthecode.eshop.controller;

import gr.mindthecode.eshop.dto.ProductDTO;
import gr.mindthecode.eshop.model.Category;
import gr.mindthecode.eshop.model.Product;
import gr.mindthecode.eshop.service.CategoryService;
import gr.mindthecode.eshop.service.ProductService;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@RestController
public class test {

    private ProductService productService;
    private CategoryService categoryService;

    public test(ProductService productService,CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/hellouser")
    public String getUser(){
        return "Hello User";
    }

    @GetMapping("/helloadmin")
    public String getAdmin(){
        return "Hello Admin";
    }

    @GetMapping("/hellomod")
    public String getMod(){
        return "Hello Moderator";
    }

    @PostMapping("/product")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO){

        return productService.createProduct(productDTO);
    }

    @GetMapping("/category")
    public List<Category> getCat(){
        return categoryService.getCategories();
    }
}
