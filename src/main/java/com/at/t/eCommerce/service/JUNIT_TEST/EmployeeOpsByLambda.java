package com.at.t.eCommerce.service.JUNIT_TEST;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeOpsByLambda {
	
	public static void lambaOps() {
		
		List<Employee> em = EmployeeData.populateEmployee();
		
//		collect the employees
		List<Employee> collect = em.stream().collect(Collectors.toList());
	
		
//sort by salary in desc
		List<Employee> collect2 = em.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).collect(Collectors.toList());
		
		
		List<Employee> collect3 = em.stream().filter(e -> e.getDepartment().equals("Engineering") && e.getExperienceYears()>=2)
				.sorted(Comparator.comparing((Employee e) -> e.getSkills().contains("Docker")).reversed()).collect(Collectors.toList());
//		collect3.forEach(System.out::println);
		
		
		 List<Employee> collect4 = em.stream().sorted(Comparator.comparing((Employee e) -> hadJavaOrDocker(e) ? 0 : 1)).collect(Collectors.toList());
//			collect4.forEach(System.out::println);
		 
		 List<Employee> collect5 = em.stream().filter(e -> e.getDepartment().contains("Engineering")).sorted(Comparator.comparing(Employee::getExperienceYears))
		 .collect(Collectors.toList());
			
		
		 List<Employee> collect6 = em.stream().filter(e -> List.of("Design","QA").contains(e.getDepartment())) 
		.sorted(Comparator.comparing(Employee::getSalary))
		 .collect(Collectors.toList());
		 collect6.forEach(System.out::println);
	}
	
	
	   public static void main (String[] args) {
		   
	    	
	        lambaOps();
	        System.out.println("Executed");
	    	
	    }
	   
	   public static boolean hadJavaOrDocker(Employee e) {
		   
		   return e.getSkills().stream().anyMatch(skill -> skill.equalsIgnoreCase("Docker") || skill.equalsIgnoreCase("Java"));
		   
	   }

}
