package gr.mindthecode.eshop.controller;

import gr.mindthecode.eshop.model.Orders;
import gr.mindthecode.eshop.repository.OrdersRepository;
import gr.mindthecode.eshop.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eshop")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public Page<Orders> getOrdersByStatus(
            @RequestParam(required = false) String address,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort
    ){
        return orderService.getOrders(address,page, size, sort);
    }

    @GetMapping("/orders/user")
    public List<Orders> getMyOrders(){
        return orderService.getMyOrders();
    }

}
