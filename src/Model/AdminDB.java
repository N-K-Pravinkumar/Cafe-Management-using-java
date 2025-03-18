package Model;
import java.sql.*;

public class AdminDB {
	static Connection con =connection.getConnection();

	public ResultSet logindb(String username,String password) throws Exception{
		Statement stmt=con.createStatement();
		return stmt.executeQuery("SELECT * FROM adminlogin WHERE username = '"+username+"' AND Password = '"+password+"'");
	}
	public ResultSet viewordersdb() throws SQLException{
		Statement stmt=con.createStatement();
		return stmt.executeQuery("select order_id,items,quantity,order_date from orders inner join menu on menu.t_id=orders.t_id order by order_date;");
		}
	public int Updateprice(String itemname,Float rate) throws Exception{
		Statement stmt=con.createStatement();
		return stmt.executeUpdate("insert into menu(items,rate) values ('"+itemname+"',"+rate+");");
	}
	public int delete(int item_id) throws SQLException {
		Statement stmt=con.createStatement();
		return stmt.executeUpdate("delete from menu where t_id = "+item_id+";");
	}
	public ResultSet daysales(int day) throws SQLException{
		Statement stmt=con.createStatement();
		return stmt.executeQuery("select items,count(items),sum(total_amount) as 'total amount' from orders inner join menu on orders.t_id=menu.t_id where day(order_date)="+day+" group by items;");
	}
	public ResultSet monthsales(int month) throws SQLException{
		Statement stmt=con.createStatement();
		return stmt.executeQuery("select items,count(items),sum(total_amount) as 'total amount' from orders inner join menu on orders.t_id=menu.t_id where month(order_date)="+month+" group by items;");
	}
	public ResultSet yearsales(int year) throws SQLException{
		Statement stmt=con.createStatement();
		return stmt.executeQuery("select items,count(items),sum(total_amount) as 'total amount' from orders inner join menu on orders.t_id=menu.t_id where year(order_date)="+year+" group by items;");
	}
	public ResultSet viewcustomer(int n) throws SQLException{
		Statement stmt=con.createStatement();
		return stmt.executeQuery("select * from login where c_id="+n+";");
	}
	public int deletecustomer(int cid) throws SQLException{
		Statement stmt=con.createStatement();
		return stmt.executeUpdate("delete from login where c_id = "+cid+";");
	}
	public ResultSet checkuserAd(String username) throws SQLException{
		Statement stmt=con.createStatement();
		return stmt.executeQuery("select * FROM adminlogin WHERE username ='"+username+"'");
	}
	public ResultSet createcustomer(String username) throws SQLException{
		Statement stmt=con.createStatement();
		return stmt.executeQuery( "select * FROM login WHERE username ='"+username+"'");
	}
	public int insertCustomer(String username,String passwrd,String email,String dob) throws SQLException{
		Statement stmt=con.createStatement();
		return stmt.executeUpdate("INSERT INTO login (username, password,email,dob) VALUES ('"+username+"','"+passwrd+"','"+email+"','"+dob+"' )");
	}

	public int CreateAdmin(String username,String Password,String dob,String Address,String phoneNo) throws SQLException{
		Statement stmt=con.createStatement();
		return stmt.executeUpdate("INSERT INTO adminlogin (username, password,dob,address,PhoneNumber) VALUES ('"+username+"','"+Password+"','"+dob+"','"+Address+"',+'"+phoneNo+"')");
	}
	public ResultSet createNewCustomer(int c_id) throws SQLException{
		Statement stmt=con.createStatement();
		return stmt.executeQuery("select items,quantity,total_amount AS 'Amount' from orders inner join login on login.c_id=orders.c_id inner join menu on menu.t_id=orders.t_id where login.c_id="+c_id+" and order_date > now() - interval 1 hour order by order_id desc ;");
	}
	public ResultSet viewOrders(int c_id)  throws SQLException{
		Statement stmt=con.createStatement();
		return stmt.executeQuery("select sum(total_amount) AS 'Total Amount' from orders inner join login on login.c_id=orders.c_id inner join menu on menu.t_id=orders.t_id where login.c_id="+c_id+" and order_date > now() - interval 1 hour order by order_id desc") ;
	}
	public  ResultSet ordersmenu() throws SQLException {
		Statement stmt=con.createStatement();
		return stmt.executeQuery("select * from menu");
	}

}