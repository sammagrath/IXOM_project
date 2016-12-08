package timeconverter;

/*
 * 
 * author: Jacob Hoffland
 * email: jacob.hoffland@gmail.com
 * 
 */

public class TimeConverter {

	//input a decimal between 0 and 1 and return a String for a date in the form hh:mm:ss
	public String decToHMS(double decimalTime){
		String hourMinSec = "";
		int hour = 0, min = 0, second = 0;
		String hourString = "", minString = "", secString = "";
		
		decimalTime = decimalTime*86400;
		
		while(decimalTime >= 3600){
			decimalTime += -3600;
			hour++;
		}
		
		while(decimalTime >= 60){
			decimalTime += -60;
			min++;
		}
		
		while(decimalTime >= 1){
			decimalTime--;
			second++;
		}
		
		if(hour >=0.0 && hour < 24){
			if (hour < 10){
				hourString = "0"+hour;
			} else {
				hourString = ""+hour;
			}
		} else {
			return null;
		}
		
		if(min >=0 && min < 60){
			if (min < 10){
				minString = "0"+min;
			} else {
				minString = ""+min;
			}
		} else {
			return null;
		}
		
		if(second >=0 && second < 60){
			if (second < 10){
				secString = "0"+second;
			} else {
				secString = ""+second;
			}
		} else {
			return null;
		}
		
		hourMinSec = hourString+":"+minString+":"+secString;
		
		return hourMinSec;
	}
	
	
	//input a date in the form hh:mm:ss and return a decimal value between 0 and 1.
	public double HMSToDec(String hourMinSec){
		double decimalTime = 2.0;
		int datelength = hourMinSec.length();
		int unit = 0;
		String hourString = "";
		String minString = "";
		String secondString = "";
		int hour = 0;
		int min = 0;
		int second = 0;
		
		for(int i = 0; i < datelength; i++){
			char current = hourMinSec.charAt(i);
			if(current == ':'){
				unit++;
			} else if (Character.isDigit(current)){
				if(unit == 0){
					hourString += current;
				}else if(unit == 1){
					minString += current;
				}else if(unit == 2){
					secondString += current;
				}
			}
		}
		
		if(!hourString.equals("")){
			hour = Integer.parseInt(hourString);
			
			if(!minString.equals("")){
				min = Integer.parseInt(minString);
			}
			
			if(!secondString.equals("")){
				second = Integer.parseInt(secondString);
				
				decimalTime = ((3600*hour) + (60*min) + second)/86400.0;
			}
		}
		
		return decimalTime;
	}
	
	
}
