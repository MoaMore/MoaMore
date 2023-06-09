package com.yedam.moa.emp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yedam.moa.emp.service.EmpService;

@Controller
public class EmpController {
	@Autowired
	EmpService empService;

	@RequestMapping("/empList")
	 public String empList(Model model){ 
		 model.addAttribute("empList", empService.getEmpList());
	 return "empList"; 
	 }
}
