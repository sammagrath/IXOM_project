package csvcleaner;

/*
 * 
 * author: Jacob Hoffland
 * email: jacob.hoffland@gmail.com
 * 
 */

public class CSVCleaner {

	public String lineCleaner(String dirtyLine){
		
		String cleanLine = "";
		int lineLength = dirtyLine.length();
		String input = "", previousStep = "";
		int stage = 0, currentStep = -1;
		String date = "", time = "", turb = "", cond = "", soil = "", temp = "", step = "";
		
		for(int i = 0; i < lineLength; i++){
			if(dirtyLine.charAt(i) == ','){
				
				if(stage == 0){
					date = dateTimeCleaner(removeSpaces(input));
					
				} else if (stage == 1){
					time = dateTimeCleaner(removeSpaces(input));
					
				} else if ((stage == 2) || (stage == 3) || (stage == 4) || (stage == 5)){
					turb = decimalCleaner(removeSpaces(input));
					
				} else if (stage == 6){
					step = stepCleaner(removeSpaces(input));
					
					if(!step.equals(previousStep)){
						previousStep = step;
						currentStep++;
					}
				}
				
				input = "";
				stage++;
				
			} else {
				input += dirtyLine.charAt(i);
			}
		}
		
		cleanLine = date+", "+time+", "+turb+", "+cond+", "+soil+", "+temp+", "+currentStep;
		
		return cleanLine;
	}

	
	//remove spaces from a String
	public String removeSpaces(String dirty){
		
		String clean = "";
		int length = dirty.length();
		char c;
		
		for(int i = 0; i < length; i++){
			c = dirty.charAt(i);
			if(c != ' '){
				length += c;
			}
		}
		
		return clean;
	}
	
	
	//input string for date or time and returns in correct format or null if wrong input, currently doesn't deal with strings that are too long eg 19/01/2016
	public String dateTimeCleaner(String dirtyDateTime){
		
		int firstDigit = 0, secondDigit = 0, thirdDigit = 0, length = dirtyDateTime.length();
		String first = "", second = "", third = "", cleanDateTime = "";
		char c;
		boolean dateTime;
		
		if(dirtyDateTime.contains("/")){
			dateTime = true;
		} else if (dirtyDateTime.contains(":")){
			dateTime = false;
		} else {
			return null;
		}
		
		for(int i = 0; i < length; i++){
			c = dirtyDateTime.charAt(i);
			
			if(Character.isDigit(c)){
				if(firstDigit < 2){
					first += c;
					firstDigit++;
					
				} else if (secondDigit < 2){
					second+= c;
					secondDigit++;
					
				} else if (thirdDigit < 2){
					third += c;
					thirdDigit++;
					
				}
			}
		}
		
		if((firstDigit + secondDigit + thirdDigit) == 6){
			if(dateTime){
				cleanDateTime = first+"/"+second+"/"+third;
			} else {
				cleanDateTime = first+":"+second+":"+third;
			}
			return cleanDateTime;
		}
		
		return null;
	}
	
	
	//input string for a decimal and returns in correct format or null if wrong input
	public String decimalCleaner(String dirtyDec){
		
		String cleanDec = "";
		int length = dirtyDec.length();
		int dec = 0, dot = 0;
		char c;
		
		for(int i = 0; i < length; i++){
			c = dirtyDec.charAt(i);
			
			if(Character.isDigit(c)){
				cleanDec += c;
				dec++;
			} else if(c == '.' && dot == 0){
				
				if(dec == 0){
					cleanDec += "0.";
					dot++;
				}
			} else{
				return null;
			}
		}
		
		return cleanDec;
	}
	
	
	//input string for step and removes the .0 from the end or returns null if wrong input
	public String stepCleaner(String dirtyStep){
		
		String cleanStep = "";
		char current;
		int length = dirtyStep.length();
		
		for(int i = 0; i < length; i++){
			current = dirtyStep.charAt(i);
			if(Character.isDigit(current)){
				cleanStep += current;
			} else if(current == '.'){
				break;
			} else {
				return null;
			}
		}
		return cleanStep;
	}
	
}
