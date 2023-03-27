package gr.mindthecode.eshop.controller;

import gr.mindthecode.eshop.model.Orders;
import gr.mindthecode.eshop.repository.OrdersRepository;
import gr.mindthecode.eshop.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public List<Orders> getAllOrders(){
        return orderService.findAll();
    }

    @GetMapping("/status/completed")
    public List<Orders> getOrdersByStatus(){
        return orderService.findByStatus();
    }

    @GetMapping("/index")
    public Page<Orders> getOrderIndex(
            @RequestParam(required = false) String orderAddress,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort
    ){
        return orderService.getOrders(orderAddress, page, size, sort);
    }
}
