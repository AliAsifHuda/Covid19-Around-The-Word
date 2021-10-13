import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/* This class creates a table to display on the GUI */

public class CovidTable {
	
	private ComboBox<String> sortByList;
	private ComboBox<String> sortingOrderList;
	private HBox hbox = new HBox(20);
	private TableView<CovidStats> table;
	private TextField textField = new TextField();;
	
	public VBox tableview() throws IOException {
		
		initialiseTable();
		
		initialiseSortingOrderList();
		initialiseSortByList();
		
		sortByList.setOnAction(this::sortingListsAction);
		sortingOrderList.setOnAction(this::sortingListsAction);
		
		hbox.getChildren().addAll(sortByList, sortingOrderList);
		
		final VBox vbox = new VBox(10);
		vbox.getChildren().addAll(hbox, textField, table);
		return vbox;
	}
	
	/* Table to display data on the GUI */
	private TableView<CovidStats> initialiseTable() throws IOException{
		table = new TableView<CovidStats>();
		table.setEditable(true);
		
		table.setMinWidth(690);
		table.setMaxWidth(690);
		
		table.setMinHeight(600);
		table.setMaxHeight(600);
		
		table.setFixedCellSize(23);
		
		TableColumn<CovidStats, String> ranking = new TableColumn<>("#");
		ranking.setMaxWidth(30);
		ranking.setCellValueFactory(new PropertyValueFactory<>("ranking"));
		
		TableColumn<CovidStats, String> country = new TableColumn<>("Country");
		country.setMaxWidth(150);
		country.setCellValueFactory(new PropertyValueFactory<>("country"));
		
		TableColumn<CovidStats, String> totalCases = new TableColumn<>("Total Cases");
		totalCases.setMaxWidth(130);
		totalCases.setCellValueFactory(new PropertyValueFactory<>("totalCases"));
		
		TableColumn<CovidStats, String> newCases = new TableColumn<>("New Cases");
		newCases.setMaxWidth(130);
		newCases.setCellValueFactory(new PropertyValueFactory<>("newCases"));
		
		TableColumn<CovidStats, String> totalDeaths = new TableColumn<>("Total Deaths");
		totalDeaths.setMaxWidth(130);
		totalDeaths.setCellValueFactory(new PropertyValueFactory<>("totalDeaths"));
		
		TableColumn<CovidStats, String> newDeaths = new TableColumn<>("New Deaths");
		newDeaths.setMaxWidth(130);
		newDeaths.setCellValueFactory(new PropertyValueFactory<>("newDeaths"));
		
		TableColumn<CovidStats, String> recoveries = new TableColumn<>("Recoveries");
		recoveries.setMaxWidth(130);
		recoveries.setCellValueFactory(new PropertyValueFactory<>("recoveries"));
		
		TableColumn<CovidStats, String> activeCases = new TableColumn<>("Active Cases");
		activeCases.setMaxWidth(130);
		activeCases.setCellValueFactory(new PropertyValueFactory<>("activeCases"));
		
		TableColumn<CovidStats, String> seriousCases = new TableColumn<>("Serious Cases");
		seriousCases.setMaxWidth(100);
		seriousCases.setCellValueFactory(new PropertyValueFactory<>("seriousCases"));
		
		FilteredList<CovidStats> filteredStat = new FilteredList<CovidStats>(getListingsList(), p -> true);// Pass the data to a
		// filtered list
		table.setItems(filteredStat);// Set the table's items using the filtered list
		table.getColumns().addAll(ranking, country, totalCases, newCases, totalDeaths, newDeaths, recoveries,
		activeCases, seriousCases);
		
		textField.setPromptText("Search here!");
		
		textField.setOnKeyReleased(keyEvent -> {
			filteredStat.setPredicate(p -> p.getCountry().toLowerCase().contains(textField.getText().toLowerCase().trim()));
			table.setItems(filteredStat);
		});
		
		return table;
	} 
	
	/**
	* This method sorts all data in the list	
	* @throws IOException
	*/
	private void sortingListsAction(ActionEvent e) {
		SortBy sort = null;
		if (sortByList.getValue() == null) {
			AlertBox.display("ERROR!", "Please select what you want to Sort By in the drop-down list first.");
			e.consume();
		}
		try {
			if (sortByList.getValue().equals("Number of Cases")) {
				sort = new SortByCases();
			} else if (sortByList.getValue().equals("Number of Deaths")) {
				sort = new SortByDeaths();
			} else if (sortByList.getValue().equals("Number of Recoveries")) {
				sort = new SortByRecoveries();
			}
			
			if (sortingOrderList.getValue().equals("Ascending")) {
				table.setItems(sort.sortList(getListingsList(), true));
			} else {
				table.setItems(sort.sortList(getListingsList(), false));
			}
			
		} catch (NullPointerException nPE) {
			// do nothing
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	* Initialise and set values of our sortByList
	*/
	private void initialiseSortByList() {
		sortByList = new ComboBox<>();
		sortByList.setPromptText("Sort by: ");
		sortByList.getItems().add("Number of Cases");
		sortByList.getItems().add("Number of Deaths");
		sortByList.getItems().add("Number of Recoveries");
	}
	
	/**
	* Initialise and set values of our sortingOrderList
	*/
	private void initialiseSortingOrderList() {
		sortingOrderList = new ComboBox<>();
		sortingOrderList.getItems().addAll("Ascending", "Descending");
		// first option (ascending) selected by default
		sortingOrderList.getSelectionModel().selectFirst();
	}
	
	
	public ObservableList<CovidStats> getListingsList() throws IOException{ 
		
		WebCrawler wc = new WebCrawler(); 
		ObservableList<CovidStats> stats = FXCollections.observableArrayList();
		for(CovidStats covidStats : wc.getStats()) { 
			stats.add(covidStats);
		}
		wc.getStats().clear();
		return stats; 
	}
	
	
}