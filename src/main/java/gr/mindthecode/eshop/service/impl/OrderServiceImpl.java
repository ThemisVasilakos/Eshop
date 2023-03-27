package gr.mindthecode.eshop.service.impl;

import gr.mindthecode.eshop.model.Orders;
import gr.mindthecode.eshop.repository.OrdersRepository;
import gr.mindthecode.eshop.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrdersRepository ordersRepository;

    public OrderServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public List<Orders> findByStatus() {
        return ordersRepository.findByStatus("pending");
    }

    @Override
    public Page<Orders> getOrders(String address, int page, int size, String sort) {
        PageRequest paging = PageRequest
                .of(page, size)
                .withSort(sort.equalsIgnoreCase("ASC") ?
                        Sort.by("totalCost").ascending() :
                        Sort.by("totalCost").descending());

        Page<Orders> res;
        if (address == null) {
            res = ordersRepository.findAll(paging);
        } else {
            res = ordersRepository.findByAddressContaining(address,paging);
        }

        return res;
    }
}
