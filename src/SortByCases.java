import javafx.collections.ObservableList;

/* Sorts by cases using lambda functions */

public class SortByCases implements SortBy {
	@Override
	public ObservableList<CovidStats> sortList(ObservableList<CovidStats> unsortedList, boolean isAscendingOrder) {
		if (isAscendingOrder) {
			unsortedList.sort((CovidStats covidStats, CovidStats t1)  -> 
			covidStats.getTotalCases() - t1.getTotalCases());
		}else {
			unsortedList.sort((CovidStats covidStats, CovidStats t1)  -> 
			t1.getTotalCases() - covidStats.getTotalCases());
		}
		return unsortedList;
	}
}
