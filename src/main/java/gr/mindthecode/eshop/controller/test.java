package gr.mindthecode.eshop.controller;

import gr.mindthecode.eshop.dto.NewOrderDto;
import gr.mindthecode.eshop.dto.ProductQuantity;
import gr.mindthecode.eshop.model.Orders;
import gr.mindthecode.eshop.model.Product;
import gr.mindthecode.eshop.model.ShoppingCart;
import gr.mindthecode.eshop.service.OrderService;
import gr.mindthecode.eshop.service.ProductService;
import gr.mindthecode.eshop.service.ShoppingCartService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
public class test {

    private ProductService productService;
    private ShoppingCartService shoppingCartService;
    private OrderService orderService;

    public test(ProductService productService,ShoppingCartService shoppingCartService,OrderService orderService) {
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
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

    @PostMapping("/product/add")
    public ShoppingCart getCart(@RequestParam Integer productId
            , @RequestParam(defaultValue = "0") int quantity){
        return shoppingCartService.addToCart(productId,quantity);
    }

    @GetMapping("/orders")
    public List<Orders> getOrders(){
        return orderService.findByStatus();
    }

    @PostMapping("/cart/checkout")
    public Orders sendOrder(@RequestParam String address){
        return shoppingCartService.sendOrder(address);
    }

    @GetMapping("/cart")
    public NewOrderDto getCart(){
        return shoppingCartService.getCart();
    }

}
