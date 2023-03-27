package gr.mindthecode.eshop.service;


import gr.mindthecode.eshop.model.Orders;
import gr.mindthecode.eshop.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCart> findAll();
    ShoppingCart addToCart(Integer productId,int quantity);
    Orders  sendOrder(String address);
}
