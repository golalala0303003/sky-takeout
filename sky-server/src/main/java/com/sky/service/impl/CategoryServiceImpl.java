package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.CategoryMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.EmployeeMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetmealMapper setmealMapper;

    @Override
    public void addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);

        //默认禁用
        category.setStatus(0);

        //设置时间
        //LocalDateTime createTime = LocalDateTime.now();
        //category.setCreateTime(createTime);
        //category.setUpdateTime(createTime);

        //Long currentId = BaseContext.getCurrentId();
        //category.setCreateUser(currentId);
        //category.setUpdateUser(currentId);

        log.info("创建了了一个新的分类，准备录入数据库{}", category);

        categoryMapper.insert(category);
    }

    public PageResult queryCategory(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(),categoryPageQueryDTO.getPageSize());
        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }

    public void startOrStop(Integer status, Long id) {
        log.info("开始禁用分类{} {}",status,id);
        Category category = Category.builder()
                .id(id)
                .status(status)
                .updateTime(LocalDateTime.now())
                .updateUser(BaseContext.getCurrentId())
                .build();
        categoryMapper.update(category);
    }

    public void deleteCategoryById(Long id) {
        log.info("开始删除分类{}",id);
        Integer count = dishMapper.countByCategoryId(id);
        if (count > 0) {
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_DISH);
        }
        count = setmealMapper.countByCategoryId(id);
        if(count > 0){
            //当前分类下有菜品，不能删除
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_SETMEAL);
        }
        categoryMapper.deleteById(id);
    }

    public void updateCategory(CategoryDTO categoryDTO) {
        log.info("开始修改分类信息{}",categoryDTO);
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        //category.setUpdateTime(LocalDateTime.now());
        //category.setUpdateUser(BaseContext.getCurrentId());
        categoryMapper.update(category);
    }

    public List<Category> getCategoryByType(Integer type) {
        log.info("开始根据类型查找分类type:{}", type);
        return categoryMapper.getCategoryByType(type);
    }


}
