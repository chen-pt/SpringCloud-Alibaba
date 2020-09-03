package com.chenpt.dao;

import com.chenpt.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: chenpt
 * @Description:
 * @Date: Created in 2020-08-03 11:34
 * @Modified By:
 */
public interface OrderDao extends JpaRepository<Order,Integer> {
}
