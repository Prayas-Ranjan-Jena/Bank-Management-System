package com.bms;

import java.io.Serializable;
import java.util.ArrayList;


public class Account implements Serializable{
	
	private static final long serialVersionUID=2L;
	
	private ArrayList<Transaction> list = new ArrayList<>();
	/*
	 * here private and final is used such to ensure that no other user can access
	 * it and can easily make any changes
	 */
	private final int accountNo;
	private final String accountHolder;

	/*
	 * Balance been kept private to ensure thet no user is going to change change
	 * the balance
	 */

	private double balance;
	static int cnt = 1;

	/*
	 * Initializes UserName Of The Account Holder Here The static variable cnt is
	 * initialized to 1 cnt++ helps the system to generate a non repetative Account
	 * Number This is how the Whole System Works !!!
	 */

	Account(String accountHolder) {
		this.accountHolder = accountHolder;
		this.accountNo = (cnt + 100000);
		cnt++;
	}
	
	public String getAccountHolder() {
		return accountHolder;
	}
	
	public int getAccountNo() {
		return accountNo;
	}
	
	public double getBalance() {
		return balance;
	}

	public void displayDetails() {
		System.out.println("UserName : " + accountHolder);
		// Prints The UserName Of The Account Holder
		System.out.println("Account Number : " + accountNo);
		// Prints The Account No Of Account Holder
		System.out.println("Current Balance : " + balance);
		// Prints The Current Balance Of The Account Holder
	}

	public void deposit(double amt) throws InvalidAmountException {
		if (amt <= 0) {
			/*
			 * Here If Amount Is 0 or Less Than 0 Then We throw an Custom Exception Into It
			 * InvalidAmountException which will be handed by the implementing files
			 */
			throw new InvalidAmountException("Invalid Amount Provided By The User !!!");
		} else {
			/*
			 * Updating The Balance By Amount Provided
			 */
			balance += amt;
			System.out.println("Deposit Successfull !!!");
		}
	}

	public void withdraw(double amt) throws InvalidAmountException {
		if(amt>balance) {
			/*
			 * This case will run when the amount to be withdrawed is
			 * more than the balance
			 */
			throw new InvalidAmountException("Invalid Balance To Perform The Operation !!!");
		}
		if (amt <= 0) {
			/*
			 * if The Provided Amount Is Less Than 0 or equal to Zero then We throw an
			 * Custom Exception Into It InvalidAmountException which will be handed by the
			 * implementing files
			 */
			throw new InvalidAmountException("Invalid Amount Provided By The User !!!");
		} else {
			/*
			 * Updating The Balance By Amount Provided
			 */
			balance -= amt;
			System.out.println("Withdrawal Successfull !!!");
		}
	}
	
	public void transactionHistory() {
		System.out.println();
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).toString());
		}
		System.out.println();
	}
	
	public void addTransaction(String history) {
		list.add(new Transaction(history));
	}


}
