package samThreshold;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import Read_Data.ExceltoCSV;

public class FetchThresholds {
	
	private static ArrayList<Double> thresholds = new ArrayList();
	private static HashMap<String, ArrayList<Double>> powders = new HashMap();
	private static Threshold t;

	
	public void setThresholds(String input, String product, String process) throws FileNotFoundException {
		
		File excel = new File(input);

		Scanner scanner = new Scanner(excel);
        scanner.useDelimiter(",");
        while(scanner.hasNextLine()){
        	     	
        if(scanner.next().equals(process)){
        	
        	while(scanner.hasNextDouble()) {
        			thresholds.add(scanner.nextDouble());
        	}
        	powders.put(process, thresholds);
        
        }
        }
        scanner.close();
        for(Double d : thresholds) {
        	System.out.println(d);
        }
        System.out.println("\n" + powders.keySet());
        System.out.println(powders.get(process));
        
	}


	public static void main(String[] args) throws IOException {
		
		FetchThresholds ft = new FetchThresholds();
		ft.setThresholds("/Users/magratsam/Downloads/IXOM/thresholds.csv", "Powders", "Evap (MPC)");
		
        


	}
}
