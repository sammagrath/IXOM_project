package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
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
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import samThreshold.Flag;
import samThreshold.FlagGeneration;
import javafx.scene.text.Text;
import Read_Data.CSV2Array;
import Read_Data.ExceltoCSV;
import Read_Data.dataPoint;

public class Display extends Application {
	
	//!!!PATS ADDITION!!!//
	private File excelFile;
	private File dirtyCSVFile;
	private String filename;
	private ExceltoCSV convertor;
	private CSV2Array populator;
	private ArrayList<dataPoint> data;
	private ArrayList<Flag> flagList;
	
	//**The textfield which displays file path**//
	private TextField textField = new TextField ();
	
	@Override
	public void start(Stage stage) throws Exception {
		
		
		//!!!PATS ADDITION!!!//
		convertor = new ExceltoCSV();
		populator = new CSV2Array();
		
		
		//Wrapping everything in this StackPane called 'root' was necessary to centre the grid later
		StackPane root = new StackPane();
		
		Scene scene = new Scene(root, 450, 450);
		
		stage.setTitle("IXOM Analysis Tool");
		
		
		//This is the run button and contains the click event for when you click it
		
		Button btnRun = new Button();
		btnRun.setText("Run Analysis Tool");
		
		btnRun.setOnAction(new EventHandler<ActionEvent>(){
		
			
			@Override
			public void handle(ActionEvent arg0){
				String filename = textField.getText();
				
				
				
				//!!!! PATS ADDITION !!!!////
				//Calls csv convertor//
				excelFile = new File(filename);
				dirtyCSVFile = new File(excelFile.getParent(), "output.csv");
				convertor.xls(excelFile, dirtyCSVFile);
				
				try {
					
					//!!!PATS ADDITION!!!//
					data = populator.populateData(dirtyCSVFile, data);
					//!!!SAM'S TAINT!!!//
					flagList = new ArrayList<Flag>();
					FlagGeneration f = new FlagGeneration(data);
					f.condThresholds(flagList);
					f.tempThresholds();
					f.endRinseCond();
					
					System.out.println(flagList.size());
					

					Alert alert = new Alert(AlertType.INFORMATION);
DialogPane dialogPane = alert.getDialogPane();
					
					alert.setTitle("Information Dialog");
					alert.setHeaderText("Flags:");
//					alert.setContentText(printAll(data));
					
			
				
					GridPane dialogGrid = new GridPane();
					dialogGrid.setAlignment(Pos.CENTER);
					   dialogGrid.setVgap(10);
					   dialogGrid.setHgap(10);
					   dialogGrid.setPadding(new Insets(5, 5, 5, 5));
					  				        
					  
					   
					   dialogGrid.add(new Label("| Start Time |"), 0, 0);
					   dialogGrid.add(new Label("| End Time |"), 1, 0);
					   dialogGrid.add(new Label("| Phase |"), 2, 0);
					   dialogGrid.add(new Label("| Message |"), 3, 0);
					   dialogGrid.add(new Label("| Target |"), 4, 0);
					   dialogGrid.add(new Label("| Actual |"), 5, 0);
				        
					   dialogPane.setContent(dialogGrid);
					   
					  int counter = 1;
						for (Flag flag : flagList) {
							
							dialogGrid.add(new Label(flag.getStartTime()), 0, counter);
							dialogGrid.add(new Label(flag.getEndTime()), 1, counter);
							dialogGrid.add(new Label(flag.getPhase()), 2, counter);
							dialogGrid.add(new Label(flag.getMessage()), 3, counter);
							dialogGrid.add(new Label(String.valueOf(flag.getTarget())), 4, counter);
							dialogGrid.add(new Label(String.valueOf(flag.getActual())), 5, counter);
				
							
							
							counter++;

						}
			        

					
	// System.out.println(printAll(data));
					alert.showAndWait();
					
				} catch (Exception e) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("ERROR");
					alert.setHeaderText("(There was an error in the retrieval process)");
					alert.setContentText("Please restart the application and try again. Make sure you have selected a valid data file to analyse");
					alert.showAndWait();
					e.printStackTrace();
				}
				
				
				
				
			}
			
		});
		
		
		
      
	        
	        //The following is a button and when clicked it launches a file chooser
	        
	    	Button btnFileChooser = new Button();
	    	btnFileChooser.setText("Choose file");
			
	    	
	    	
	    	btnFileChooser.setOnAction(new EventHandler<ActionEvent>(){
			
				
				@Override
				public void handle(ActionEvent arg0){
					FileChooser fileChooser = new FileChooser();
					fileChooser.setTitle("Open Resource File");
					
					//this String contains the file path of the file that you choose with the fileChooser
					String name = fileChooser.showOpenDialog(stage).toString();
					
					
					textField.setText(name);
					
				
					
				}
				
			});
		
		
	    	//This is the grid, which is contained within a 
	    	
	        GridPane grid = new GridPane();
	        grid.setAlignment(Pos.CENTER);
	        grid.setVgap(10);
	        grid.setHgap(10);
	        grid.setPadding(new Insets(5, 5, 5, 5));
	       
	        
	        grid.add(new Label("Select a data file to analyse: "), 0, 0);
	        grid.add(textField, 0, 1);
	        grid.add(btnFileChooser, 1, 1);
	        grid.add(btnRun, 0, 2);
	      
	       
		
	        root.getChildren().add(grid);
	        stage.setScene(scene);
	        stage.setScene(scene);
	        
	        

		stage.show();
	}
	
	
	//!!!PATS ADDITION!!!//
	public String printAll(ArrayList<dataPoint> data){
		
		String output;
		output = "|   Date   |   Time   | turb | cond | soil | temp | zone | \n" ;
		
		for(dataPoint d: data){
			output = output + d.print();
		}
		
		return output;

	}
	
	
	//SAMS CODE // ANDREW SAYS: I had to abandon this particular method when using the gridpane, but I'm keeping it here in case we need it again
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
	
	
}
