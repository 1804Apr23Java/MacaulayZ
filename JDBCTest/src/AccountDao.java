import java.util.List;

public interface AccountDao {
	public List<Account> getCaves();
	public Account getAccountById(int id);
}
