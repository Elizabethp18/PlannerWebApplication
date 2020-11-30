import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppProgram {

	public static void main(String[] args) {
		String jdbcURL = "jdbc:postgresql://localhost:5432/planner";
		String username = "postgres";
		String password = "DevPass12!@";
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, password);
			System.out.println("Successfully connected");
			
			String sql = "SELECT * FROM recipes";
			Statement statement = connection.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next())  {
				int id = result.getInt("id");
				String recipeName = result.getString("recipe_name");
				String instructions = result.getString("ingredients");
				String directions = result.getString("directions");
				
				System.out.printf("%d - %s, %s, %s\n", id, recipeName, instructions, directions);
			}
			
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error connecting to database");
			e.printStackTrace();
		}
	}

}
