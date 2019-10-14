package com.springmvcdemo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.springmvcdemo.model.User;
import com.springmvcdemo.web.excelView;

@SessionAttributes(value="user")//types= {String.class}
@Controller
public class IndexController {
	
	/*
	 * name参数的值，如果没有指定默认情况下为：IC#testConsumes
	 * value="testConsumes/?  ?通配符匹配一个字符，*匹配任意字符，**匹配多层路径
	 * index.jsp   ${pageContext.request.contextPath}/testConsumes/a"
	 */
	
	@RequestMapping(value="/testConsumes/", method=RequestMethod.POST)
	public String testConsumes() {
		return "success";
		
	}
	
	@RequestMapping({"/index","/",""})
	//@GetMapping("/index.html")
	public String index()
	{
		return "index";//返回对应的视图（jsp页面文件的）名字
		
	}
	
	@RequestMapping(value="/testRequestParam", method=RequestMethod.POST)
	public String testRequestParam(@RequestParam String username,@RequestParam(defaultValue="0") int age) {
		//由于字符串的影响，参数取值@RequestParam(required=false) Integer age//@RequestParam(defaultValue="0") int age
		System.out.println("==username:"+username);
		System.out.println("==password:"+age);
		return "success";
	}
	
	@RequestMapping(value="/testRequestHeader")
	public String testRequestHeader(@RequestHeader("Accept-Encoding")  String co)//CookieValue("JSESSIONID")
	{
		System.out.println(co);
		return "success";
		
	}
	
	@RequestMapping("/addUser")
	public String addUser(User user)  {//采用POJO方法
	    //public String addUser(String username,Integer age)
	    //表单里面的属性名和这里面的属性名保持一致，就可以直接获取了，@RequestParam默认值可以省去	
		
		System.out.println("---username1:"+user.getUsername());
		System.out.println("---age1:"+user.getAge());		
		return "success";
	}
//从success页面获取这里的值,testMV and testMV2 效果一样
	@RequestMapping("/testMV")
	public ModelAndView testMV()//和map集合用法一样
	{ 
		//1.注入字符串
		ModelAndView mv=new ModelAndView();
		mv.addObject("name","xaiobai");
		//2.注入对象
		User user=new User();
		user.setId(1);
		user.setAge(10);
		user.setUsername("xiaogong");
		mv.addObject("user",user);
		//3.注入list或map集合
		User user1=new  User();
		user1.setId(2);
		user1.setAge(12);
		user1.setUsername("xaiohei");
		List<User> list=new ArrayList<>();
		list.add(user);
		list.add(user1);
		mv.addObject("list",list);
		
		mv.setViewName("success");
		return mv;
		
	}
	@RequestMapping("/testMV2")
	public String testMV2(ModelMap model)//Model model//也可以用Map<String,Object> map
	{
		//ModelMap和Model的区别：ModelMap包含Model和Map，可以同时使用addAttribute()和put()方法，Map和Model则不可以
		//1.注入字符串
	        	model.addAttribute("name","xaiobai");
		//2.注入对象
				User user=new User();
				user.setId(1);
				user.setAge(10);
				user.setUsername("xiaogong");
				model.addAttribute("user",user);
		//3.注入集合
				List<User> list=new ArrayList<>();
				list.add(user);
				model.addAttribute("list", list);
		return "success";
	}
	
	@ModelAttribute//这个标签注解太牛逼了，无论哪个方法运行都要先运行这个标签下的方法
	public void testModeAttribute(@RequestParam(value="id",required=false) Integer id,Model model) {
		//System.out.println("id:"+id);
		//System.out.println("@ModelAttribute这个注解方法执行了！");
		User user=new User();
		user.setId(17);
		user.setUsername("admin");
		user.setAge(20);
		model.addAttribute("user",user);
	}
	@RequestMapping("updateUser")
	public String updateUser(User user)
	{
		System.out.println("要修改的信息"+user);
		
		
		return "test";
	}
	
	/*@RequestMapping({"/index2","/",""})	
	public String index2(Model model)
	{
		model.addAttribute("test", "test11111");
		
		return "index2";
		
	}*/
	
	@RequestMapping("/ev")
	public ModelAndView excelViewTest()
	{
		Map<String,Object> map=new HashMap<>();
		
		User user1=new User();
		User user2=new User();
		
		user1.setId(1);
        user1.setUsername("zs");
		user1.setAge(21);
		user2.setId(2);
		user2.setUsername("ls");
		user2.setAge(22);
		
		List<User> userList=new ArrayList<>();
		userList.add(user1);
		userList.add(user2);
		
		map.put("users", userList);
		
		ModelAndView mv=new ModelAndView(new excelView(),map);
		
		return mv;
	}
	@RequestMapping("/ev1")
	public String excelTest(Model model)
	{
		User user1=new User();
		User user2=new User();
		
		user1.setId(1);
        user1.setUsername("zs");
		user1.setAge(21);
		user2.setId(2);
		user2.setUsername("ls");
		user2.setAge(22);
		
		List<User> userList=new ArrayList<>();
		userList.add(user1);
		userList.add(user2);
		
		model.addAttribute("users", userList);
			
		return "excelView";
	}
	
	//简单的重定向例子
	@RequestMapping("/tr")
	public String testRedirect() {
		return "redirect:/ev1";
	}
	
	
	//REST风格的四种请求
	@RequestMapping(value="/testGet",method=RequestMethod.GET)
	public String testGet() {//获取一条数据
		
		System.out.println("testGet");
		return "success";	
	}
	@RequestMapping(value="/testPost",method=RequestMethod.POST)
	public String testPost() {//增加一条
		
		System.out.println("testPost");
		return "success";	
	}
	@RequestMapping(value="/testPut",method=RequestMethod.PUT)
	public String testPut() {//更新一条
		
		System.out.println("testPut");
		return "redirect:/index";	
	}
	@RequestMapping(value="/testDel",method=RequestMethod.DELETE)
	public String testDel() {//删除一条
		
		System.out.println("testDel");
		return "redirect:/index";	
	}
	
	
	
}
