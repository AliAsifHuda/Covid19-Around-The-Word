
public class CovidStats {
	
	
	private int ranking;
	private String country;
	private int totalCases;
	private int newCases;
	private int totalDeaths;
	private int newDeaths;
	private int recoveries;
	private int activeCases;
	private int seriousCases;
	private int casesPerMillion;
	private float deathsPerMillion;
	private int tests;
	private int population;
	
	/**
	* This class creates an object to store all data
	* @param ranking
	* @param country
	* @param totalCases
	* @param newCases
	* @param totalDeaths
	* @param newDeaths
	* @param recoveries
	* @param activeCases
	* @param seriousCases
	* @param casesPerMillion
	* @param deathsPerMillion
	* @param tests
	* @param testsPerMillion
	* @param population
	*/
	public CovidStats(int ranking, String country, int totalCases, int newCases, int totalDeaths,
	int newDeaths, int recoveries, int activeCases, int seriousCases, int casesPerMillion,
	float deathsPerMillion, int tests, int population) {
		this.ranking = ranking;
		this.country = country;
		this.totalCases = totalCases;
		this.newCases = newCases;
		this.totalDeaths = totalDeaths;
		this.newDeaths = newDeaths;
		this.recoveries = recoveries;
		this.activeCases = activeCases;
		this.seriousCases = seriousCases;
		this.casesPerMillion = casesPerMillion;
		this.deathsPerMillion = deathsPerMillion;
		this.tests = tests;
		this.population = population;
	}
	
	
	
	public int getRanking() {
		return ranking;
	}
	
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public int getTotalCases() {
		return totalCases;
	}
	
	public void setTotalCases(int totalCases) {
		this.totalCases = totalCases;
	}
	
	public int getNewCases() {
		return newCases;
	}
	
	public void setNewCases(int newCases) {
		this.newCases = newCases;
	}
	
	public int getTotalDeaths() {
		return totalDeaths;
	}
	
	public void setTotalDeaths(int totalDeaths) {
		this.totalDeaths = totalDeaths;
	}
	
	public int getNewDeaths() {
		return newDeaths;
	}
	
	public void setNewDeaths(int newDeaths) {
		this.newDeaths = newDeaths;
	}
	
	public int getRecoveries() {
		return recoveries;
	}
	
	public void setRecoveries(int recoveries) {
		this.recoveries = recoveries;
	}
	
	public int getActiveCases() {
		return activeCases;
	}
	
	public void setActiveCases(int activeCases) {
		this.activeCases = activeCases;
	}
	
	public int getSeriousCases() {
		return seriousCases;
	}
	
	public void setSeriousCases(int seriousCases) {
		this.seriousCases = seriousCases;
	}
	
	public int getCasesPerMillion() {
		return casesPerMillion;
	}
	
	public void setCasesPerMillion(int casesPerMillion) {
		this.casesPerMillion = casesPerMillion;
	}
	
	public float getDeathsPerMillion() {
		return deathsPerMillion;
	}
	
	public void setDeathsPerMillion(float deathsPerMillion) {
		this.deathsPerMillion = deathsPerMillion;
	}
	
	public int getTests() {
		return tests;
	}
	
	public void setTests(int tests) {
		this.tests = tests;
	}
	
	public int getPopulation() {
		return population;
	}
	
	public void setPopulation(int population) {
		this.population = population;
	}
	
}