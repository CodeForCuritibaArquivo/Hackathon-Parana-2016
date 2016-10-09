import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConsultaBanco {
	public static void main(String args[]) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("localhost:5000", "postgres",
					"postgres");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Taxi;");
			while (rs.next()) {
				String placa = rs.getString("placa");
				String modelo = rs.getString("modelo");
				int anofab = rs.getInt("anofab");
				System.out.println("Placa = " + placa);
				System.out.println("Modelo = " + modelo);
				System.out.println("Ano de Fabicacao = " + anofab);
				System.out.println();
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}
}