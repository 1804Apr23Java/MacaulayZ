import org.junit.jupiter.api.Test;

public class JDBCTest {
	
	/*******************************************************************
	 * Console class
	 ******************************************************************/
	public static final Console console= new Console();
	
	@Test
	public void testLoginMessage() {
		assertEquals("", console.loginMessage());
	}
	
	@Test
	public void testLoginAttempt() {
		assertEquals("", console.loginAttempt());
	}
	/*******************************************************************
	 * IncorrectPasswordException class
	 ******************************************************************/
	@Test
	public void testIncorrectPasswordException() {
		
	}
	
	/*******************************************************************
	 * User class
	 ******************************************************************/
	public static final User userDao = new UserDao();
	
	@Test
	public void testUserAccountCreation() {
		assertEquals("", userDao.createAccount());
	}
	
	@Test
	public void testBankAccountCreation() {
		assertEquals("", userDao.createBankAccount());
	}
	
	@Test
	public void testViewUserAccount() {
		assertEquals("", userDao.viewUserAccount());
	}
	
	@Test
	public void testAccountDeposit() {
		assertEquals("", userDao.deposit());
	}
	
	@Test
	public void testAccountWithdrawal() {
		assertEquals("", user.withdraw());
	}
	@Test
	public void testAccountDeletion() {
		assertEquals("", user.deleteAccount());
	}
	
	@Test
	public void testLogout() {
		assertEquals("", user.logout());
	}
	/*******************************************************************
	 * OverdraftException class
	 ******************************************************************/
	@Test
	public void testOverdraftException() {
		
	}
	
	/*******************************************************************
	 * Superuser class
	 ******************************************************************/
	public static final Superuser superUser = new Superuser();
	
	@Test
	public void testUserView() {
		
	}
	
	@Test
	public void testUserCreation() {
		
	}
	
	public void testUserUpdate() {
		
	}
	
	@Test
	public void testUserDeletion() {
		
	}
	
}
