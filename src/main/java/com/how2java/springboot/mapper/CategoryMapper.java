package com.how2java.springboot.mapper;
	
import java.util.List;
 
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
 
import com.how2java.springboot.pojo.Category;
 
@Mapper
public interface CategoryMapper {
	
    List<Category> findAll();
    public int save(Category category);
    public void delete(int id);
    public Category get(int id);
    public int update(Category category);
}