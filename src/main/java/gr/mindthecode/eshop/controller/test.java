package gr.mindthecode.eshop.controller;

import gr.mindthecode.eshop.model.Product;
import gr.mindthecode.eshop.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class test {

    private ProductService productService;

    public test(ProductService productService) {
        this.productService = productService;
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

    @GetMapping("/product")
    public Page<Product> getProducts(
            @RequestParam(required = false) String productDescription,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort
    ){

        return productService.getProducts(productDescription,category,page,size,sort);
    }

    @PostMapping("/product")
    public Product createProduct(@RequestBody Product product){
        try {
            return productService.createOrUpdateProduct(product.getProductId(), product);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400), e.getMessage());
        }
    }

}
