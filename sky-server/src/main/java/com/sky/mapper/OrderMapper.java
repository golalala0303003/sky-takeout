package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderMapper {

    void insert(Orders orders);

    @Update("update orders set status = 2 , pay_status = 1 where number = #{orderNumber}")
    void paidByOrderNumber(String orderNumber);

    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    @Select("select * from orders where id = #{id}")
    Orders getById(Long id);

    void update(Orders orders);

    @Select("select count(id) from orders where status = #{status}")
    Integer countStatus(Integer toBeConfirmed);
}
