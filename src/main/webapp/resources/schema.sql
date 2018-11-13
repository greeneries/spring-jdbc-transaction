DROP TABLE IF EXISTS emp;

CREATE TABLE emp (
	empno INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	ename VARCHAR(100),
	job VARCHAR(100),
	sal DOUBLE
) ENGINE=InnoDB;

DROP TABLE IF EXISTS member;

-- 이미 존재하는 테이블의 정보를 이용하여 새 테이블 member를 만든다.
-- 필터: 이미 존재하는 테이블의 데이터는 복사하지 않는다.
-- 주의: 이미 존재하는 테이블의 키 설정은 복사되지 않는다.
CREATE TABLE member SELECT * FROM emp WHERE 1=0;