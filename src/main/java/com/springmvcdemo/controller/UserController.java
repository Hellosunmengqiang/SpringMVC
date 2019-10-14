package com.springmvcdemo.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	//HttpMessageConverter
    @ResponseBody
    @RequestMapping(value="/testConver",method=RequestMethod.POST)
    public String testConver(@RequestBody String test) {
       System.out.println(test);  	
    	return "qweee"+new Date();
    	/*
    	 * 在SpringMVC的 Controller层经常会用到@RequestBody和@ResponseBody，通过这两个注解，
    	 * 可以在Controller中直接使用Java对象作为请求参数和返回内容(这里直接将返回内容直接写入到
    	 * Response对象的body数据区，从而绕过来前面我们讲的视图解析器，不通过视图解析器直接将数据响应给浏览器)，
    	 * 而在写入之前必须要转换响应的数据格式，完成这之间转换作用的便是HttpMessageConverter。
    	 * 
    	 */
    }
    
    @RequestMapping(value="/upLoad",method=RequestMethod.POST)
    public String upLoad(@RequestParam("file") MultipartFile file,@RequestParam("mark") String mark,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
    	System.out.println("===mark:"+mark);
    	System.out.println(file.getOriginalFilename());
    	if(!file.isEmpty())
    	{
    	//String path=request.getServletContext().getRealPath("/resources/uploadfile/");//上传到服务器的地址
    		
    	String path="C:\\Users\\Administrator\\eclipse-workspace\\springmvcdemo\\src\\main\\webapps\\resources\\uploadfile\\";//本地项目地址
    	String fileName=file.getOriginalFilename();
    	model.addAttribute("filename", fileName);
    	File file2=new File(path,fileName);
    	if(!file2.getParentFile().exists()) {
    		file2.getParentFile().mkdirs();//如果文件不存在，创建；
    	}
    	System.out.println("===path:"+path);
    	file.transferTo(new File(path+File.separator+fileName));//写进硬盘
    	return "success";
    	}
    	return "error";
    }
    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(@RequestParam("fileName") String fileName,HttpServletRequest request) throws IOException{
    	String path=request.getServletContext().getRealPath("/resources/uploadfile/");
    	File file=new File(path+File.separator+fileName);
    	HttpHeaders headers=new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    	headers.setContentDispositionFormData("attachment",new String(fileName.getBytes("UTF-8"),"UTF-8"));
    	
    	return new ResponseEntity<>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
    }
    
    @RequestMapping("/testex")
    public String testex(int i) {
    	System.out.println(10/i);
    	return "redirect:/index";
    }
    
    //处理上面的异常的结果页面
    /*@ExceptionHandler({ArithmeticException.class})
    public ModelAndView handlerEx(Exception ex) {
        ModelAndView mv=new ModelAndView();
        mv.addObject("ex",ex.getMessage());
        mv.setViewName("error");
    	return mv;
    }*/

}
