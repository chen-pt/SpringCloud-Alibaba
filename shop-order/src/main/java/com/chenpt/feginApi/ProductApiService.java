package com.chenpt.feginApi;

import com.chenpt.domain.Product;
import com.chenpt.feignFallback.ProductApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-product",fallback = ProductApiFallback.class)//声明调用的提供者的name
public interface ProductApiService {

    /**
     * 指定调用提供者的哪个方法
     * @FeignClient+@GetMapping 就是一个完整的请求路径 http://service-product/product/{pid}
     * @param pid
     * @return
     */
    @GetMapping("/product/{pid}")
    Product getByPid(@PathVariable("pid") Integer pid);


}
