package com.practice.employeesrecords.repository;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.practice.employeesrecords.entity.Employee;
import com.practice.employeesrecords.service.EmployeeService;



@Component
public class DataInjector implements CommandLineRunner {
	
	public Logger log = LoggerFactory.getLogger(this.getClass());
	
	private EmployeeService employeeService;
	
	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Override
	public void run(String... args) throws Exception {				// data loader from Excel file...
		log.info("CommandLineRunner -- Data Injector starts >>");
		
//		employeeService.saveEmployee( new Employee("Ghazya", "Area", 1974, "Stockholm") );
//		employeeService.saveEmployee( new Employee("Hilary", "Franstic", 1992, "London") );
		
		String fileLocation = "Employees.xlsx";
		
		Workbook workbook = new XSSFWorkbook( new FileInputStream(fileLocation) );
		Sheet sheet = workbook.getSheet("employees");
		
		for (Row row : sheet) {
			if (row.getRowNum() != 0) {
				employeeService.saveEmployee(
						new Employee(
								row.getCell(0).getStringCellValue(),
								row.getCell(1).getStringCellValue(),
								(int) row.getCell(2).getNumericCellValue(),
								row.getCell(3).getStringCellValue()
								)
						);
			}
		}
		
		
//		// The mere printing of Excel data - as a TEST of it working well
//		for (Row row : sheet) {
//			if (row.getRowNum() == 0) {
//				log.info("-- Header --");
//				log.info(row.toString());
//			} else {
//				for (Cell cell : row) {
//					if (cell.getCellType() == CellType.STRING)
//						log.info(String.valueOf(cell.getColumnIndex())+" - "+cell.getStringCellValue());
//					if (cell.getCellType() == CellType.NUMERIC)
//						log.info(String.valueOf(cell.getColumnIndex())+" - "+String.valueOf((int) cell.getNumericCellValue()));
//				}
//			}
//		}
		
		log.info("CommandLineRunner -- Data Injector ends <<");
	}

}
