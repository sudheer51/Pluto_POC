package org.iit.healthcare.mmpinspire;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FutureDateEx {
	
	public static String getFutureDate(int n,String format)
	{		
		
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime());
		 
		cal.add(Calendar.DATE, n);
		System.out.println(cal.getTime());
		//"MM/dd/YYYY"
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String futureDate = sdf.format(cal.getTime());
		System.out.println("Future Date :::" + futureDate);
		
		return futureDate;
	}
	public static void main(String[] args) {
		
		FutureDateEx f1 = new FutureDateEx();
		FutureDateEx f2 = new FutureDateEx();
	 
		System.out.println(f1);
		System.out.println(f1!=f2);
		
		
		getFutureDate(6,"MMMMM/d/YYYY");
		getFutureDate(6,"MM/d/YYYY");
	}
}
