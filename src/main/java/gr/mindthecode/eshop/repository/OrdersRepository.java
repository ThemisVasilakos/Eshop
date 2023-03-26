package gr.mindthecode.eshop.repository;

import gr.mindthecode.eshop.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,Integer> {
    Page<Orders> findByAddressContaining(String address, Pageable pageable);
    List<Orders> findAll();
}
