package cheong.clinic_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cheong.clinic_management_system.entity.OrderDetails;


public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer>{


}
