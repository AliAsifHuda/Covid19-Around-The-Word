import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* This class connects to database and stores all data in a database */
public class CovidDb {
	//connecting initials for database
	private static final String USERNAME = "root"; 
	private static final String PASSWORD =  "Kingscl19";
	private static final String CONN_STRING =
	"jdbc:mysql://localhost/covid?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static PreparedStatement preparedStmt = null;
	
	public static void main(String[] args) throws SQLException, IOException{
		
		Connection conn = null;
		WebCrawler webCrawler = new WebCrawler();
		
		try {
			conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			
			//Drop Database
			System.out.println("Deleting previous data");
			String sql = "DELETE FROM covid_data";
			preparedStmt = conn.prepareStatement(sql);
			preparedStmt.executeUpdate(sql);
			preparedStmt.execute();
			
			System.out.println("updating data");
			
			//adding entries to database
			System.out.print("[");
			for(int i=0 ; i<215; i++ ) {
				String query = " insert into covid_data (country, ranking, total_cases, new_case, total_deaths, new_deaths, recoveries, active_cases, "
				+ "serious_cases, cases_per_million, deaths_per_million, tests, population)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString (1, webCrawler.getStats().get(i).getCountry());
				preparedStmt.setInt(2, webCrawler.getStats().get(i).getRanking());
				preparedStmt.setInt(3, webCrawler.getStats().get(i).getTotalCases());
				preparedStmt.setInt(4, webCrawler.getStats().get(i).getNewCases());
				preparedStmt.setInt(5, webCrawler.getStats().get(i).getTotalDeaths());
				preparedStmt.setInt(6, webCrawler.getStats().get(i).getNewDeaths());
				preparedStmt.setInt(7, webCrawler.getStats().get(i).getRecoveries());
				preparedStmt.setInt(8, webCrawler.getStats().get(i).getActiveCases());
				preparedStmt.setInt(9, webCrawler.getStats().get(i).getSeriousCases());
				preparedStmt.setInt(10, webCrawler.getStats().get(i).getCasesPerMillion());
				preparedStmt.setFloat(11, webCrawler.getStats().get(i).getDeathsPerMillion());
				preparedStmt.setInt(12, webCrawler.getStats().get(i).getTests());
				preparedStmt.setInt(13, webCrawler.getStats().get(i).getPopulation());
				
				preparedStmt.execute();
				if(i % 3 == 0) {
					System.out.print(".");
				}
			}
			System.out.print("]");
			System.out.println("\ndone");
			String queryTwo = "SELECT * FROM covid_data ORDER BY ranking";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(queryTwo);
			
			
			System.out.format("+---------+---------------------------+-----------+---------+------------+----------+----------+------------+-------------+------------------------+-------------------------+----------+-------------+%n");
			System.out.format("| Ranking | Country                   |Total cases|New cases|Total deaths|New deaths|Recoveries|Active cases|Serious cases|Cases per million people|Deaths per million people|Tests     |Population   |%n");
			System.out.format("+---------+---------------------------+-----------+---------+------------+----------+----------+------------+-------------+------------------------+-------------------------+----------+-------------+%n");
			
			while(rs.next()){
				//Retrieve by column name
				int ranking  = rs.getInt("ranking");
				String country = rs.getString("country");
				int totalCases = rs.getInt("total_cases");
				int newCases = rs.getInt("new_case");
				int totalDeaths = rs.getInt("total_deaths");
				int newDeaths = rs.getInt("new_deaths");
				int recoveries  = rs.getInt("recoveries");
				int activeCases = rs.getInt("active_cases");
				int seriousCases = rs.getInt("serious_cases");
				int casesPerMillion = rs.getInt("cases_per_million");
				int deathsPerMillion = rs.getInt("deaths_per_million");
				int tests = rs.getInt("tests");
				int population = rs.getInt("population");
				
				String leftAlignFormat = "| %-8s| %-25s | %-10s| %-8s| %-11s| %-9s| %-9s| %-11s| %-12s| %-23s| %-24s| %-9s| %-12s| %n";
				System.out.format(leftAlignFormat, ranking , country, totalCases, newCases, totalDeaths, newDeaths, recoveries, activeCases, seriousCases, casesPerMillion, deathsPerMillion, tests, population);
			}
			System.out.format("+---------+---------------------------+-----------+---------+------------+----------+----------+------------+-------------+------------------------+-------------------------+----------+-------------+%n");
			
		} catch (SQLException e) {
			System.err.println(e);
		}finally {
			if (conn != null) {
				conn.close();
			}
			
		}
	}
	
	
}
