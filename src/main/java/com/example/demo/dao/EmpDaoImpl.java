package com.example.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Emp;

@Repository
public class EmpDaoImpl implements EmpDao {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
//	Row(테이블 행) 정보를 <Emp> 객체에 Mapper(매핑) 하기 위한 로직
//	어느 칼럼의 데이터를 어느 멤버변수에 넣어야 하는지 알려주는 로직
	private RowMapper<Emp> rowMapper = new RowMapper<Emp>() {
		@Override
		public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
			Emp e = new Emp();
			e.setEmpno(rs.getInt("empno"));
			e.setEname(rs.getString("ename"));
			e.setJob(rs.getString("job"));
			e.setSal(rs.getDouble("sal"));
			
			return e;
		}
	};	
	
//	빈 컨테이너에 JdbcTemplate이 없을 때 대신 DataSource를 받아서 처리하는 방식
//	@Autowired
//	public void setDataSource(DataSource dataSource) {
//		this.jdbcTemplate = new JdbcTemplate(dataSource);
//	}
	
//	메소드마다 반복적으로 사용되는 부분을 밖으로 빼내는 방법을 고려해 보자.
	@Override
	public int insert(Emp emp) {
		String sql = "insert into emp(empno, ename, job, sal) values(?, ?, ?, ?)";
		int affected = jdbcTemplate.update(sql, 
				emp.getEmpno(), emp.getEname(), emp.getJob(), emp.getSal());
		
		return affected;	
	}

	@Override
	public int update(Emp emp) {
		String sql = "update emp set ename=?, job=?, sal=? where empno=?";
		return jdbcTemplate.update(sql, 
				emp.getEname(), emp.getJob(), emp.getSal(), emp.getEmpno());
	}

	@Override
	public int delete(int empno) {
		String sql = "delete emp where empno=?";
		return jdbcTemplate.update(sql, empno);
	}

	@Override
	public List<Emp> findAll() {
		String sql = "select empno, ename, job, sal from emp order by empno asc";
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public int count() {
		String sql = "select count(*) from emp";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public Emp findOne(int empno) {
		String sql = "select empno, ename, job, sal from emp where empno=?";
		return jdbcTemplate.queryForObject(sql, rowMapper, empno);
	}

}