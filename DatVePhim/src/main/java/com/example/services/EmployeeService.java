package com.example.services;

import com.example.dto.EmployeeDTO;
import com.example.models.Employee;
import com.example.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository empRepository;

    public List<Employee> getAll() {
        return empRepository.findAll();
    }

    public Boolean addEmployee(EmployeeDTO emp) {
        Employee newEmp = new Employee();
        setData(emp, newEmp);
        empRepository.save(newEmp);
        return true;
    }


    public boolean updateEmployee(EmployeeDTO emp, int id) {
        Employee e = empRepository.getById(id);
        setData(emp, e);
        empRepository.save(e);
        return true;
    }


    public boolean deleteEmployee(int id) {
        empRepository.deleteById(id);
        return true;
    }

    private void setData(EmployeeDTO emp, Employee newEmp) {
        newEmp.setFullName(emp.getFullName());
        newEmp.setBirthday(LocalDate.parse(emp.getBirthday()));
        newEmp.setAddress(emp.getAddress());
        newEmp.setEmail(emp.getEmail());
        newEmp.setPosition(emp.getPosition());
    }


}

