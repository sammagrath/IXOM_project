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
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
	
	@Override
	public void start(Stage stage) throws Exception {
		
		
		//!!!PATS ADDITION!!!//
		convertor = new ExceltoCSV();
		populator = new CSV2Array();
		
		
		//Wrapping everything in this StackPane called 'root' was necessary to centre the grid later
		StackPane root = new StackPane();
		
		Scene scene = new Scene(root, 450, 250);
		
		stage.setTitle("IXOM Analysis Tool");
		
		
		//This is the run button and contains the click event for when you click it
		
		Button btnRun = new Button();
		btnRun.setText("Run Analysis Tool");
		
		btnRun.setOnAction(new EventHandler<ActionEvent>(){
		
			
			@Override
			public void handle(ActionEvent arg0){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Flags");
				alert.setContentText(printAll(data));

				alert.showAndWait();
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
					filename = fileChooser.showOpenDialog(stage).toString();
					
					
					
					
					
					//!!!! PATS ADDITION !!!!////
					//Calls csv convertor//
					excelFile = new File(filename);
					dirtyCSVFile = new File("C:/CIPtest/cleanfile3.csv");
					convertor.xls(excelFile, dirtyCSVFile);
					
					try {
						
						//!!!PATS ADDITION!!!//
						data = populator.populateData(dirtyCSVFile, data);
						
						//this is a placeholder way of notifying you which file you selected, I will change this later
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Success");
						alert.setHeaderText("(Your File was added successfully)");
						alert.setContentText("Filepath: " + filename);
						alert.showAndWait();
						
					} catch (FileNotFoundException e) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("ERROR");
						alert.setHeaderText("(There was an error in the retrieval process)");
						alert.setContentText("Filepath: " + filename);
						alert.showAndWait();
						e.printStackTrace();
					}
					
				}
				
			});
		
		
	    	//This is the grid, which is contained within a 
	    	
	        GridPane grid = new GridPane();
	        grid.setAlignment(Pos.CENTER);
	        grid.setVgap(10);
	        grid.setHgap(10);
	        grid.setPadding(new Insets(5, 5, 5, 5));
	       
	        
	        grid.add(new Label("Select a data file to analyse: "), 0, 0);
	        grid.add(btnFileChooser, 0, 1);
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
	
	
	
	public static void main(String args[]) {
		launch(args);
	}
	
	
}
