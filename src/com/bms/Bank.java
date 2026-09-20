package com.bms;


import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.ObjectInputStream;

public class Bank implements Serializable{
	
	private HashMap<Integer,Account> accounts = new HashMap<>();
	private final transient Scanner sc = new Scanner(System.in);
	private static final long serialVersionUID=1L;
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;
	
	
	Bank(){
		
		try {
			ois=new ObjectInputStream(new FileInputStream("BankData.ser"));
			Bank bank = (Bank)ois.readObject();
			this.accounts = bank.accounts;
			
			ois.close();
		}
		catch(IOException e) {
			System.out.println("No Data Found Currently !!! \n \n");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	
	public void menu() {
		/*
		 * Here A Bank Menu Method been Created To Help The 
		 * User To Traverse freely Through all The Operations Of
		 * Bank
		 */
		bankMenu:
		while(true) {
			System.out.println("---------------BANK MENU---------------");
			System.out.println("1. Create an Account");
			System.out.println("2. Delete an Account");
			System.out.println("3. Deposit To An Account");
			System.out.println("4. Withdraw From an Account");
			System.out.println("5. To Display An Account Details");
			System.out.println("6. To Dispay All The Account Details");
			System.out.println("7. To Check Transaction History");
			System.out.println("8. Transferring Money");
			System.out.println("9. For Exiting Bank");
			System.out.println();
			System.out.println();
			
			System.out.print("Enter The Choice U Want To Perform : ");
			int n=sc.nextInt();
			System.out.println();
			
			switch(n) {
			case 1:
				createAccount();
				break;
			
			case 2:
				deleteAccount();
				break;
			
			case 3:
				deposit();
				break;
				
			case 4:
				withdraw();
				break;
				
			case 5:
				displayAccountDetails();
				System.out.println();
				break;
				
			case 6:
				displayAllAccounts();
				break;
				
			case 7:
				checkTransactionHistory();
				break;
				
			case 8:
				transfer();
				break;
		
			case 9:
				try {
					
					oos=new ObjectOutputStream(new FileOutputStream("BankData.ser"));
					oos.writeObject(this);
					
					oos.close();
					
				} catch (IOException e) {
					
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break bankMenu;
				
				
			default:
				System.out.println("Invalid Choice !!!");
			}
			
			
			
		}
	}
	
	public void createAccount() {
		sc.nextLine();
		
		System.out.print("Enter The UserName Of The Account You Want To Create : ");
		
		String name=sc.nextLine();
		
		Account newAccount = new Account(name);
		
		accounts.put(newAccount.getAccountNo(),newAccount);
		
		System.out.println("Successfully Created Account With UserName : "+name);
		
		System.out.println();
	}
	
	public void deleteAccount() {
		
		sc.nextLine();
		
		System.out.print("Enter The Account Number Of The Account You Want To Delete : ");
		
		Integer actno=sc.nextInt();
		
		Account acnt = accounts.get(actno);
		
		if(acnt==null) {
			
			System.out.println("Currently There Are No Account With Account Number : "+actno);
			return;
		}
				
		accounts.remove(actno);
		System.out.println("Account Has Been Removed Successfully !!!");
		
		System.out.println();
	}
	
	public void deposit() {
		System.out.print("Enter The Account Number In Which You Want To Deposit Your Money : ");
		
		int actno=sc.nextInt();
		
		System.out.println();
		
		Account acnt=accounts.get(actno);	
		
		if(acnt==null) {
			System.out.println("There is no account with account number: " + actno);
			return;
		}
				
		System.out.print("Enter The Amount You Want To Deposit : ");
		double amt=sc.nextDouble();
				
		System.out.println();
		
		try {					
			(acnt).deposit(amt);
		}
		
		catch(InvalidAmountException E) {
			System.out.println(E.getMessage());
		}		
		
	}
	
	
	public void withdraw() {
		System.out.print("Enter The Account Number In Which You Want To Withdraw Your Money : ");
		
		int actno=sc.nextInt();
		
		Account acnt=accounts.get(actno);	
		
		if(acnt==null) {
			System.out.println("There is no account with account number: " + actno);
			return;
		}
				
		System.out.print("Enter The Amount You Want To Withdraw : ");
		double amt=sc.nextDouble();
			
		try {	
			(acnt).withdraw(amt);
		}
				
		catch(InvalidAmountException E) {
			System.out.println(E.getMessage());
		}

	}
	
	public void displayAccountDetails() {
		System.out.print("Enter The Account Number To Display The Account Details : ");
		
		int actno=sc.nextInt();
		Account acnt=accounts.get(actno);	
		
		if(acnt==null) {
			System.out.println("There is no account with account number: " + actno);
			return;
		}		
		acnt.displayDetails();

	}
	
	public void displayAllAccounts() {
		if(accounts.size()==0) {
			System.out.println("Currently There Are No Accounts !!!");
			return;
		}
		
		Set<Integer> set = accounts.keySet();
		
		for(Integer i : set) {
			(accounts.get(i)).displayDetails();
			System.out.println();
		}
	}
	
	public void transfer() {
		System.out.print("Enter The Account No Of Sender's Account : ");
		int actno1=sc.nextInt();
		
		System.out.println();
		
		Account acnt1=accounts.get(actno1);
		
		if(acnt1==null) {
			System.out.println("There is no account with account number: " + actno1);
			return;
		}
		
		System.out.print("Enter The Account No Of Receiver's Account : ");
		int actno2=sc.nextInt();
		
		System.out.println();
		
		Account acnt2=accounts.get(actno2);
		
		if(acnt2==null) {
			System.out.println("There is no account with account number: " + actno2);
			return;
		}
		
		if(actno1==actno2) {
			System.out.println("Money Cannot Be Transfered Within Same Account with Account Number: "+actno1);
			return;
		}
		
		System.out.print("Enter The Amount You Want To Transfer : ");
		double amt=sc.nextDouble();
		
		System.out.println();
		
		try {
			acnt1.withdraw(amt);
			
			acnt2.deposit(amt);
			
			acnt1.addTransaction("Transfered ₹"+amt + " to Account : "+ actno2);
			
			acnt2.addTransaction("Received ₹"+amt + " from  Account : " + actno1);
			
			
			System.out.println();
			System.out.println("₹ "+amt+" transfered successfully from Account "+actno1+" to Account "+actno2);
			System.out.println();
		}
		catch(InvalidAmountException E) {
			System.out.println();
			System.out.println(E.getMessage());
			System.out.println();
		}
	}
	
	public void checkTransactionHistory() {
		System.out.print("Enter The Account No : ");
		
		int n=sc.nextInt();
		
		Account acnt=accounts.get(n);
		
		if(acnt==null) {
			System.out.println("There Is No Account Available With Account Number : "+n);
			return;
		}
		
		acnt.transactionHistory();
	}
	
	public static void main(String [] args) {
		// TODO Auto-generated method stub
		Bank bank = new Bank();
		bank.menu();
	}

}
