import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

/* Web vrawler to scrap covid data using Jsoup library */

public class WebCrawler {
	public WebCrawler() throws IOException {
		fetchData();
	}
	
	private static ArrayList<CovidStats> stats =  new ArrayList<>();
	private static int ranking, totalCases, newCases, totalDeaths, newDeaths, totalRecovered,
	activeCases, seriousCritical, casesPerMillion, tests,  population; 
	private static String country;
	private static float deathsPerMillion;
	
	/* Fetches data from a website and then adds it to the CovidStats object */
	public static void fetchData() throws IOException{
		Document doc = Jsoup.connect("https://www.worldometers.info/coronavirus/").get();
		
		Element table = doc.select("table").get(0); //select the first table.
		Elements rows = table.select("td"); 
		
		int firstTableRow = 176;
		for (int tableRow = firstTableRow ; tableRow <= 5080; tableRow++) {
			
			Element row = rows.get(tableRow);            	
			int numberOfColumns = 22;
			int currentCell = tableRow % numberOfColumns;
			switch(currentCell) {
				case 0:            			
				ranking = Integer.parseInt(row.text());
				break;
				case 1: 
				country = row.text();
				break;
				case 2: 
				String tempTotalCases = row.text();
				tempTotalCases = tempTotalCases.replaceAll(",", "");
				totalCases = Integer.parseInt(tempTotalCases);
				break;
				case 3:
				String tempNewCases = row.text();
				if (tempNewCases.equals("")){
					newCases = 0;
				}else {
					tempNewCases = tempNewCases.replaceAll(",", "");
					newCases = Integer.parseInt(tempNewCases);
				}
				break;
				case 4:
				String tempTotalDeaths = row.text();
				if (tempTotalDeaths.equals("")){
					totalDeaths = 0;
				}else {
					tempTotalDeaths = tempTotalDeaths.replaceAll(",", "");
					totalDeaths = Integer.parseInt(tempTotalDeaths);
				}
				break;
				case 5:
				String tempNewDeaths = row.text();
				if (tempNewDeaths.equals("")){
					newDeaths = 0;
				}else {
					tempNewDeaths = tempNewDeaths.replaceAll(",", "");            				 
					newDeaths = Integer.parseInt(tempNewDeaths);
				}
				break;
				case 6:
				String tempTotalRecovered = row.text();
				if (tempTotalRecovered.equals("")||tempTotalRecovered.equals("N/A")){
					newDeaths = 0;
				}else {
					tempTotalRecovered = tempTotalRecovered.replaceAll(",", "");
					totalRecovered = Integer.parseInt(tempTotalRecovered);
				}
				break;
				case 8:
				String tempActiveCases = row.text();
				if (tempActiveCases.equals("")||tempActiveCases.equals("N/A")){
					activeCases = 0;
				}else {
					tempActiveCases = tempActiveCases.replaceAll(",", "");
					activeCases = Integer.parseInt(tempActiveCases);
				}
				break;
				case 9:
				String tempSeriousCritical = row.text();
				if (tempSeriousCritical.equals("")){
					seriousCritical = 0;
				}else {
					tempSeriousCritical = tempSeriousCritical.replaceAll(",", "");
					seriousCritical = Integer.parseInt(tempSeriousCritical);
				}
				break;
				case 10:
				String tempCasesPerMillion = row.text();
				if (tempCasesPerMillion.equals("")){
					casesPerMillion = 0;
				}else {
					tempCasesPerMillion = tempCasesPerMillion.replaceAll(",", "");
					casesPerMillion = Integer.parseInt(tempCasesPerMillion);
				}
				break;
				case 11:
				String tempDeathsPerMillion = row.text();
				if (tempDeathsPerMillion.equals("")){
					deathsPerMillion = 0;
				}else {
					tempDeathsPerMillion = tempDeathsPerMillion.replaceAll(",", "");
					deathsPerMillion = Float.parseFloat(tempDeathsPerMillion);
				}
				break;
				case 12:
				String temptests = row.text();
				if (temptests.equals("")){
					tests = 0;
				}else {
					temptests = temptests.replaceAll(",", "");
					tests = Integer.parseInt(temptests);
				}
				break;
				case 14:
				String tempPopulation = row.text();
				if (tempPopulation.equals("")){
					population = 0;
				}else {
					tempPopulation = tempPopulation.replaceAll(",", "");
					population = Integer.parseInt(tempPopulation);
				}
				stats.add(new CovidStats(ranking, country, totalCases, newCases, totalDeaths, newDeaths, totalRecovered, 
				activeCases, seriousCritical, casesPerMillion, deathsPerMillion, tests, population));  
				break;              
				default: break;
			}
			
		}
	}
	
	public ArrayList<CovidStats> getStats() {
		return stats;
	}
	
	
	
}
