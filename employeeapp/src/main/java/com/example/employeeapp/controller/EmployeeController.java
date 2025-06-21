package com.example.employeeapp.controller;

import com.example.employeeapp.model.Employee;
import com.example.employeeapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller // This annotation makes it a Spring MVC controller
public class EmployeeController {

    @Autowired // Injects the EmployeeRepository dependency
    private EmployeeRepository employeeRepository;

    // 1. Landing page with Employee input form
    @GetMapping("/")
    public String index(Model model) {
        // Adds an empty Employee object to the model, to be used by the form for data binding
        model.addAttribute("employee", new Employee());
        return "index"; // Returns the name of the JSP file (index.jsp)
    }

    // Handles form submission to save a new employee
    // This is an MVC endpoint, not a direct REST API for submission
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee, Model model) {
        employeeRepository.save(employee); // Saves the employee to MongoDB
        model.addAttribute("message", "Employee saved successfully!");
        return "redirect:/"; // Redirects back to the index page
    }

    // 2. '/displayAll' path to show all employees (MVC View)
    @GetMapping("/displayAll")
    public String displayAllEmployees(Model model) {
        List<Employee> employees = employeeRepository.findAll(); // Fetches all employees
        model.addAttribute("employees", employees); // Adds the list to the model
        return "displayAll"; // Returns the JSP file to display all employees
    }

    // 3. '/display/<user id>' path to show a particular employee (MVC View)
    @GetMapping("/display/{id}")
    public String displayEmployeeById(@PathVariable("id") String id, Model model) {
        Optional<Employee> employee = employeeRepository.findById(id); // Finds employee by ID
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
        } else {
            model.addAttribute("message", "Employee not found with ID: " + id);
        }
        return "displayEmployee"; // Returns the JSP file to display a single employee
    }

    // RESTful API Endpoints (prefixed with /api)

    // RESTful GET all employees
    @GetMapping("/api/employees")
    @ResponseBody // Indicates that the return value should be bound to the web response body
    public List<Employee> getAllEmployeesRest() {
        return employeeRepository.findAll();
    }

    // RESTful GET employee by ID
    @GetMapping("/api/employees/{id}")
    @ResponseBody
    public ResponseEntity<Employee> getEmployeeByIdRest(@PathVariable("id") String id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // RESTful POST to create a new employee
    @PostMapping("/api/employees")
    @ResponseBody
    public ResponseEntity<Employee> createEmployeeRest(@RequestBody Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // RESTful PUT to update an existing employee
    @PutMapping("/api/employees/{id}")
    @ResponseBody
    public ResponseEntity<Employee> updateEmployeeRest(@PathVariable("id") String id, @RequestBody Employee employeeDetails) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee existingEmployee = employeeOptional.get();
            existingEmployee.setName(employeeDetails.getName());
            existingEmployee.setEmail(employeeDetails.getEmail());
            existingEmployee.setLocation(employeeDetails.getLocation());
            Employee updatedEmployee = employeeRepository.save(existingEmployee);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // RESTful DELETE an employee
    @DeleteMapping("/api/employees/{id}")
    @ResponseBody
    public ResponseEntity<HttpStatus> deleteEmployeeRest(@PathVariable("id") String id) {
        try {
            employeeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
