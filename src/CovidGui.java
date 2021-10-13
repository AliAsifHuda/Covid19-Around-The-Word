import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.stage.FileChooser;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

/* This class create a GUI for user through which the user can
view, search, filter and sort data. The user can also generate 
a CSV file  to further analyse the data  */
public class CovidGui extends Application { 
	
	
	private VBox vbox = new VBox(10);
	private FileChooser fileChooser = new FileChooser();
	private String content = "";	
	
	@Override 
	public void start(Stage stage) throws IOException {
		CovidTable covidTable = new CovidTable();   
		
		vbox.setMinSize(700, 700); 
		vbox.setMaxSize(700, 700);
		vbox.setMinHeight(720);
		vbox.setMaxHeight(720);
		vbox.setPadding(new Insets(10, 10, 10, 10)); 
		vbox.setAlignment(Pos.CENTER);
		
		
		Label label = new Label("COVID-19 CORONAVIRUS PANDEMIC");
		VBox table = covidTable.tableview();
		
		Button buttonSave = new Button("Generate csv file");
		buttonSave.setOnAction(this::saveAction);
		
		vbox.getChildren().addAll(label, table, buttonSave);
		vbox.getStylesheets().add("myCss.css");
		Scene scene = new Scene(vbox);
		stage.setScene(scene); 
		stage.setMinHeight(840);
		stage.setMaxWidth(820);
		stage.show(); 
	}      
	
	/**
	* @throws IOException
	* Create CSV file with all data  
	*/
	public void csvGenrator() throws IOException{
		WebCrawler m = new WebCrawler();
		WebCrawler.fetchData();
		
		content += "Country, Total Cases, New Cases, Total Deaths, New Deaths, Recoveries, Active Cases, Serious Cases, Cases Per Million," + 
		"DeathsPerMillion, Tests, Population \n";
		
		for(int i = 0; i < 215; i++) {
			content +=
			m.getStats().get(i).getCountry() + "," +
			m.getStats().get(i).getTotalCases() + "," +
			m.getStats().get(i).getNewCases() + "," +
			m.getStats().get(i).getTotalDeaths() + "," +
			m.getStats().get(i).getNewDeaths() + "," +
			m.getStats().get(i).getRecoveries() + "," +
			m.getStats().get(i).getActiveCases() + "," +
			m.getStats().get(i).getSeriousCases() + "," +
			m.getStats().get(i).getCasesPerMillion() + "," +
			m.getStats().get(i).getDeathsPerMillion() + "," +
			m.getStats().get(i).getTests() + "," +
			m.getStats().get(i).getPopulation() + "\n";
		}
		
		try {
			FileWriter myWriter = new FileWriter("covid.csv");
			myWriter.write(content);
			myWriter.close();
		}catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}  
	}
	
	//main file saving method wit dialog box
	private void saveAction(ActionEvent event) {
		try {
			csvGenrator();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Window stage = vbox.getScene().getWindow();
		fileChooser.setTitle("save");
		fileChooser.setInitialFileName("covid");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("csv files (*.csv)", "*.csv");
		fileChooser.getExtensionFilters().add(extFilter);
		
		File file = fileChooser.showSaveDialog(stage);
		
		if(file != null){
			SaveFile(content, file);
		}
		
	}
	
	//helper method to save file
	private void SaveFile(String content, File file){
		try {
			FileWriter fileWriter = null;
			fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}
	
	public static void main(String args[]){ 
		launch(args); 
	} 
}