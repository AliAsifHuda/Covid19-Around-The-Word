/* This class prints all covid data in tabular form in the console */
import java.io.IOException;

public class CovidConsole {
    
    public static void PrintToCOnsole() throws IOException {
        System.out.format("+---------+---------------------------+-----------+---------+------------+----------+----------+------------+-------------+------------------------+-------------------------+----------+-------------+%n");
        System.out.format("| Ranking | Country                   |Total cases|New cases|Total deaths|New deaths|Recoveries|Active cases|Serious cases|Cases per million people|Deaths per million people|Tests     |Population   |%n");
        System.out.format("+---------+---------------------------+-----------+---------+------------+----------+----------+------------+-------------+------------------------+-------------------------+----------+-------------+%n");
        
        WebCrawler m = new WebCrawler(); 
        
        for(int i = 0; i < 215; i++){
            int ranking = m.getStats().get(i).getRanking();   
            String country = m.getStats().get(i).getCountry(); 
            int totalCases = m.getStats().get(i).getTotalCases();        
            int newCases = m.getStats().get(i).getNewCases();          
            int totalDeaths = m.getStats().get(i).getTotalDeaths();       
            int newDeaths = m.getStats().get(i).getNewDeaths();         
            int recoveries  = m.getStats().get(i).getRecoveries();        
            int activeCases = m.getStats().get(i).getActiveCases();       
            int seriousCases = m.getStats().get(i).getSeriousCases();      
            int casesPerMillion = m.getStats().get(i).getCasesPerMillion();  
            float deathsPerMillion = m.getStats().get(i).getDeathsPerMillion();
            int tests = m.getStats().get(i).getTests();            
            int population = m.getStats().get(i).getPopulation();       
            
            String leftAlignFormat = "| %-8s| %-25s | %-10s| %-8s| %-11s| %-9s| %-9s| %-11s| %-12s| %-23s| %-24s| %-9s| %-12s| %n";
            System.out.format(leftAlignFormat, ranking , country, totalCases, newCases, totalDeaths, newDeaths, recoveries, activeCases, seriousCases, casesPerMillion, deathsPerMillion, tests, population);
        }
        
        System.out.format("+---------+---------------------------+-----------+---------+------------+----------+----------+------------+-------------+------------------------+-------------------------+----------+-------------+%n");
    }
}
