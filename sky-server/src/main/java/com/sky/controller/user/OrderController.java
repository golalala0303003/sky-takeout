package com.sky.controller.user;

import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController("userOderController")
@RequestMapping("/user/order")
@Api(tags = "用户端订单接口")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping("/submit")
    @ApiOperation("用户下单")
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO) {
        log.info("ordersSubmitDTO:{}", ordersSubmitDTO);
       OrderSubmitVO orderSubmitVO = orderService.submitOrder(ordersSubmitDTO);
       return Result.success(orderSubmitVO);
    }

    @PutMapping("/payment")
    @ApiOperation("订单支付")
    public Result<OrderPaymentVO> payment(@RequestBody OrdersPaymentDTO ordersPaymentDTO) throws Exception {
        log.info("订单支付：{}", ordersPaymentDTO);
        OrderPaymentVO orderPaymentVO = new OrderPaymentVO();
        log.info("生成预支付交易单：{}", orderPaymentVO);
        //这里无法实现支付功能，所以跳过，直接修改订单参数为已支付
        orderService.paid(ordersPaymentDTO.getOrderNumber());

        return Result.success(orderPaymentVO);
    }
}
