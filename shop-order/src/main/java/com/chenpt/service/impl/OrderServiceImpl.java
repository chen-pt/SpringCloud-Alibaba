package com.chenpt.service.impl;

import com.chenpt.dao.OrderDao;
import com.chenpt.domain.Order;
import com.chenpt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: chenpt
 * @Description:
 * @Date: Created in 2020-08-03 11:36
 * @Modified By:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;


    @Override
    public void save(Order order) {
        orderDao.save(order);
    }
}
