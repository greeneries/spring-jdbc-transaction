package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.example.demo.dao.EmpDao;
import com.example.demo.model.Emp;

//클래스에 트랜잭션 설정을 하면 이 안에 있는 모든 메소드에 트랜잭션이 적용됩니다.
//서비스 메소드에서 다수의 DAO 메소드를 호출하면, 그 메소드 모두 하나의 트랜잭션 
//단위로 처리됩니다.
/*
 * @Transactional(
		isolation=Isolation.DEFAULT,
		propagation=Propagation.REQUIRED,
		timeout=-1,
		readOnly=false)
 */
@Transactional
@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private EmpDao empDao;

	@Override
	public int insert(Emp emp) throws Exception {
		int	affected = empDao.insert(emp);
		return affected;
	}

	@Override
	public int update(Emp emp) throws Exception {
		return empDao.update(emp);
	}

	@Override
	public int delete(int empno) throws Exception {
		return empDao.delete(empno);
	}

	@Override
	public List<Emp> findAll() throws Exception {
		return empDao.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public int count() throws Exception {
		return empDao.count();
	}

	@Transactional(readOnly=true)
	@Override
	public Emp findOne(int empno) throws Exception {
		return empDao.findOne(empno);
	}

}