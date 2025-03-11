package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    @AutoFill(value = OperationType.INSERT)
    @Insert("insert into category(id,name,type,sort,status,create_time,update_time,create_user,update_user)" +
            "values(#{id},#{name},#{type},#{sort},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void insert(Category category);

    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    @AutoFill(value = OperationType.UPDATE)
    void update(Category category);

    @Delete("delete from category where id = #{id}")
    void deleteById(Long id);
}
