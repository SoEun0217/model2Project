package jdbc.user.dao.test;
//AssertŬ���� �Ʒ��� �ִ� ��� static method�� Import�ϰڴ�.
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

	// Test������ �ݵ�� public void�� �����ؾ���
	// @Test��� Annotation�� �ݵ�� ������ �־���Ѵ�.
	@Test
	public void delete() {
		int cnt=dao.deleteUSer("test1");
		assertEquals(1, cnt);
	}
}
