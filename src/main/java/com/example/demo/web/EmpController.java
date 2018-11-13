package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dao.EmpDao;
import com.example.demo.model.Emp;

@Controller
public class EmpController {
	@Autowired
	private EmpDao empDao;

//	http://localhost:8080/transaction/emps
	@RequestMapping(value="/emps", method=RequestMethod.GET)
	public String getAll(Model model) {
		// DAO에게 데이터를 구해달라고 요청한다.
		List<Emp> emps = empDao.findAll();
		// Model 객체에 데이터를 추가하면 
		// 스프링이 HttpServletRequest 객체로 옮긴다.
		// HttpServletRequest 객체에 존재하는 데이터는 
		// JSP에서 접근하여 사용할 수 있다.
		model.addAttribute("emps", emps);
		// 다음으로 연동할 JSP 뷰 파일명을 리턴한다.
		return "emp-list";
	}
	
}