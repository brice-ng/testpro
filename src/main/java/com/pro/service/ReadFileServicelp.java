package com.pro.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.CellBase;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.pro.model.Users;
import com.pro.repositorie.ReadFileRepository;


@Service
public class ReadFileServicelp {
	
	@Autowired private ReadFileRepository readFileRepository;


	public List<Users> findAll() {

	return	(List<Users>) readFileRepository.findAll();
		}

	
	public boolean saveDataFromUploadfile(MultipartFile file) {

		boolean isFlag=false;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		
		if(extension.equalsIgnoreCase("json")) {
			//isFlag=readDataFromJson(file);
		}else if(extension.equalsIgnoreCase("csv")) {
			
			//isFlag=readDataFromCsv(file);

		}else if(extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")) {
			
			isFlag=readDataFromExcel(file);

		}
				
		return isFlag;
	}

	
	
	private boolean readDataFromExcel(MultipartFile file) {

		XSSFWorkbook workbook = (XSSFWorkbook) getWorkBook(file);
		XSSFSheet sheet =  workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();
		rows.next();
		
		while(rows.hasNext()) {
			
			XSSFRow row = (XSSFRow) rows.next();
			Users users = new Users(); 
			
			if(row.getCell(0).getCellType() == CellType.STRING ) {
				
				users.setFirstname(row.getCell(0).getStringCellValue());
				
			}

			if(row.getCell(1).getCellType() == CellType.STRING ) {
				
				users.setLastname(row.getCell(1).getStringCellValue());
					
			}
			
			if(row.getCell(2).getCellType() == CellType.STRING ) {
				
				users.setEmail(row.getCell(2).getStringCellValue());
				
			}
			

			if(row.getCell(3).getCellType() == CellType.NUMERIC) {
				
				String phoneNumber = NumberToTextConverter.toText(row.getCell(3).getNumericCellValue());
				users.setPhoneNumber(phoneNumber);
				
			} else if(row.getCell(3).getCellType() == CellType.STRING) {
				
				String phoneNumber = NumberToTextConverter.toText(row.getCell(3).getNumericCellValue());
				users.setPhoneNumber(phoneNumber);
				
			}
			
			users.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
			readFileRepository.save(users);
			
		}
		
		
		return true;
	}

	
	private Workbook getWorkBook(MultipartFile file) {

	Workbook	workbook = null;
	
	String extension = FilenameUtils.getExtension(file.getOriginalFilename());

	try {
	
		if(extension.equalsIgnoreCase("xlsx")) {
			workbook = new XSSFWorkbook(file.getInputStream());
			
			
		}else 		if(extension.equalsIgnoreCase("xls")) {
			
			workbook = new HSSFWorkbook(file.getInputStream());
			
		}	
	} catch (Exception e) {
		e.printStackTrace();
		
	}
		
		

		return workbook;
	}

	private boolean readDataFromCsv(MultipartFile file) {

		try {
			
			InputStreamReader reader = new InputStreamReader(file.getInputStream());
			CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
			List<String[]> rows = csvReader.readAll();
			for(String[] row : rows ) {
				readFileRepository.save(new Users( row[0], row[1], row[2], row[3],FilenameUtils.getExtension(file.getOriginalFilename())));
						
			}return true;
				
		}catch (Exception e) {

			return false;
		}
		
		
	}

	private boolean readDataFromJson(MultipartFile file) {

		try {
			InputStream inputStream = file.getInputStream();
			ObjectMapper mapper = new ObjectMapper();
			List<Users> users = Arrays.asList(mapper.readValue(inputStream,Users[].class ));
			
			if(users!=null && users.size()>0) {
				for(Users user: users) {
					user.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
					readFileRepository.save(user);
				}
			}
			
			return true;
			
		}catch (Exception e) {
			return false;

		}
		
	}
	 
	
	public void deleteAll() {

		readFileRepository.deleteAll();
		
		}
	 
	

}
