package genericUtility;

import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	public int getRandomNumber() {
		Random random=new Random();
		int randomNum = random.nextInt(1000);
		return randomNum;
	}
	
	public String getSystemDate() {
		Date dt=new Date();
		String sysDate = dt.toString();
		return sysDate;
	}
	
	public String getSystemDateInFormat() {
		Date dt = new Date();
		String[] dArr = dt.toString().split(" ");
		String day = dArr[0];
		String month = dArr[1];
		String date = dArr[2];
		String time = dArr[3].replace(":", "-");
		String year = dArr[5];
		String dateInFormat = date + " " + month + " " + year + " " + time;
		return dateInFormat;
	}

}
