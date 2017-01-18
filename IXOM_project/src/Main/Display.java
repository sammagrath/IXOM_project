package Main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import samThreshold.Flag;
import samThreshold.FlagGeneration;
import Read_Data.CSV2Array;
import Read_Data.ExceltoCSV;
import Read_Data.PhaseNamesFromCSV;
import Read_Data.DataPoint;
import decayAnalysis.Analyser;
import decayAnalysis.Coordinate;
import decayAnalysis.Quintuple;
import decayAnalysis.RegressionAndParameters;

public class Display extends Application {

	// !!!PATS ADDITION!!!//
	private ExceltoCSV convertor = new ExceltoCSV();
	private CSV2Array populator = new CSV2Array();
	private GraphGenerator graphGenerator;
	
	private ArrayList<DataPoint> data;
	private ArrayList<Flag> flagList;
	
	private File excelFile, dirtyCSVFile;

	// **The textfield which displays file path**//
	private TextField textField = new TextField();
	
	private String selection;

	@Override
	public void start(Stage stage) throws Exception {

		stage.setTitle("IXOM Analysis Tool");
		stage.setResizable(false);
		
		// Wrapping everything in this StackPane called 'root' was necessary to centre the grid later
		StackPane root = new StackPane();
		root.setId("pane");
		Scene scene = new Scene(root, 450, 450);
		scene.getStylesheets().add("cobra.css");

		// Sam's Addition - Instantiation of drop-down menu for selecting CIP process
		ComboBox<String> combobox = setUpComboBox();
		combobox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				selection = combobox.getValue();
				
			}
		});

		// This is the run button and contains the click event for when you click it
		Button btnRun = new Button();
		btnRun.setId("btnRun");
		btnRun.setText("Run Analysis Tool");
		btnRun.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				String filename = textField.getText();
				System.out.println(selection);
				// !!!! PATS ADDITION !!!!////
				// Calls csv convertor//
				excelFile = new File(filename);
				dirtyCSVFile = new File(excelFile.getParent(), "output.csv");
				try {
					convertor.xls(excelFile, dirtyCSVFile, 0);
				} catch (EncryptedDocumentException e1) {

					e1.printStackTrace();
				} catch (InvalidFormatException e1) {

					e1.printStackTrace();
				}

				try {
					// henry's addition
					File RawCSV = new File(excelFile.getParent(), "RawOutput.csv");
					convertor.xls(excelFile, RawCSV, ExceltoCSV.getFinalSheetNumber(excelFile) - 1);
					ArrayList<String> phaseNames = PhaseNamesFromCSV.returnPhaseNames(RawCSV);

					// !!!PATS ADDITION!!!//
					data = populator.populateData(dirtyCSVFile, data);
					
					//set up Map
					DataPoint.setMap(data, phaseNames);
					
					// !!!SAM'S ADDITION!!!//
					/*
					 * instantiates Flag Array, Flag Generation object - it is
					 * passed data array and contains metricTaker object for
					 * performing flag tests thrown flags are added to array and
					 * then passed to the application
					 */
					flagList = new ArrayList<Flag>();
					FlagGeneration f = new FlagGeneration(data,phaseNames,selection);
					
					
					//Print results of curve fitting, remove later
					analyseData(data);
						
					f.thresholds(flagList);

					Alert alert = new Alert(AlertType.INFORMATION);

					// this line allows the alert box to accept a gridpane for displaying the flags
					DialogPane dialogPane = alert.getDialogPane();

					alert.setTitle("Information Dialog");
					alert.setHeaderText("Flags for " + excelFile.getName() + ":");
					// alert.setContentText(printAll(data));

					// gridpane is set up here
					GridPane dialogGrid = new GridPane();
					setUpGrid(dialogGrid);

					// print if there are any flags, else say that there are no flags
					if (flagList.size() > 0) {

						setGridLabelConstant(dialogGrid);

						// this line sets the content of the dialogpane (which
						// is inside the alert box) to be the gridpane (which
						// contains all flag information)
						dialogPane.setContent(dialogGrid);

						int counter = 1;
						for (Flag flag : flagList) {

							setGridLabelVary(dialogGrid, flag, counter);
							counter++;
						}

						
						Button print = new Button();
						print.setText("Export to File");
						print.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								
								exportSummary(stage, flagList, excelFile);

							}

						});

						dialogGrid.add(print, 5, counter);

					} else {
						dialogGrid.add(new Label("No flags to display"), 0, 0);
						dialogPane.setContent(dialogGrid);
					}

					String shortname = excelFile.getName().substring(0, excelFile.getName().lastIndexOf('.'));
					graphGenerator = new GraphGenerator();
					graphGenerator.generateGraphs(data, shortname, flagList, phaseNames);
					
					alert.showAndWait();

				} catch (Exception e) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("ERROR");
					alert.setHeaderText("(There was an error in the retrieval process)");
					alert.setContentText(
							"Please restart the application and try again. Make sure you have selected a valid data file to analyse");
					alert.showAndWait();
					e.printStackTrace();
				}

			}

		});

		// The following is a button and when clicked it launches a file chooser
		Button btnFileChooser = new Button();
		btnFileChooser.setText("Choose file");
		btnFileChooser.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Resource File");

				// this String contains the file path of the file that you
				// choose with the fileChooser
				String name = fileChooser.showOpenDialog(stage).toString();

				textField.setText(name);

			}

		});

		// This is the grid, which is contained within a

		GridPane grid = new GridPane();
		grid.setId("grid");
		//calls method grid settings
		setUpGrid(grid);

		grid.add(new Label("Select a data file to analyse: "), 0, 0);
		grid.add(textField, 0, 1);
		
		grid.add(btnFileChooser, 1, 1);
		
		
		grid.add(combobox, 0, 2);
		grid.add(btnRun, 0, 3);

		root.getChildren().add(grid);
		stage.setScene(scene);
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		stage.show();
	}
	
	
	public void setUpGrid(GridPane grid){
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(10);
		grid.setHgap(10);
		grid.setPadding(new Insets(5, 5, 5, 5));
	}
	
	
	public void exportSummary (Stage stage, ArrayList<Flag> flagList, File excelFile){
		

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(" Save/Export Flag Summary");
		fileChooser.setInitialFileName("Flag Summary - "
				+ excelFile.getName().substring(0, excelFile.getName().lastIndexOf("."))
				+ ".txt");
		File file = fileChooser.showSaveDialog(stage);

		if (file != null) {
			try {
				PrintWriter writer = new PrintWriter(file, "UTF-8");

				writer.println();
				writer.println("Flag Summary: " + excelFile.getName().substring(0,
						excelFile.getName().lastIndexOf(".")));
				writer.println("Date: " + (String) data.get(4).getDate());

				for (Flag flag : flagList) {
					
					writer.println("\nFlag: " + flag.getMessage());
					writer.println("Phase: " + flag.getPhase());
					writer.println("Start: " + flag.getStartTime());
					writer.println("End: " + flag.getEndTime());
					writer.println("Threshold: " + flag.getTarget());
					writer.println("Actual: " + flag.getActual());

				}

				writer.close();

				Alert success = new Alert(AlertType.INFORMATION);
				// this line allows the alert box to accept a gridpane for displaying the flags
				success.setTitle("Information Dialog");
				success.setHeaderText("Flags Exported Successfully");
				success.showAndWait();

			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
		
	}
	
//ewf
	// !!!PATS ADDITION!!!//
	public String printAll(ArrayList<DataPoint> data) {

		String output;
		output = "|   Date   |   Time   | turb | cond | soil | temp | zone | \n";

		for (DataPoint d : data) {
			output = output + d.print();
		}

		return output;

	}

	
	// SAMS CODE // ANDREW SAYS: I had to abandon this particular method when
	// using the gridpane, but I'm keeping it here in case we need it again
	public String printFlags(ArrayList<Flag> flagList) {

		String output;

		output = "| Start Time | End Time | Phase | Message | Target | Actual  | \n";

		for (Flag flag : flagList) {

			output = output + flag.print();
		}

		return output;
	}

	
	
	public static void main(String args[]) {
		launch(args);
	}

	
	//method for testing Curve Fitting etc, prints out results to console
	public void analyseData(ArrayList<DataPoint> data){
		Analyser a = new Analyser();
		
		ArrayList<ArrayList<DataPoint>> LOC = a.splitByZones(data);
		
		System.out.println("Results for Conductivity curve fitting");
		for(ArrayList<DataPoint> dp: LOC){
			DataPoint point = dp.get(0);
			String zoneString = DataPoint.getMap().get(point).toString();
			
			if(zoneString.contains("RINSE") && !zoneString.contains("PRERINSE")){
				ArrayList<Coordinate> coords = a.findSteepestCond(dp);
				
				RegressionAndParameters reg = new RegressionAndParameters(coords);
				Quintuple quintuple = reg.leastSquaresFitting(coords);
				
				System.out.println("Curve for "+zoneString+" estimated as: y = "+quintuple.getA()+"exp("+quintuple.getB()+"x) with r^2 value of "+quintuple.getrSquared());
				System.out.println("With start time: "+quintuple.getStartTime()+" and end time: "+quintuple.getEndTime());
			}
		}
	}
	
	public ComboBox<String> setUpComboBox(){
		ComboBox<String> combobox = new ComboBox<String>();
		combobox.setId("combobox");
		combobox.getItems().addAll(
				"Concentrate Lines",
				"Drier & Fluid beds",
				"Evap Preheat (MPC)",
				"Evap Preheat (WMP/SMP)",
				"Evap (MPC)",
				"Evap (WMP/SMP)",
				"Ingredients Oils",
				"Vitamin/Minerals System"

				);
		combobox.setValue("Select Process");
		return combobox;
	}
	
	public void setGridLabelConstant(GridPane grid){
		grid.add(new Label("| Start Time |"), 0, 0);
		grid.add(new Label("| End Time |"), 1, 0);
		grid.add(new Label("| Phase |"), 2, 0);
		grid.add(new Label("| Message |"), 3, 0);
		grid.add(new Label("| Target |"), 4, 0);
		grid.add(new Label("| Actual |"), 5, 0);
	}
	
	public void setGridLabelVary(GridPane grid, Flag flag, int counter){
		grid.add(new Label(flag.getStartTime()), 0, counter);
		grid.add(new Label(flag.getEndTime()), 1, counter);
		grid.add(new Label(flag.getPhase()), 2, counter);
		grid.add(new Label(flag.getMessage()), 3, counter);
		grid.add(new Label(String.valueOf(flag.getTarget())), 4, counter);
		grid.add(new Label(String.valueOf(flag.getActual())), 5, counter);
	}
		
}
