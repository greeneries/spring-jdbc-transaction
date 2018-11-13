package com.example.demo.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.dao.EmpDao;
import com.example.demo.dao.EmpDaoImpl;
import com.example.demo.model.Emp;


/*
 * 
 * Proect 오른쪽 클릭 > Build Path > Add Libraries > jUnit 추가 (legacy project로 생성 시 최초 한 번만)
 * 
 * jUnit 테스트 코드 클래스 생성하려면
 *  패키지 마우스 오른쪽 클릭 > New > jUnit Test Case 선택 >
 *  > Name : EmpDaoImpleTest , class under test : com.example.demo.dao.EmpDao 입력 후 next >
 *  > EmpDao 모두 선택 > finish
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
  "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class EmpDaoImplTest {

	@Autowired
	private EmpDao dao;
	
	//@Test
	public void testDi() {
		System.out.println(dao);
		System.out.println(((EmpDaoImpl)dao).jdbcTemplate);
	}

	//@Test
	public void testInsert() {
		Emp emp = new Emp();
		emp.setEmpno(3201);
		emp.setEname("홍길동");
		emp.setJob("도둑");
		emp.setSal(999);
		
		int affected = dao.insert(emp);
		System.out.println("affected = " + affected);
	}

	//@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	//@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	//@Test
	public void testFindAll() {
		List<Emp> emps = dao.findAll();
		System.out.println(emps.size());
		
		for (Emp emp : emps) {
			System.out.println(emp);
		}
	}
	
	@Test
	public void testCount() {
		int count = dao.count();
		System.out.println("count = " + count);
		
		// 단정메소드: 테스트 목적의 명확성, 테스트 자동화 및 코드의 가독성 향상을 위해서 사용한다.
		assertEquals(count, dao.findAll().size());
	}

}
