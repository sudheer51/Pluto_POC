package org.iit.healthcare.mmpinspire;

import java.util.Random;

public class RandomEx {
	
	public static void main(String[] args) {
		
		Random rand = new Random();
		//LowerBoundary : 0
		//UpperBoundary : 1000
		//formula: lbound+rand.nextInt(ubound-lbound)
		int lbound=50;
		int ubound=1000;
		//int randNo = rand.nextInt(ubound);
		
		int randNo = lbound+rand.nextInt(ubound);//-> 0 to 1000
		System.out.println(randNo);
		
		lbound=97;
		ubound=122;
		char lowerCase = (char)(lbound+rand.nextInt(ubound-lbound));
		System.out.println("LowerCase Char:::" + lowerCase);
		
		
		lbound=65;
		ubound=90;
		char upperCase =(char)( lbound+rand.nextInt(ubound-lbound));
		System.out.println("Uppercase Char::" + upperCase);
		
		String randomEmail="AUTQA"+upperCase+lowerCase+randNo+"@gmail.com";
		System.out.println("Randome Email"+ randomEmail);
		
		
		
	}

}
