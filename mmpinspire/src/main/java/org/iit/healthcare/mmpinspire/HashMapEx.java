package org.iit.healthcare.mmpinspire;

import java.util.Set;
import java.util.TreeMap;

 

public class HashMapEx {

	public static void main(String[] args) {
		
		TreeMap<String,String> hMap = new TreeMap<String,String>();
		hMap.put("expectedDoctor","Charlie");
		hMap.put("expectedTime","11AM");
		
		System.out.println(hMap.get("expectedDoctor"));
		
		//all the keys
		Set<String> appointmentDetailsSet = hMap.keySet();
		
		for(String key:appointmentDetailsSet)
		{
			System.out.println("Key Values::: "+ key);
		}
		
		for(String key:appointmentDetailsSet)
		{
			System.out.print("Key Values:::"+ hMap.get(key));
			System.out.println();
		}
		
		
	}
}
