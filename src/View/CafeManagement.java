package View;

import java.sql.*;
import java.util.Scanner;
import Controller.cafeMC;
import Model.connection;

public class CafeManagement {
	static Connection con = connection.getConnection();
	public static void main(String[] args) throws Exception { 
		User user=new User();
		cafeMC cm=new cafeMC();
		Admin ad=new Admin();
		cm.setHome(0);
		cm.setAdmin(0);
		Scanner input=new Scanner(System.in);
			System.out.println("**** WELCOME TO CAFE ****");
	        System.out.println("Enter a Number for UR Choice");
			System.out.println("1.User");
			System.out.println("2.Admin");
			cm.setChoice(input.next().charAt(0));
			if(cm.getChoice()=='2') {
				System.out.println("1.Admin LOGIN");
				System.out.println("2.Exit");
				@SuppressWarnings("unused")
				boolean exit=false;
				cm.setAdmin(input.nextInt());
				switch(cm.getAdmin()) { 
	            case 1:
	                ad.adminlogin();
	                break;
	            case 2:
	                exit = true;
	                break;
	            default:
	                System.out.println("Invalid choice, please try again.");
	                break;
				}
			}
			else if(cm.getChoice()=='1'){
			System.out.println("1.LOG_IN");
			System.out.println("2.SIGN_UP");
			System.out.println("3.Exit");            
			@SuppressWarnings("unused")
			boolean exit=false;
			cm.setHome(input.nextInt());
			switch(cm.getHome()){ 
            case 1:
            	user.login();
                break;
            case 2:
            	user.signup();
                break;
            case 3:
                exit=true;
                break;
            default:
                System.out.println("Invalid choice, please try again.");
                break;
			}
			}
			else {
				System.out.println("Invalid Login");
				
			}
			input.close();
	}
	public void printResultset(ResultSet r)throws SQLException{
		ResultSetMetaData  rsm=(ResultSetMetaData) r.getMetaData();
		int col=rsm.getColumnCount();
		for(int i=1;i<=col;i++) {
			System.out.printf("%20s|",rsm.getColumnName(i).toUpperCase());
		}
		System.out.println();
		while(r.next()) {
			for(int i=1;i<=col;i++) {
				System.out.printf("%20s",r.getString(i)+" ","%20s");
			}
			System.out.println();
		}
	}
}