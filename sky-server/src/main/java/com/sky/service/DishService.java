package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    /**
     * 新增菜品
     * @param dishDTO
     */
    void saveWithFlavor(DishDTO dishDTO);

    /**
     * 分页查询
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 批量删除菜品
     * @param ids
     */
    void delete(List<Long> ids);

    /**
     * 根据id查询dish
     * @return
     */
    DishVO findById(Long id);

    /**
     * 修改菜品
     * @param dishDTO
     */
    void update(DishDTO dishDTO);

    /**
     * 更改状态
     * @param status
     * @param id
     */
    void updateStatus(Integer status, Long id);


    /**
     * 根据分类id查询菜品
     * @param id
     * @return
     */
    List<Dish> findByCategoryId(Long id);

    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);

}
