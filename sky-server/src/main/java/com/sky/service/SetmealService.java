package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {

    /**
     * 添加新的套餐
     */
    void addNewSetmeal(SetmealDTO setmealDTO);

    /**
     * 分页查询套餐
     */
    PageResult page(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 批量删除套餐
     */
    void deleteSetmeals(List<Long> ids);

    /**
     * 根据id查询套餐
     */
    SetmealVO getSetmealById(Long id);

    /**
     * 修改套餐
     */
    void updateSetmeal(SetmealDTO setmealDTO);

    /**
     * 修改状态
     */
    void status(Integer status,Long id);
}
