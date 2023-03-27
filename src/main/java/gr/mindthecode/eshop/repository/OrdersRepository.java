package gr.mindthecode.eshop.repository;

import gr.mindthecode.eshop.model.Orders;
import gr.mindthecode.eshop.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders,Integer> {
    Page<Orders> findByAddressContaining(String address, Pageable pageable);
    Optional<Orders> findByStatusAndUsers(String status, User user);
    List<Orders> findByStatus(String status);
    List<Orders> findAll();
}
