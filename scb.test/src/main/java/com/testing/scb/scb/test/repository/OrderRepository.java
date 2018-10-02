package com.testing.scb.scb.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.testing.scb.scb.test.model.OrdersModel;

@Repository
public interface OrderRepository extends JpaRepository<OrdersModel, Object>  {
	
	@Query(value = "Select price from order Where users = ?1  AND orderId = ?2", nativeQuery = true)
	public OrdersModel findByUserIdAndOrderId(String users , Integer orderId);

}
