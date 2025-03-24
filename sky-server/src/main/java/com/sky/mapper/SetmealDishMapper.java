package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品id查询套餐id
     * @param dishIds
     * @return
     */
    List<Long> getSetmealDishIdsByDishId(List<Long> dishIds);

    /**
     * 批量添加套餐内菜品
     * @param setmealDishes
     */
    void addSetmealDishes(List<SetmealDish> setmealDishes);

    /**
     * 批量删除套餐内菜品
     * @param ids
     */
    void deleteSetmealDishes(List<Long> ids);

    /**
     * 查询根据套餐id查询名下所有菜品
     * @param id
     * @return
     */
    @Select("select * from setmeal_dish where setmeal_id = #{id}")
    List<SetmealDish> getSetmealDishesByDishId(Long id);

    @Delete("delete from setmeal_dish where setmeal_id = #{id}")
    void deleteSetmealDish(Long id);
}
