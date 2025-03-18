package Controller;

import java.sql.ResultSet;
import View.CafeManagement;
import java.sql.SQLException;

import Model.UserDB;
public class userc{
	UserDB ur=new UserDB();
	CafeManagement cm=new CafeManagement();
	private String username;
	private String Password;
	private  String email;
	private String dob;
	private int choice;
	private int item_id;
	private int itemno;
	private float quantity;
	private int order_id;
	
	public void setchoice(int choice) {this.choice=choice;}
	public void setusername(String username) {this.username=username;}
	public void setpassword(String Password) {this.Password=Password;}
	public void setemail(String email) {this.email=email;}
	public void setdob(String dob) {this.dob=dob;}
	public void setitemid(int item_id) {this.item_id=item_id;}
	public void setitemno(int itemno) {this.itemno=itemno;}
	public void setQuantity(float quantity) {this.quantity = quantity;}
	
	public String getusername() {return username;}
	public String getpassword() {return Password;}
	public String getemail(){return email;}
	public String getdob() {return dob;}
	public int getchoice(){return choice;}
	public int getitemid(){return item_id;}
	public int getitemno(){return itemno;}
	public float getQuantity() {return quantity;}

	public ResultSet checkname() throws SQLException {
        ResultSet result = ur.checkname(getusername());
		return result;
	}
	public int insertcustomer() throws SQLException{
        int rowsInserted =ur.insertAccount(getusername(),getpassword(),getemail(),getdob());
		return rowsInserted;
	}
	public ResultSet checklogin() throws SQLException{
		ResultSet r=(ResultSet) ur.logindb(getusername(),getpassword());
		return r;
	}
	public void orderdel(ResultSet tresult) throws SQLException{
		cm.printResultset(tresult);
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	
} 