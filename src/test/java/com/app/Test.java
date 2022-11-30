package com.app;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		String s = "str";
		System.out.println(" s : " + s);
		strConcat(s);
		System.out.println(" strConcat : " + s);
		
		Integer numberInteger = 10;
		System.out.println(" numberInteger : " + numberInteger);
		numberAdd(numberInteger);
		System.out.println(" numberInteger Add : " + numberInteger);
		
		List<String> strings = new ArrayList<>();
		strings.add("str1");
		strings.forEach(item -> {
			System.out.println(item);
		});
		addString(strings);
		strings.forEach(item -> {
			System.out.println(item);
		});
		 
	}
	
	public static void strConcat(String s) {
		s = new String("strConcat");
	}
	
	public static void numberAdd(Integer number) {
		number+=1;
	}
	
	public static void addString(List<String> strings) {
		strings.add("str2");
	}
}
