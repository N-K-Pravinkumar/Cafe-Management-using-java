package View;
import java.sql.*;

import Model.AdminDB;
import Model.UserDB;
import Controller.userc;
import java.util.*;
import Model.connection;
public class User extends CafeManagement{
	static Connection con = connection.getConnection();
	CafeManagement rs=new CafeManagement();
	userc ur=new userc();
	UserDB ub=new UserDB();
	AdminDB ad=new AdminDB();
	@SuppressWarnings("resource")
    public  void login() throws Exception {
        Scanner input=new Scanner(System.in);
	    System.out.print("Username: ");
	    ur.setusername(input.nextLine());
	    System.out.print("Password: ");
	    ur.setpassword(input.nextLine());
	    if(ur.checklogin().next()) {
	        System.out.println("Login successful!");
			ResultSet cresult=ub.getcid(ur.getusername(),ur.getpassword());
	        while(cresult.next()) { 
	            collectcid=cresult.getInt(1);
	        }
	        billing();
	    } else {
	        System.out.println("Invalid username or password.");
	        login();
	        
	    }
	    System.out.println();
	    System.out.println("1.login");
	    System.out.println("2.signup");
	    System.out.println("3.Exit");
	    ur.setchoice(input.nextInt());
	    if(ur.getchoice()==1)
	        login();
	    else if(ur.getchoice()==2)
	    	signup();
	    else {
	    	return;
	    }
	    input.close();
	}	



	public static int collectcid=0;
	public  void billing() throws Exception{
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int count=0,deletecount=0;
        while (true) {
            System.out.println("Welcome to Cafe");
            System.out.println("1. Order item");
            System.out.println("2. delete Order item");
            System.out.println("3. Change Quantiy of Item");
            System.out.println("4. View Order");
            System.out.println("5. log out");
            ur.setchoice(sc.nextInt());
            switch ( ur.getchoice()) {
                case 1:
                     ResultSet resultSet = ub.vieworders();
                     rs.printResultset(resultSet);
                     System.out.println("Enter item number to Order :");
                     ur.setitemno(sc.nextInt());
                     sc.nextLine();
                     System.out.println("Enter quantity of item to Order :");
                     ur.setQuantity(sc.nextFloat());
                     ResultSet resultSt =ub.selectorders(ur.getitemno());
                     float f=0;
                     while (resultSt.next()){
                      f=resultSt.getFloat(1);
                     }
                     float sum=f *ur.getQuantity();
                     int rslt=ub.insertorders(ur.getitemno(),collectcid,sum,ur.getQuantity());
                     if(rslt >0) {
                    	 System.out.println("successfully item add ");
                    	 count++;
                     }else {
                    	 System.out.println("No Items Purchased yet");
                     }
                    break;
                case 2:
                	ResultSet tresult=ub.selectDeleteOrders(count,deletecount);
                	ur.orderdel(tresult);
                    System.out.print("Enter order_id to delete your order:");
                    ur.setitemid(sc.nextInt());
            		int result=ub.deleteorders(collectcid,ur.getitemid());
                    if (result >0) {
                    	System.out.println("Item deleted successfully.");
                    	deletecount++;
                    }else {
                    	System.out.println("Item not deleted Successfully.");
                    }
                    break;
                case 3:
                	ResultSet treesult=ub.selectDeleteOrders(count,deletecount);
                	ur.orderdel(treesult);
                	System.out.println("Enter orderID :");
                	ur.setOrder_id(sc.nextInt());
                	System.out.println("Enter changing quantity :");
                	ur.setQuantity(sc.nextFloat());
                	int alter=ub.alterOrders(ur.getOrder_id(),ur.getQuantity());
                    if (alter>0) {
                    	System.out.println("Quantity changed successfully.");
                    }else {
                    	System.out.println("Quantity not changed Successfully.");
                    }
                    break;
                case 4:
                	ResultSet dresult1=ub.confirmorders(count,deletecount);
                    rs.printResultset(dresult1);
                    ResultSet view=ub.viewOrders(collectcid,count,deletecount);
                    while(view.next()) {
                    	System.out.printf("\n%40sTotal Amount =%s"," ",view.getString(1));
                    }
                	break;
                case 5:
                    System.out.println("Thank you for Ordering Cafe .");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            System.out.println();
        }
	}
        
		@SuppressWarnings("resource")
        public void  signup() throws Exception {
        	@SuppressWarnings("resource")
            Scanner input=new Scanner(System.in);
            System.out.print("Choose a username: ");
            ur.setusername(input.nextLine());
            if (ur.checkname().next()){
                System.out.println("Username already exists, please choose another.");
                signup();
                return;
            }
            System.out.print("Choose a password: ");
            ur.setpassword(input.nextLine());
            System.out.print("Enter your Email_id: ");
            ur.setemail(input.nextLine());
            System.out.print("Enter your DATE OF BIRTH(YYYY-MM-DD): ");
            ur.setdob(input.nextLine());
            if (ur.insertcustomer() > 0) {
                System.out.println("User created successfully!");
                System.out.println();
                System.out.println("***LOG_IN**");
                login();
            } else {
                System.out.println("Failed to create user.");
            }
            input.close();
        }

	}
	

