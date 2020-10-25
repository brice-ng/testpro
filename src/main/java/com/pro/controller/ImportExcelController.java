package com.pro.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pro.model.Choice;


public class ImportExcelController {

	
	
	  @RequestMapping(value = "/import-excel", method = RequestMethod.POST)
	    public ResponseEntity<List<Choice>> importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {
	        HttpStatus status = HttpStatus.OK;
	        List<Choice> productList = new ArrayList<>();

	        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
	        XSSFSheet worksheet = workbook.getSheetAt(0);

	        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
	            if (index > 0) {
	            	Choice product = new Choice();

	                XSSFRow row = worksheet.getRow(index);
	                Integer id = (int) row.getCell(0).getNumericCellValue();

	                product.setId(id);
	             

	                product.setProductName(row.getCell(1).getStringCellValue());
	                product.setPrice(row.getCell(2).getNumericCellValue());
	                product.setCatgorie(row.getCell(3).getStringCellValue());

	                productList.add(product);
	            }
	        }

	        return new ResponseEntity<>(productList, status);
	    }
	
}
