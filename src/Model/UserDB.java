package Model;
import java.sql.*;
import com.mysql.cj.protocol.Resultset;

public class UserDB {
		
	static Connection con =connection.getConnection();
	public Resultset logindb(String username,String password) throws SQLException{
		Statement stmt=con.createStatement();
		return (Resultset) stmt.executeQuery("SELECT * FROM login WHERE username = '"+username+"' AND password = '"+password+"'");
	}
	
	public ResultSet getcid(String username,String password) throws SQLException{
		Statement stmt=con.createStatement();
		return stmt.executeQuery("SELECT c_id FROM login WHERE username = '"+username+"' AND password = '"+password+"'");
	}
	public ResultSet vieworders() throws SQLException{
		Statement stmt=con.createStatement();
		return stmt.executeQuery("select * from menu ");
	}
	public ResultSet selectorders(int itemnumber)  throws SQLException{
		Statement stmt=con.createStatement();
		return stmt.executeQuery("select rate from menu where t_id ="+itemnumber+";" );
	}
	public int insertorders(int itemnumber,int collectcid,float sum,float quantity)  throws SQLException{
		Statement stmt=con.createStatement();
		return stmt.executeUpdate("insert into orders (t_id,c_id,total_amount,quantity) VALUES('"+itemnumber+"',"+collectcid+","+sum+","+quantity+")");
	}
	public ResultSet selectDeleteOrders(int count,int deletecount)  throws SQLException{
		Statement stmt=con.createStatement();
		return stmt.executeQuery("select order_id,items,quantity,total_amount from orders inner join menu on menu.t_id=orders.t_id order by order_date desc limit "+(count-deletecount)+";");
	}

	public int deleteorders(int collectcid,int item_id)  throws SQLException{
		Statement stmt=con.createStatement();
		return stmt.executeUpdate("delete from orders where c_id="+collectcid+"  and order_id ="+item_id+" and order_date > now() - interval 15 minute;");
	}
	public ResultSet confirmorders(int count,int deletecount)  throws SQLException{
		Statement stmt=con.createStatement();
		return stmt.executeQuery("select items,quantity,total_amount from orders inner join menu on menu.t_id=orders.t_id order by order_date desc limit "+(count-deletecount)+";");
	}
	public ResultSet checkname(String username)  throws SQLException{
		Statement stmt=con.createStatement();
		return stmt.executeQuery("select * FROM login WHERE username ='"+username+"'");
	}
	public int insertAccount(String username,String password,String email,String dob) throws SQLException{
		Statement stmt=con.createStatement();
		return stmt.executeUpdate("INSERT INTO login (username, password,email,dob) VALUES ('"+username+"','"+password+"','"+email+"','"+dob+"' )");
	}
	public int alterOrders(int order_id,float quantity) throws SQLException{
		Statement stmt=con.createStatement();
		return stmt.executeUpdate("update orders set quantity="+quantity+" where order_id="+order_id+";");
	}
	public ResultSet viewOrders(int collectcid,int count,int deletecount)  throws SQLException{
		Statement stmt=con.createStatement();
		return stmt.executeQuery("select sum(total_amount) AS 'Total Amount' from orders inner join login on login.c_id=orders.c_id inner join menu on menu.t_id=orders.t_id where login.c_id="+collectcid+" and order_date > now() - interval 1 hour order by order_id desc limit "+(count-deletecount)+";") ;
	}
}