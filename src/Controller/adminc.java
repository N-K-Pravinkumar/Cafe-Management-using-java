package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import Controller.adminc;
import Model.AdminDB;
import View.CafeManagement;
public class adminc {
	CafeManagement ac=new CafeManagement();
	AdminDB ad=new AdminDB();
	private int choice;
	private String username;
	private String password;
	private String dob;
	private String address;
	private String phoneNo;
	private  String itemname;
	private float rate;
	private int item_id;
	private int day;
	private int month;
	private int year;
	private int n;
	private int cid; 
	private int c_id; 

	public void setchoice(int choice) {this.choice=choice;}
	public void setusername(String username) {this.username=username;}
	public void setpassword(String password) {this.password=password;}
	public void setdob(String dob) {this.dob=dob;}
	public void setaddress(String address) {this.address=address;}
	public void setphoneno(String phoneNo) {this.phoneNo=phoneNo;}
	public void setitemname(String itemname) {this.itemname=itemname;}
	public void setrate(float rate) {this.rate=rate;}
	public void setitemID(int item_id) {this.rate=item_id;}
	public void setday(int day) {this.day=day;}
	public void setmonth(int month) {this.month=month;}
	public void setyear(int year) {this.year=year;}
	public void setn(int n) {this.n=n;}
	public void setcid(int cid) {this.cid=cid;}
	public void setc_id(int c_id) {this.c_id=c_id;}

	public int getchoice(){return choice;}
	public String getusername() {return username;}
	public String getpassword() {return password;}
	public String getdob() {return dob;}
	public String getaddress() {return address;}
	public String getphoneNo() {return phoneNo;}
	public String getitemname(){return itemname;}
	public float getrate() {return rate;}
	public int getitemID(){return item_id;}
	public int getday() {return day;}
	public int getmonth() {return month;}
	public int getyear() {return year;}
	public int getn() {return n;}
	public int getcid() {return cid;}
	public int getc_id() {return c_id;}
	public void vieworders() throws SQLException{
        ResultSet vrslt=ad.viewordersdb();
        ac.printResultset(vrslt);
	}
	public int result() throws Exception{
		int result=ad.Updateprice(getitemname(),getrate());
		return result;
	}
	public int itemresult() throws Exception{
		int result=ad.delete(getitemID());
		return result;
	}
	public void daycheck() throws Exception{
    	ResultSet drslt=ad.daysales(getday());
    	ac.printResultset(drslt);
	}
	public void monthcheck() throws Exception{
    	ResultSet drslt=ad.monthsales(getmonth());
        ac.printResultset(drslt);
	}
	public void yearcheck() throws Exception{
    	ResultSet drslt=ad.yearsales(getyear());
        ac.printResultset(drslt);
	}
	public void viewcid() throws Exception{
    	ResultSet drslt=ad.viewcustomer(getn());
        ac.printResultset(drslt);
	}
	public int deletecid() throws Exception{
		int result=ad.deletecustomer(getcid());
		return result;
	}
	public ResultSet checkname() throws Exception{
        ResultSet result = ad.checkuserAd(getusername());
		return result;
	}
	public int insertadmin() throws Exception{
        int rowsInserted = ad.CreateAdmin(getusername(),getpassword(),getdob(),getaddress(),getphoneNo());
		return rowsInserted;
	}
	public void displaybill() throws SQLException {
    	ResultSet billrslt=ad.createNewCustomer(getc_id());
        ac.printResultset(billrslt);
        ResultSet view=ad.viewOrders(getc_id());
        while(view.next()) {
        	System.out.printf("\n%40sTotal Amount =%s"," ",view.getString(1));
        }
	}
	public  void orders()throws Exception{
        ResultSet resultSet =ad.ordersmenu();
        ac.printResultset(resultSet);
    }
}
