package com.how2java.springboot.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.how2java.springboot.dao.UserDAO;
import com.how2java.springboot.pojo.Category;
import com.how2java.springboot.pojo.User;

@Controller
public class UserController {
	@Autowired UserDAO userDAO;
	//登录验证
	@RequestMapping("/login")
	public String Login(User user,Model m) {	
		List<User> list=userDAO.findAll();
		for (User user2 : list) {
			if (user.getUsername().equals(user2.getUsername())&&user.getPassword().equals(user2.getPassword())) {
				m.addAttribute("start", "1");
				return "listCategory";
			}
		}
		m.addAttribute("msg", "用户名或密码错误！");
		return "login";
	}
	@RequestMapping("/index")
	public String index() {
		return "login";
	}
	@RequestMapping("/zhuce")
	public String zhuce(Model m) {
		int[] num=new int[4];
		Random random=new Random();
		for (int i = 0; i <4; i++) {
			num[i]=random.nextInt(10)+1;
			System.out.println("原始："+num[i]);	
		}		
		m.addAttribute("yan", num);
		return "zhuce";
	}
	//更改验证码
	@RequestMapping("/yanzheng")
	@ResponseBody
	public List<Integer> yanzheng(Model m) {
	//	
		List<Integer> num=new ArrayList<Integer>();
		Random random=new Random();
		for (int i = 0; i <4; i++) {
			num.add(random.nextInt(10)+1);
			System.out.println("新的验证码"+num.get(i));	
		}
		m.addAttribute("yan", num);
		return num;
	}
	//注册用户
	@RequestMapping("/register")
	public String register(User user,Model m) {
		//System.out.println("接收到前台验证码："+yan);
		/*
		 * if (yan==null) { userDAO.save(user); return "login"; }
		 */
		return "zhuce";
	}
	//检查用户名是否重复
	@RequestMapping("/check")
	@ResponseBody
	public Map<String,String> check(@RequestParam(value = "username") String username,Model m) {
		List<User> list=userDAO.findAll();
		Map<String,String> u=new HashMap<String, String>();		
		for (User user : list) {
			if (username.equals(user.getUsername())) {
				System.out.println("same");
				u.put("name","1");
				}
		}		
		return u;	
	}
	//检查验证码是否错误
	@RequestMapping("/checkyan")
	@ResponseBody
	public boolean checkyan(@RequestParam(value = "yan") String yan) {
		System.out.println("接收到前台验证码："+yan);
		return false;
	}
}
