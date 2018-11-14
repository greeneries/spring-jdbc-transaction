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
		 * jUnit TestŬ�������� @Transactional @Test �� �� �� ������̼��� ����ϴ� ��� 
		 * �׽�Ʈ�� �� insert, update �� �κ��� �� rollback�Ѵ�. 
		 * �׷��� ������ �׽�Ʈ�� �� �Է��� �����͸� ���¿� �������� �ʾƵ� �ȴ�.
		 */
		System.out.println("---------- transaction test start ----------");
		
		int oldCount = daoImpl.count();
		System.out.println("oldCount = " + oldCount);
		
		Emp emp = new Emp();
		emp.setEmpno(3301);
		emp.setEname("��浿");
		emp.setJob("�Ѹ�������");
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
			emp.setEname("ȫ�浿");
			daoImpl.update(emp);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("---------- transaction test end ----------");
		
	}
	
	@Test
	public void testTransactionRollback() {
		/*
		 * �׽�Ʈ �ó�����
		 * EmpDaoImplŬ�������� �����͸� insert�Ҷ� �Ϻη� unchecked ������ �߻����ѳ�����.
		 * spring���� rollback�� ���� �ϴ��� �׽�Ʈ 
		 */
		
		System.out.println("---------- transaction rollback test start ----------");
		
		int oldCount = daoImpl.count();
		System.out.println("oldCount = " + oldCount);
		
		Emp emp = new Emp();
		emp.setEmpno(3301);
		emp.setEname("ȫ�浿");
		emp.setJob("����");
		emp.setSal(999);
		
		try {
			int affected = daoImpl.insertFail(emp);
			System.out.println("affected = " + affected);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		int nowCount = daoImpl.count();
		System.out.println("nowCount = " + nowCount);
		
		assertEquals("insert �޼ҵ忡�� ���ܹ߻�, Ʈ����� �����̽� ����, "
				+ "�ѹ��� �Ǿ�� �ϱ� ������ oldCount ���� nowCount ���� ���ƾ� �Ѵ�.", 
				oldCount, nowCount);
		
		System.out.println("---------- transaction rollback test end ----------");
	}
	
}
