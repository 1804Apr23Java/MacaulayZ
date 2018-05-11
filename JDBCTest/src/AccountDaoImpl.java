import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao{
	private String filename = "connection.properties";

	@Override
	public List<Account> getAccount() {
		List<Account> al = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			// using a Statement - beware SQL injection
			String sql = "SELECT * FROM Account";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// move through result set
			while (rs.next()) {
				int id = rs.getInt("ACCOUNT_ID");
				String name = rs.getString("ACCOUNT_NAME");
				int maxAccounts = rs.getInt("MAX_ACCOUNTS");
				al.add(new Account(id, name, maxAccounts));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return al;
	}

	@Override
	public Account getAccountById(int id) {
		Account a = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			// use a prepared statement
			String sql = "SELECT * FROM CAVE WHERE ACCOUNT_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			// do something with result
			if (rs.next()) {
				String name = rs.getString("ACCOUNT_NAME");
				int maxBears = rs.getInt("MAX_ACCOUNTS");
				a = new Account(id, name, maxAccounts);
			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return a;
	}
}
