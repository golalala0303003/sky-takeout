package com.sky.mapper;

import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderMapper {

    void insert(Orders orders);

    @Update("update orders set status = 2 , pay_status = 1 where number = #{orderNumber}")
    void paidByOrderNumber(String orderNumber);
}
