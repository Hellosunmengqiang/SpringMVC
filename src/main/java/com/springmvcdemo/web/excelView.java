package com.springmvcdemo.web;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.springmvcdemo.model.User;

@Component("excelView")//对应String 方法ev1
public class excelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String fileName="test.xlsx";
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/ms-excel");
		response.setHeader("Content-Disposition", "inline:filename="+new String(fileName.getBytes(),"UTF-8"));
		//System.out.println(model.get("users"));
		
		@SuppressWarnings("unchecked")
		List<User> users=(List<User>) model.get("users");//model里面的数据来自于controller类处理方法中放进的
		//创建Excel里面的一张表	
		Sheet sheet=workbook.createSheet("用户信息表");
		//创建sheet表的第一行
		Row headRow=sheet.createRow(0);
		//给第一行单元格里面写东西
		headRow.createCell(0).setCellValue("id");
		headRow.createCell(1).setCellValue("username");
		headRow.createCell(2).setCellValue("age");
		
		int rownum=1;
		for(User u:users)
		{
			Row row=sheet.createRow(rownum++);
			row.createCell(0).setCellValue(u.getId());
			row.createCell(1).setCellValue(u.getUsername());
			row.createCell(2).setCellValue(u.getAge());
		}
		OutputStream outStream=response.getOutputStream();
		workbook.write(outStream);
		outStream.flush();
		outStream.close();
	}

}
