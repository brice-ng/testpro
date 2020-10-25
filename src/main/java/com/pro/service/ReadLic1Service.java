package com.pro.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
import com.pro.model.Licence1;
import com.pro.repositorie.ReadLicence1;


@Service
public class ReadLic1Service {
	
	@Autowired private ReadLicence1 readLicence1;


	public List<Licence1> findAll() {

	return	(List<Licence1>) readLicence1.findAll();
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
			Licence1 users = new Licence1(); 
			
			if(row.getCell(0).getCellType() == CellType.STRING ) {
				
				users.setFirstname(row.getCell(0).getStringCellValue());
				
			}

			if(row.getCell(1).getCellType() == CellType.STRING ) {
				
				users.setLastname(row.getCell(1).getStringCellValue());
					
			}
			
					
			users.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
			readLicence1.save(users);
			
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
				readLicence1.save(new Licence1( row[0], row[1],FilenameUtils.getExtension(file.getOriginalFilename())));
						
			}return true;
				
		}catch (Exception e) {

			return false;
		}
		
		
	}

	private boolean readDataFromJson(MultipartFile file) {

		try {
			InputStream inputStream = file.getInputStream();
			ObjectMapper mapper = new ObjectMapper();
			List<Licence1> users = Arrays.asList(mapper.readValue(inputStream,Licence1[].class ));
			
			if(users!=null && users.size()>0) {
				for(Licence1 user: users) {
					user.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
					readLicence1.save(user);
				}
			}
			
			return true;
			
		}catch (Exception e) {
			return false;

		}
		
	}
	
	
		public void deleteAll() {

		readLicence1.deleteAll();
		
		}
	 
		public Optional<Licence1> findById(Long id) {
			return readLicence1.findById(id);
		}
		
		//delete country 
		public void delete(Long id) {

			readLicence1.deleteById(id);
		}

		
	

}
