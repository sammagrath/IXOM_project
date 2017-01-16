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

	private static ArrayList<Threshold> thresholds = new ArrayList();
	private static HashMap<String, ArrayList<Threshold>> powders = new HashMap();
	private static Threshold t;
	

	public void setThresholds(String input, String product, String process) throws IOException {

		File excel = new File(input);

		Scanner tScan = new Scanner(excel);
		tScan.useDelimiter(",");
		
		BufferedReader brTest = new BufferedReader(new FileReader(System.getProperty("user.dir")+ File.separator + "thresholds.csv"));
		
		String text = brTest.readLine();
//		System.out.println(text);
		Scanner pScan = new Scanner(text);
		pScan.useDelimiter(",");
		ArrayList<String> phaseNames = new ArrayList();
		while(pScan.hasNext()) {
			
			Threshold t = new Threshold();
			t.setPhase(pScan.next());
			thresholds.add(t);
		}
		
		String currentLine = "";
		String currentItem = "";
		String saveItem = "";
		
		for(Threshold t:thresholds) {
			System.out.println(t.printThresholds());
		}
		
		int count = 1, index = 0;
//		currentItem = tScan.nextLine();
		Threshold currentThreshold = new Threshold();
		ArrayList<Threshold> tempThresholds = new ArrayList<Threshold>();
		
		while(tScan.hasNextLine()) {
			
			tempThresholds = new ArrayList<Threshold>();
//			currentLine = tScan.nextLine();
//			while(tScan.hasNextLine()) {
//				System.out.println("true");
//				for(Threshold t:thresholds) {
//					
//					t.setCondLower(tScan.nextDouble());
//					t.setCondUpper(tScan.nextDouble());
//					t.setTempLower(tScan.nextDouble());
//					t.setTempUpper(tScan.nextDouble());
//					System.out.println();
//					System.out.println(t.printThresholds());
//				}
//				System.out.println(tScan.next());
			}
			
//			currentThreshold = thresholds.get(index);
//			
//			if((count-2) % 18 ==0){
//				saveItem = tScan.next();
//				currentThreshold.setPhase(saveItem);
//				System.out.println(saveItem);
//				
//			} else if((count-3 % 18 ==0) || (count-7 % 18 ==0) || (count-11 % 18 ==0) || (count-15 % 18 ==0)){
//				currentThreshold.setCondLower(tScan.nextDouble());
//			} else if((count-4 % 18 ==0) || (count-8 % 18 ==0) || (count-12 % 18 ==0) || (count-16 % 18 ==0)){
//				currentThreshold.setCondUpper(tScan.nextDouble());
//			} else if((count-5 % 18 ==0) || (count-9 % 18 ==0) || (count-13 % 18 ==0) || (count-17 % 18 ==0)){
//				currentThreshold.setTempLower(tScan.nextDouble());
//			} else if((count-6 % 18 ==0) || (count-10 % 18 ==0) || (count-12 % 18 ==0) || (count % 18 ==0)){
//				currentThreshold.setTempUpper(tScan.nextDouble());
//				tempThresholds.add(currentThreshold);
//				index++;
//			}else {
//				currentItem = tScan.next();
//			}
//				
//			
//			count++;
//			
//			if((count-1) % 18 == 0){
//				ArrayList<Threshold> toMap = tempThresholds;
//				powders.put(saveItem, toMap);
//			}
//		}
		
		
//		System.out.println(powders.size());
		
//		for(Threshold d : thresholds) {
//			System.out.print(d.getPhase());
//			
//			while(tScan.hasNextLine()) {
//				
//				if(tScan.next().equals(process)) {
//					
//					d.setCondLower(tScan.nextDouble());
//					d.setCondUpper(tScan.nextDouble());
//					d.setTempLower(tScan.nextDouble());
//					d.setTempUpper(tScan.nextDouble());
//				}
//			}
//		}
		
//		for(String s : powders.keySet()) {
//			
//			String key = s.toString();
//			
//			System.out.println(key + " " +powders.get(s).get(0).printThresholds());
//			
////			System.out.println("Key: " + key + ", Value: " + value);
//		}
		
		
		
//		while (scanner.hasNextLine()) {
//
//			if (scanner.next().equals(process)) {
//
//				while (scanner.hasNextDouble()) {
//					thresholds.add(scanner.nextDouble());
//				}
//				powders.put(process, thresholds);
//
//			}
//		}
//		scanner.close();
//		for (Double d : thresholds) {
//			System.out.println(d);
//		}
//		System.out.println("\n" + powders.keySet());
//		System.out.println(powders.get(process));

	}

	public static void main(String[] args) throws IOException {

		FetchThresholds ft = new FetchThresholds();
		System.out.println(System.getProperty("user.dir")+ File.separator + "thresholds.csv");
		
		ft.setThresholds(System.getProperty("user.dir")+ File.separator + "thresholds.csv", "Powders", "Evap (MPC)");

	}
}
