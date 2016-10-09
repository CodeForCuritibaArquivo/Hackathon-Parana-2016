//AcessaBanco.java
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseAcess implements Runnable {
	private static Connection connection;

	public static GenericMessage requestQuey(String query, RequestType typeRequest ) {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			
			if(typeRequest == RequestType.LOGIN){
				
				
			}else if(typeRequest == RequestType.INFORMATIONS){
				
			}else if(typeRequest == RequestType.START_GPS_TRACKING){
				
			}else if(typeRequest == RequestType.PROCESS_VIAGEM){
				
			}else if(typeRequest == RequestType.ADD_GPS_POSITION){
				
			}

		/*	
			while (rs.next()) {
				String placa = rs.getString("placa");
				String modelo = rs.getString("modelo");
				int anofab = rs.getInt("anofab");
				System.out.println("Placa = " + placa);
				System.out.println("Modelo = " + modelo);
				System.out.println("Ano de Fabicacao = " + anofab);
				System.out.println();
			}*/
			rs.close();
			stmt.close();
//			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public DatabaseAcess() {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(
					Configurations.DB_CONNECT_QUERY, Configurations.DB_LOGIN,
					Configurations.DB_PASSWORD);
			connection.setAutoCommit(false);
			System.out.println("Opened database successfully");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
