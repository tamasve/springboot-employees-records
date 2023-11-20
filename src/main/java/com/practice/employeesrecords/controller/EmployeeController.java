package com.practice.employeesrecords.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.practice.employeesrecords.service.EmployeeService;


@Controller
public class EmployeeController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private EmployeeService employeeService;
	
	private Settings settings;
	
	@Autowired
//	public void setEmployeeService(EmployeeService employeeService) {
//		this.employeeService = employeeService;
//	}
	public EmployeeController(EmployeeService employeeService, Settings settings) {
		this.employeeService = employeeService;
		this.settings = settings;
	}
	
	
	
	//** THE MAIN RENDERER METHOD **//
	
	// most other methods redirect here after making the changes requested by UI in the settings
	
	@GetMapping("/")
	public String getAllEmployees(Model model) {
		log.info("selected list: " + settings.getSelectedList());
		if (settings.getPageCount() == 0) 
			settings.setPageCount( employeeService.countOfEmployeePages() );	// if count of pages equals to 0, ask it from repo
		model
			.addAttribute("employees", employeeService.getAllEmployees())
			.addAttribute("employeesInACity", employeeService.findEmployeesByResidence(settings.getSearchCity()))
			.addAttribute("employeesByName", employeeService.findEmployeesByName(settings.getSearchName()))
			.addAttribute("settings", settings);
		if (settings.getSortedBy().isEmpty()) model.addAttribute("employeesOnPages", employeeService.findEmployeesOnPages(settings.getPageNumber()));
			else model.addAttribute("employeesOnPages", employeeService.findEmployeesOnPagesSorted(settings.getPageNumber(), settings.getSortedBy(), settings.isAscending()) );
		return "employees-records";
	}
	
	//**      ------------       **//
	
	
	
	@GetMapping("/menuitem/{item}")
	public String chooseMenuItem(@PathVariable("item") String item, Model model) {
		log.info("item = " + item);
		settings.setSelectedList( Integer.valueOf(item) );
//		model
//			.addAttribute("employees", employeeService.getAllEmployees())
//			.addAttribute("settings", settings);
//		return "employees-records";
		return "redirect:/";				// !!! if we show the same page that we came from - ALWAYS REDIRECT !!!  (otherwise it can be recursive causing failures...)
	}
	
	@GetMapping("/search-by-city")
	public String searchInACity(@ModelAttribute("settings") Settings settings_, Model model) {
		settings.setSearchCity( settings_.getSearchCity() );
		log.info("new search in " + settings_.getSearchCity());
		return "redirect:/";
	}
	
	@GetMapping("/search-by-name")
	public String searchByName(@ModelAttribute("settings") Settings settings_, Model model) {
		settings.setSearchName( settings_.getSearchName() );
		log.info("new search in " + settings_.getSearchName());
		return "redirect:/";
	}
	
	
	// the controller methods behind the paging buttons
	
	@GetMapping("/previous-page")
	public String previousPage() {
		settings.decPageNumber();
		if (settings.getPageNumber() < 0) settings.setPageNumber(0);
		return "redirect:/";
	}
	
	@GetMapping("/next-page")
	public String nextPage() {
		settings.incPageNumber();
		if (settings.getPageNumber() > settings.getPageCount()-1) settings.setPageNumber(settings.getPageCount()-1);
		return "redirect:/";
	}
	
	// sorting by a field by clicking on the header... if twice after each other >> asc <-> desc
	
	@GetMapping("/sort-by/{field}")
	public String sorted(@PathVariable String field) {
		if (settings.getSortedBy().equals(field)) settings.setAscending( !settings.isAscending() );	// prop did not changed >>   asc <-> desc
			else settings.setAscending(true);
		settings.setSortedBy(field);
		return "redirect:/";
	}

	@GetMapping("/no-sorting")
	public String sorted() {
		settings.setSortedBy("");		// clear sorting field
		return "redirect:/";
	}
}
