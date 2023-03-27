package gr.mindthecode.eshop.service;

import gr.mindthecode.eshop.model.Orders;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    List<Orders> findAll();
    List<Orders> findByStatus();
    Page<Orders> getOrders(String address,
                           int page,
                           int size,
                           String sort);
}
