package com.how2java.springboot.web;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.springboot.dao.CategoryDAO;
import com.how2java.springboot.mapper.CategoryMapper;
import com.how2java.springboot.pojo.Category;
   
@Controller
public class CategoryController {
    @Autowired CategoryMapper categoryMapper;
    @Autowired  CategoryDAO categoryDAO;
    @RequestMapping("/listCategory")
    public String listCategory(Model m,@RequestParam(value = "start", defaultValue = "0") int start,@RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        PageHelper.startPage(start,size,"id desc");
        List<Category> cs=categoryMapper.findAll();
        PageInfo<Category> page = new PageInfo<>(cs);
        m.addAttribute("page", page);         
        return "listCategory";
    }
    @RequestMapping("/addCategory")
    public String addCategory(Category  category) {
    	categoryMapper.save(category);
    	return "redirect:listCategory";
    }
    @RequestMapping("/deleteCategory")
    public String deleteCategory(int id) {
    	//categoryMapper.delete(id);
    	categoryDAO.delete(id);
    	return "redirect:listCategory";
    }
    @RequestMapping("/updateCategory")
    public String updateCategory(Category category) {
    	categoryDAO.save(category);
    	return "redirect:listCategory";
    }
    @RequestMapping("/editCategory")
    public String editCategory(int id,Model m) {
    	Category category=categoryMapper.get(id);
    	m.addAttribute("c", category);
    	return "editCategory";
    }
}