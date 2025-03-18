package View;
import java.sql.Connection;

import java.sql.ResultSet;
import java.util.Scanner;
import Controller.adminc;
import Controller.userc;
import Model.AdminDB;
import Model.connection;
public class Admin extends  CafeManagement{

	static Connection con = connection.getConnection();
	CafeManagement ac=new CafeManagement();
	adminc ad=new adminc();
	AdminDB ab=new AdminDB();
	userc ur=new userc();
	public void adminlogin() throws Exception {
    	Scanner input=new Scanner(System.in);
    	System.out.println();
        System.out.print("Username: ");
        ad.setusername(input.nextLine());
        System.out.print("Password: ");
        ad.setpassword(input.nextLine());
        ResultSet r=ab.logindb(ad.getusername(),ad.getpassword());
        if(r.next()) {
            System.out.println("Login successful!");
            adminhome();
        } else {
            System.out.println("Invalid login");
            adminlogin();
        }
        input.close();
    }
	@SuppressWarnings("resource")
    public  void adminhome() throws Exception{
    	Scanner sc=new Scanner(System.in);
    	System.out.println();
        System.out.println("1. View orders ");
        System.out.println("2. Update item and prices");
        System.out.println("3. Delete item ");
        System.out.println("4. View sales report by day ");
        System.out.println("5. View sales report by month ");
        System.out.println("6. View sales report by year ");
        System.out.println("7. View customer detail");
        System.out.println("8. delete customer detail");
        System.out.println("9. update customer detail");
        System.out.println("10. Create new admin account");
        System.out.println("11. Display bill by c_id");
        System.out.println("12. log out");
        System.out.print("Enter your choice: ");
        
        ad.setchoice(sc.nextInt());
        int password = 1234;
        if(ad.getchoice() == 1){
        	ad.vieworders();
            adminhome();
        }
        else if(ad.getchoice()==2) {
                System.out.print("Enter password to update item prices: ");
                int enteredPassword = sc.nextInt();
                sc.nextLine();
                if (enteredPassword == password) {
                	System.out.print("Enter Item Name: ");
                	ad.setitemname(sc.nextLine());
                	System.out.print("Enter Amount: ");
                	ad.setrate(sc.nextFloat());
                    if (ad.result() >0) {
                    System.out.println("Item prices inserted successfully.");
                    adminhome();
                    }
                } else {
                    System.out.println("Incorrect password. Please try again.");
                    adminhome();
                }
        }
        else if(ad.getchoice() ==3){
                System.out.println("Enter item_id to delete ");
                ad.setitemID(sc.nextInt());
                if (ad.itemresult() >0) {
                System.out.println("Item deleted successfully.");
                adminhome();
                }
        }
        else if(ad.getchoice() ==4) {
        	System.out.print("Enter day to check :");
        	ad.setday(sc.nextInt());
        	ad.daycheck();
        	adminhome();
        }else if(ad.getchoice() ==5) {
           	System.out.print("Enter month to check :");
           	ad.setmonth(sc.nextInt());
           	ad.monthcheck();
            adminhome();
        }else if(ad.getchoice() ==6) {
        	System.out.print("Enter year to check :");
        	ad.setyear(sc.nextInt());
        	ad.yearcheck();
            adminhome();
        }
        else if(ad.getchoice() ==7) {
        	System.out.print("Enter customer_id  to view :");
        	ad.setn(sc.nextInt());
        	ad.viewcid();
        	adminhome();
        }
        else if (ad.getchoice()==8) {
        	System.out.print ("Enter customer_id to delete :");
        	ad.setcid(sc.nextInt());
            if (ad.deletecid() >0) {
            System.out.println("Item deleted successfully.");
            }
            else {
            	System.out.println("No yet Found");
            }
            adminhome();
        }else if(ad.getchoice() ==9) {
        	System.out.print("Choose a username: ");
        	ur.setusername(sc.nextLine());
            if (ur.checkname().next()){
                System.out.println("Username already exists, please choose another.");
                return;
            }
            System.out.print("Choose a password: ");
    	    ur.setpassword(sc.nextLine());
            System.out.print("Enter your Email_id: ");
            ur.setemail(sc.nextLine());
            System.out.print("Enter your DATE OF BIRTH(YYYY-MM-DD): ");
            ur.setdob(sc.nextLine());
            if (ur.insertcustomer() > 0) {
                System.out.println("User created successfully!");
            }else {
            	System.out.println("Fail to create USER");
            }adminhome();
        }
        else if(ad.getchoice()==10) {
        	System.out.print("Enter a username: ");
        	ad.setusername(sc.nextLine());
            if (ad.checkname().next()){
                System.out.println("Username already exists, please choose another.");
            }
            System.out.print("Enter a password: ");
            ad.setpassword(sc.nextLine());
            System.out.print("Enter your DATE OF BIRTH(YYYY-MM-DD): ");
            ad.setdob(sc.nextLine());
            System.out.print("Enter your Address(In single Line): ");
            ad.setaddress(sc.nextLine());
            System.out.print("Enter your Phone Number: ");
            ad.setphoneno(sc.nextLine());
            if (ad.insertadmin()> 0) {
                System.out.println("User created successfully!");
            } else {
                System.out.println("Failed to create user.");
            }
            adminhome();

        }
        else if(ad.getchoice()==11) {
        	System.out.print("ENTER customerID :");
        	ad.setc_id(sc.nextInt());
            ad.displaybill();
            adminhome();

        }
        else if(ad.getchoice()==12) {
        	System.out.println();
        	System.out.println("Admin login closed....");
        }
        else {
             System.out.println("Invalid choice. Please try again.");
             adminhome();
        }
        System.out.println();
        sc.close();
    }

}