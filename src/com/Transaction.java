package com.bms;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction implements Serializable{
	
	private static final long serialVersionUID=3L;

	private String history;
	
	private LocalDateTime time;
	
	public Transaction(String history) {
		this.history=history;
		
		this.time=LocalDateTime.now();
	}
	
	@Override
	public String toString() {
		
		DateTimeFormatter formatter =
		        DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
		
		
		return this.history+" at "+time.format(formatter);
	}
}
