package gr.mindthecode.eshop.service.impl;

import gr.mindthecode.eshop.dto.NewOrderDto;
import gr.mindthecode.eshop.model.Orders;
import gr.mindthecode.eshop.model.User;
import gr.mindthecode.eshop.repository.OrdersRepository;
import gr.mindthecode.eshop.repository.UserRepository;
import gr.mindthecode.eshop.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrdersRepository ordersRepository;
    private UserRepository userRepository;

    public OrderServiceImpl(OrdersRepository ordersRepository,UserRepository userRepository){
        this.ordersRepository = ordersRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Page<Orders> findByStatus(int page, int size, String sort) {
        PageRequest paging = PageRequest
                .of(page, size)
                .withSort(sort.equalsIgnoreCase("ASC") ?
                        Sort.by("totalCost").ascending() :
                        Sort.by("totalCost").descending());

        Page<Orders> res = ordersRepository.findByStatus("submitted",paging);
        return res;
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

    public List<Orders> getMyOrders(){
        UserDetails userDetailService = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetailService.getUsername();
        User user =  userRepository.findByUsername(username);

        List<Orders> myOrders = ordersRepository.findOrdersByStatusAndUsers("submitted",user);

        return myOrders;
    }
}
