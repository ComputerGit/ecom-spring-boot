package com.at.t.eCommerce.service.JUNIT_TEST;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class EmployeeData {

    public static List<Employee> populateEmployee() {
        return List.of(
            new Employee(1, "Harry", "Styles", 150000.0, "Engineering", "London", true, 8,
                    List.of("Java", "Spring", "AWS"), "L4", LocalDate.of(2015, 3, 10), Optional.of("Simon")),
            new Employee(2, "Zayn", "Malik", 125000.0, "Design", "Manchester", false, 6,
                    List.of("Figma", "UX", "Illustrator"), "L3", LocalDate.of(2017, 7, 15), Optional.of("Harry")),
            new Employee(3, "Liam", "Payne", 95000.0, "Marketing", "London", true, 5,
                    List.of("SEO", "Analytics", "Content Creation"), "L2", LocalDate.of(2019, 1, 5), Optional.of("Louis")),
            new Employee(4, "Louis", "Tomlinson", 145000.0, "Engineering", "London", true, 7,
                    List.of("Java", "Microservices", "Docker"), "L4", LocalDate.of(2016, 6, 20), Optional.of("Simon")),
            new Employee(5, "Niall", "Horan", 110000.0, "HR", "Dublin", true, 4,
                    List.of("Recruitment", "People Ops", "Communication"), "L2", LocalDate.of(2020, 2, 10), Optional.of("Zayn")),
            new Employee(6, "Simon", "Cowell", 200000.0, "Leadership", "London", true, 15,
                    List.of("Management", "Talent Scouting", "Strategy"), "Director", LocalDate.of(2009, 8, 1), Optional.empty()),
            new Employee(7, "Sophia", "Smith", 88000.0, "Finance", "Manchester", false, 3,
                    List.of("Budgeting", "Excel", "Auditing"), "L2", LocalDate.of(2021, 3, 30), Optional.of("Niall")),
            new Employee(8, "Perrie", "Edwards", 105000.0, "QA", "Birmingham", true, 6,
                    List.of("Testing", "Selenium", "Postman"), "L3", LocalDate.of(2018, 11, 25), Optional.of("Louis"))
        );
    }
}
