package com.app;

import org.springframework.transaction.annotation.Transactional;

public class GarbageCollection {
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		System.out.println("finalize");
	}
	
 
	public static void main(String[] args) {
		GarbageCollection collection = new GarbageCollection();
		collection = null;
		System.gc();
		System.out.println("loggger");
	}

}
