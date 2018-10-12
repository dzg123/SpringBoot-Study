package com.dzg.springboot.controller;

import com.dzg.springboot.dao.DepartmentDao;
import com.dzg.springboot.dao.EmployeeDao;
import com.dzg.springboot.entities.Department;
import com.dzg.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    //    查询所有员工列表页面
    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps", employees);
//       thymeleaf默认就会拼串:prefix = "classpath:/templates/"
        return "emp/list";
    }

    //    来到员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model) {
//        查出所有的部门，在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    //    员工添加
//    springMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javaBean对象的属性名称一致
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        employeeDao.save(employee);
//        redirect:重定向到一个地址
//        forward:表示转发到一个地址
        return "redirect:/emps";
    }

    //    来到修改页面。查出当前员工，在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
//        回到修改页面（add是一个修改添加二合一的页面）；
        return "emp/add";
    }

    //    员工修改,需要提交id
    @PutMapping("/emp")
    public String updateEmployee(Employee employee) {
        System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //    员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";

    }
}
