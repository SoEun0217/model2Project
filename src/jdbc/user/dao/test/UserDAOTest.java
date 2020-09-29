package jdbc.user.dao.test;
//Assert클래스 아래에 있는 모든 static method를 Import하겠다.
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import jdbc.user.dao.UserDAO;

public class UserDAOTest {
	UserDAO dao;	

	@Before
	public void init() {
		dao=new UserDAO();
	}

	// Test에서는 반드시 public void로 시작해야함
	// @Test라는 Annotation을 반드시 선언해 주어야한다.
	@Test
	public void delete() {
		int cnt=dao.deleteUSer("test1");
		assertEquals(1, cnt);
	}
}
