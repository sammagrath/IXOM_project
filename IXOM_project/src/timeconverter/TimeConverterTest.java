package timeconverter;

import static org.junit.Assert.*;

import org.junit.Test;

public class TimeConverterTest {

	@Test
	public void testMidnightDec2HSM() {
		TimeConverter tc = new TimeConverter();
		
		String hms = tc.decToHMS(0.0);
		assertTrue(hms.equals("00:00:00"));
	}

	@Test
	public void testMidnightHSM2Dec() {
		TimeConverter tc = new TimeConverter();
		
		double dec = 0.0;
		assertTrue(dec == tc.HMSToDec("00:00:00"));
	}
	
}
