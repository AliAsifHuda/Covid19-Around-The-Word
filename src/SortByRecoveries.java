import javafx.collections.ObservableList;

/* Sorts by recovweries using lambda functions */

public class SortByRecoveries implements SortBy {
	@Override
	public ObservableList<CovidStats> sortList(ObservableList<CovidStats> unsortedList, boolean isAscendingOrder) {
		if (isAscendingOrder) {
			unsortedList.sort((CovidStats covidStats, CovidStats t1)  -> 
			covidStats.getRecoveries() - t1.getRecoveries());
		}else {
			unsortedList.sort((CovidStats covidStats, CovidStats t1)  -> 
			t1.getRecoveries() - covidStats.getRecoveries());
			
		}
		return unsortedList;
	}
}
