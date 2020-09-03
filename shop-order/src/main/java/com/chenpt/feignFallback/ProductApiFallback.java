package com.chenpt.feignFallback;

import com.chenpt.domain.Product;
import com.chenpt.feginApi.ProductApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *
 * 容错类要求必须实现被容错的接口,并为每个方法实现容错方案
 *
 * @Author: chenpt
 * @Description:
 * @Date: Created in 2020-08-19 15:29
 * @Modified By:
 */
@Component
@Slf4j
public class ProductApiFallback implements ProductApiService {
    @Override
    public Product getByPid(Integer pid) {
        Product product = new Product();
        product.setPname("服务容错");
        product.setPid(-1);
        return product;
    }
}
