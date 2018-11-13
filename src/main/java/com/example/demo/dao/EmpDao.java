package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.Emp;

public interface EmpDao {
	// 리턴자료형 int: 작업결과로 영향받은 row의 개수를 의미합니다.
	public int insert(Emp emp);
	public int update(Emp emp);
	public int delete(int empno);
	
	public List<Emp> findAll();
	public int count();
	public Emp findOne(int empno);
}