package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.example.demo.dao.EmpDao;
import com.example.demo.model.Emp;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private EmpDao empDao;
	@Autowired
	private DataSourceTransactionManager transactionManager;

	@Override
	public int insert(Emp emp) throws Exception {
		
// transaction definition은 4가지 속성을 어떻게 적용할 것인가?
	
/* 트랜잭션 격리 레벨 (Isolation Level) 출처: http://idkbj.tistory.com/31
 * ISOLATION_DEFAULT : 개별적인 PlatformTransactionManager를 위한 디폴트 격리 레벨
 * ISOLATION_READ_UNCOMMITTED : 격리 레벨 중 가장 낮은 격리 레벨이다. 
 *                              이 격리 레벨은 다른 Commit 되지 않은 트랜잭션에 의해 변경된 데이터를 볼 수 있기 때문에 거의 트랜잭션의 기능을 수행하지 않는다.
 * ISOLATION_READ_COMMITTED : 대 개의 데이터베이스에서의 디폴트로 지원하는 격리 레벨이다. 
 *                            이 격리 레벨은 다른 트랜잭션에 의해 Commit 되지 않은 데이터는 다른 트랜잭션에서 볼 수 없도록 한다. 
 *                            그러나 개발자들은 다른 트랜잭션에 의해 입력되거나 수정된 데이터는 조회할 수 있다.
 * ISOLATION_REPEATABLE_READ : ISOLATION_READ_COMMITTED 보다는 다소 엄격한 격리 레벨이다. 
 *                             이 격리 레벨은 다른 트랜잭션이 새로운 데이터를 입력했다면 새롭게 입력된 데이터를 조회할 수 있다는 것을 의미한다.
 * ISOLATION_SERIALIZABLE : 가장 많은 비용이 들지만 신뢰할 만한 격리 레벨을 제공하는 것이 가능하다. 
 *                          이 격리 레벨은 하나의 트랜잭션이 완료된 후에 다른 트랜잭션이 실행하는 것처럼 지원한다.          
 */
	

/* 전달 행위 (Propagation Behavior) 출처: http://idkbj.tistory.com/31
 * PROPAGATION_REQUIRED	: 이미 하나의 트랜잭션이 존재한다면 그 트랜잭션을 지원하고, 트랜잭션이 없다면 새로운 트랜잭션을 시작한다.
 * PROPAGATION_SUPPORTS : 이미 트랜잭션이 존재한다면 그 트랜잭션을 지원하고, 트랜잭션이 없다면 비-트랜잭션 현태로 수행한다.
 * PROPAGATION_MANDATORY : 이미 트랜잭션이 존재한다면 그 트랜잭션을 지원하고, 활성화된 트랜잭션이 없다면 예외를 던진다.
 * PROPAGATION_REQUIRES_NEW : 언제나 새로운 트랜잭션을 시작한다. 이미 활성화된 트랜잭션이 있다면 일시 정지한다.
 * PROPAGATION_NOT_SUPPORTED : 활성화된 트랜잭션을 가진 수행을 지원하지 않는다. 언제나 비-트랜잭션으로 수행하고 존재하는 트랜잭션은 일시 정지한다.
 * PROPAGATION_NEVER : 활성화된 트랜잭션이 존재하더라도 비-트랜잭션적으로 수행한다. 활성화된 트랜잭션이 존재한다면 예외를 던진다.
 * PROPAGATION_NESTED : 활성화된 트랜잭션이 존재한다면 내포된 트랜잭션으로 수행된다. 작업 수행은 TransactionDefinition.PROPAGATION_REQUIRED 으로 세팅된 것처럼 수행된다.
 */
		
		
		
/*
 * 이미 하나의 트랜잭션이 존재한다면 그 트랜잭션을 지원하고, 트랜잭션이 없다면 새로운 트랜잭션을 시작한다.
 */
		
		DefaultTransactionDefinition transactionDefinition = new 
    DefaultTransactionDefinition();
		// 아래 설정은 모두 디폴트 값을 사용하고 있다.
		transactionDefinition.setPropagationBehavior(
    TransactionDefinition.PROPAGATION_REQUIRED); // 
		transactionDefinition.setIsolationLevel(
    TransactionDefinition.ISOLATION_DEFAULT); // database default 값
		transactionDefinition.setTimeout(-1); // -1은 시간제한을 주지 않겠다는 의미 
		transactionDefinition.setReadOnly(false); // 조회쿼리 수행할 때 true로 변경하면 이 transaction을 제치고 다른 transaction이 먼저 작업하는 것을 허용

		TransactionStatus transactionStatus = transactionManager
    .getTransaction(transactionDefinition);
		
		int affected = 0;
		
		try {
			// 상단 부분 코드 : Around:Before Advice
			
			// ************************************
			// Delegation: 타겟 메소드의 핵심로직을 호출한다.
			affected = empDao.insert(emp);
			// ************************************
			
			// 하단 부분 코드 : Around:After Advice
			
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			transactionManager.rollback(transactionStatus);
			throw e;
		}

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

	@Override
	public int count() throws Exception {
		return empDao.count();
	}

	@Override
	public Emp findOne(int empno) throws Exception {
		return empDao.findOne(empno);
	}

}