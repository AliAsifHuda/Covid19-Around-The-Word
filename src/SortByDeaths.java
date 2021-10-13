import javafx.collections.ObservableList;
/* Sorts by death using lambda functions */
public class SortByDeaths implements SortBy {
	@Override
	public ObservableList<CovidStats> sortList(ObservableList<CovidStats> unsortedList, boolean isAscendingOrder) {
		if (isAscendingOrder) {
			unsortedList.sort((CovidStats covidStats, CovidStats t1)  -> 
			covidStats.getTotalDeaths() - t1.getTotalDeaths());
		}else {
			unsortedList.sort((CovidStats covidStats, CovidStats t1)  -> 
			t1.getTotalDeaths() - covidStats.getTotalDeaths());
			
		}
		return unsortedList;
	}
}
