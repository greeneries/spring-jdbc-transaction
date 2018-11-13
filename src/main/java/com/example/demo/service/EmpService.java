package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Emp;

public interface EmpService {
	public int insert(Emp emp) throws Exception;
	public int update(Emp emp) throws Exception;
	public int delete(int empno) throws Exception;
	
	public List<Emp> findAll() throws Exception;
	public int count() throws Exception;
	public Emp findOne(int empno) throws Exception;
}