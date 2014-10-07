/*
 * DEPRECATED - DELETE THIS CLASS
 */

package Model;

import java.util.GregorianCalendar;

public class Date {
	
	private GregorianCalendar cal;
	private Date date;
	
	public Date() {
		cal = new GregorianCalendar();
		date = today();
	}
	

	
	public Date returnDate() {
		return date;
	}
	
	
	//always returns todays date
	public Date today() {
		Date today = new Date();
		
		return today;
	}




}
