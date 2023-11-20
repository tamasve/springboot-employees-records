package com.practice.employeesrecords.controller;

import org.springframework.stereotype.Component;

/**
 * For transfering the UI settings to Thymeleaf
 * 
 * !!! basically all @Component POJOs should have getters and setters !!!
 * 
 * selectedList would not need getter for the th:switch, but searchCity does need it for the th:field...
 * 
 * 28-29 Sept 2023
 */

@Component
public class Settings {
	
	private int selectedList;
	private String searchCity;
	private String searchName;
	private int pageNumber;
	private int pageCount;
	private String sortedBy;
	private boolean ascending;
	
	// default settings
	public Settings() {
		selectedList = 0;
		searchCity = "";
		searchName = "";
		pageNumber = 0;
		pageCount = 0;
		sortedBy = "";
		ascending = true;
	}


	public int getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(int selectedList) {
		this.selectedList = selectedList;
	}

	public String getSearchCity() {
		return searchCity;
	}

	public void setSearchCity(String searchCity) {
		this.searchCity = searchCity;
	}
	
	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public int getPageNumber() {
		return pageNumber;
	}
	
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public int incPageNumber() {
		return pageNumber++;
	}
	
	public int decPageNumber() {
		return pageNumber--;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public String getSortedBy() {
		return sortedBy;
	}

	public void setSortedBy(String sortedBy) {
		this.sortedBy = sortedBy;
	}
	

	public boolean isAscending() {
		return ascending;
	}

	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}

}
