package com.practice.employeesrecords.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@Column(name="firstname", nullable=false)
	String firstName;

	@Column(name="lastname", nullable=false)
	String lastName;
	
	@Column(name="birthyear", nullable=false)
	Integer birthYear;
	
	@Column(name="residence", nullable=false)
	String residence;
	
	public Employee() {}

	/**
	 * @param id
	 * @param firstname
	 * @param lastname
	 * @param birthyear
	 * @param residence
	 */
	public Employee(Long id, String firstName, String lastName, Integer birthYear, String residence) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthYear = birthYear;
		this.residence = residence;
	}
	
	public Employee(String firstName, String lastName, Integer birthYear, String residence) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthYear = birthYear;
		this.residence = residence;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}
	
	
	
}
