package gr.mindthecode.eshop.service;

import gr.mindthecode.eshop.dto.NewOrderDto;
import gr.mindthecode.eshop.dto.ProductQuantity;
import gr.mindthecode.eshop.model.ShoppingCart;

import java.util.Collection;
import java.util.List;

public interface ShoppingCartService {
    NewOrderDto createOrder(NewOrderDto newOrderDto);
    List<ShoppingCart> findAll();
    void addProductQuantity(Integer id);
    void buildNewOrderDto(String address, Collection<ProductQuantity> productQuantity);
    NewOrderDto getNewOrderDto();
    Collection<ProductQuantity> getProductQuantity();
    void reset();
}
