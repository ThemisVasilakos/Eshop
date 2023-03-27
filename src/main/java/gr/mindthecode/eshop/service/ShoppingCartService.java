package gr.mindthecode.eshop.service;


import gr.mindthecode.eshop.dto.NewOrderDto;
import gr.mindthecode.eshop.dto.ProductQuantity;
import gr.mindthecode.eshop.model.Orders;
import gr.mindthecode.eshop.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    NewOrderDto getCart();
    ShoppingCart addToCart(Integer productId,int quantity);
    Orders  sendOrder(String address);
}
