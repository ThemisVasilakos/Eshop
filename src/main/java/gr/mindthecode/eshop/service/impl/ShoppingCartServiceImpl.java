package gr.mindthecode.eshop.service.impl;

import gr.mindthecode.eshop.model.*;
import gr.mindthecode.eshop.repository.OrdersRepository;
import gr.mindthecode.eshop.repository.ProductRepository;
import gr.mindthecode.eshop.repository.ShoppingCartRepository;
import gr.mindthecode.eshop.repository.UserRepository;
import gr.mindthecode.eshop.service.ShoppingCartService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ProductRepository productRepository;
    private OrdersRepository ordersRepository;
    private ShoppingCartRepository shoppingCartRepository;
    private UserRepository userRepository;


    public ShoppingCartServiceImpl(ProductRepository productRepository, OrdersRepository ordersRepository,
                                   ShoppingCartRepository shoppingCartRepository,
                                   UserRepository userRepository) {
        this.productRepository = productRepository;
        this.ordersRepository = ordersRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public ShoppingCart addToCart(Integer productId, int quantity) {
        UserDetails userDetailService = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetailService.getUsername();
        User user =  userRepository.findByUsername(username);

        Optional<Product> check = productRepository.findById(productId);
        if(check.isEmpty()){
            throw new RuntimeException("Product doesnot exist");
        }

        double totalCost;
        ShoppingCartPK shoppingCartPK = new ShoppingCartPK();
        ShoppingCart shoppingCart = new ShoppingCart();

        Orders finalOrder;
        Optional<Orders> order = ordersRepository.findByStatusAndUsers("pending",user);
        if(order.isPresent()){
            finalOrder = order.get();
            totalCost = finalOrder.getTotalCost();
            totalCost+=check.get().getProductPrice()*quantity;
            finalOrder.setTotalCost(totalCost);
            ordersRepository.save(finalOrder);
        }else{
            finalOrder = new Orders();
            finalOrder.setUser(user);
            finalOrder.setStatus("pending");
            totalCost=check.get().getProductPrice()*quantity;
            finalOrder.setTotalCost(totalCost);
            ordersRepository.save(finalOrder);
        }

        shoppingCartPK.setOrdersId(finalOrder.getOrdersId());
        shoppingCartPK.setProductId(check.get().getProductId());

        shoppingCart.setId(shoppingCartPK);
        shoppingCart.setOrder(finalOrder);
        shoppingCart.setProducts(check.get());
        shoppingCart.setQuantity(quantity);
        shoppingCart.setUser(user);

        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public Orders sendOrder(String address) {
        UserDetails userDetailService = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetailService.getUsername();
        User user =  userRepository.findByUsername(username);

        Optional<Orders> order = ordersRepository.findByStatusAndUsers("pending",user);
        if(order.isEmpty()){
            throw new RuntimeException("Order not found");
        }
        order.get().setAddress(address);
        order.get().setStatus("completed");
        ordersRepository.save(order.get());

        return order.get();
    }

    @Override
    public List<ShoppingCart> findAll() {
        return shoppingCartRepository.findAll();
    }

}
