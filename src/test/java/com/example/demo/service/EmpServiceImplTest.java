package com.example.demo.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.EmpDao;
import com.example.demo.dao.EmpDaoImpl;
import com.example.demo.model.Emp;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
  "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class EmpServiceImplTest {

	@Autowired
	private EmpDaoImpl daoImpl;
	
	
	@Transactional
	@Test
	public void testTransaction() {
		
		/*
		 * jUnit Test클래스에서 @Transactional @Test 두 개 다 어노테이션을 사용하는 경우 
		 * 테스트할 때 insert, update 한 부분을 다 rollback한다. 
		 * 그렇기 때문에 테스트할 때 입력한 데이터를 구태여 삭제하지 않아도 된다.
		 */
		System.out.println("---------- transaction test start ----------");
		
		int oldCount = daoImpl.count();
		System.out.println("oldCount = " + oldCount);
		
		Emp emp = new Emp();
		emp.setEmpno(3301);
		emp.setEname("고길동");
		emp.setJob("둘리아저씨");
		emp.setSal(999);
		try {
			int key = daoImpl.insert(emp); // insert 
			System.out.println("key = " + key);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		int countAfterInsert = daoImpl.count();
		System.out.println("countAfterInsert = " + countAfterInsert);
		
	
		try {
			emp.setEname("홍길동");
			daoImpl.update(emp);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("---------- transaction test end ----------");
		
	}
	
	@Test
	public void testTransactionRollback() {
		/*
		 * 테스트 시나리오
		 * EmpDaoImpl클래스에서 데이터를 insert할때 일부로 unchecked 에러를 발생시켜놓았음.
		 * spring에서 rollback을 실제 하는지 테스트 
		 */
		
		System.out.println("---------- transaction rollback test start ----------");
		
		int oldCount = daoImpl.count();
		System.out.println("oldCount = " + oldCount);
		
		Emp emp = new Emp();
		emp.setEmpno(3301);
		emp.setEname("홍길동");
		emp.setJob("도둑");
		emp.setSal(999);
		
		try {
			int affected = daoImpl.insertFail(emp);
			System.out.println("affected = " + affected);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		int nowCount = daoImpl.count();
		System.out.println("nowCount = " + nowCount);
		
		assertEquals("insert 메소드에서 예외발생, 트랜잭션 어드바이스 적용, "
				+ "롤백이 되어야 하기 때문에 oldCount 값과 nowCount 값은 같아야 한다.", 
				oldCount, nowCount);
		
		System.out.println("---------- transaction rollback test end ----------");
	}
	
}
