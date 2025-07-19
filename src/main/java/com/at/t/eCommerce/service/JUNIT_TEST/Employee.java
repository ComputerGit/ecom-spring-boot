package com.at.t.eCommerce.service.JUNIT_TEST;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private double salary;
    private String department;
    private String location;
    private boolean isPermanent;
    private int experienceYears;
    private List<String> skills;
    private String gradeLevel;
    private LocalDate joiningDate;
    private Optional<String> managerName;

    public Employee(int id, String firstName, String lastName, double salary,
                    String department, String location, boolean isPermanent,
                    int experienceYears, List<String> skills,
                    String gradeLevel, LocalDate joiningDate, Optional<String> managerName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.department = department;
        this.location = location;
        this.isPermanent = isPermanent;
        this.experienceYears = experienceYears;
        this.skills = skills;
        this.gradeLevel = gradeLevel;
        this.joiningDate = joiningDate;
        this.managerName = managerName;
    }

    // Getters (use only the ones you need for stream ops)

    
    
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                ", location='" + location + '\'' +
                ", isPermanent=" + isPermanent +
                ", experienceYears=" + experienceYears +
                ", skills=" + skills +
                ", gradeLevel='" + gradeLevel + '\'' +
                ", joiningDate=" + joiningDate +
                ", managerName=" + managerName +
                '}';
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isPermanent() {
		return isPermanent;
	}

	public void setPermanent(boolean isPermanent) {
		this.isPermanent = isPermanent;
	}

	public int getExperienceYears() {
		return experienceYears;
	}

	public void setExperienceYears(int experienceYears) {
		this.experienceYears = experienceYears;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Optional<String> getManagerName() {
		return managerName;
	}

	public void setManagerName(Optional<String> managerName) {
		this.managerName = managerName;
	}

}
