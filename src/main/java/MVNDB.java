import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.lang.ClassNotFoundException;

import com.google.gson.Gson;

public class MVNDB {

	public static void main(String[] args) throws Exception {
		
		try {
			boolean exit = true;
			while (exit) {
		Scanner sc = new Scanner(System.in);
	System.out.println("\t \tChoose One Option:\t \t");
	System.out.println("\t\t 1.read JSON file in concol ");
	System.out.println("\t\t 2.ReadOrderly ");
	System.out.println("\t\t 3. createTable ");
	System.out.println("\t\t 4. insert ");
	System.out.println("\t\t 5. readFromTable ");
	System.out.println("\t\t 6. updateById ");
	System.out.println("\t\t 7. deleteById ");
	System.out.println(" *********************************************** ");
  	 Scanner scanner = new Scanner(System.in);

//     boolean isExit = true;
    int option = sc.nextInt();
	switch (option) {
	
	case 1:
		MVNDB.ReadJsonFile();
	break;

	case 2:
		MVNDB.ReadOrderly();
	break;
	
	case 3:
		MVNDB.createTable();

	break;
	case 4:
		MVNDB.insert();

	break;
	case 5:
		MVNDB.readFromTable();
	break;
	case 6:
		MVNDB.updateById();
	break;
	case 7:
		MVNDB.deleteById();
		break;
	}
			}exit = false;
			} catch (Exception e) {
				System.out.println(e);
			}
	
	}
		
	
		
	public static void ReadJsonFile() throws Exception {
		
		String jsonUrl = "http://universities.hipolabs.com/search?country=United+States";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(jsonUrl)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
	        System.out.println(response.body());
	        
	}
	
	
	
	
	
	public static void ReadOrderly() throws Exception {
		String jsonUrl = "http://universities.hipolabs.com/search?country=United+States";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(jsonUrl)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		Gson gn = new Gson();
		RandomApi[] useGson = gn.fromJson(response.body(), RandomApi[].class);
		for (RandomApi RAC : useGson) {

			System.out.println("web_pages:" + RAC.getWeb_pages());
			System.out.println("state_province:" + RAC.getState_province());
			System.out.println("alpha_two_code:" + RAC.getAlpha_two_code());
			System.out.println("name:" + RAC.getName());
			System.out.println("country:" + RAC.getCountry());
			System.out.println("domains:" + RAC.getDomains());
		}
	}
	
	
	public static void createTable() throws Exception {

			final String url = "jdbc:mysql://localhost:3306/MVNDB";

			   final String user = "root";
			   final String pass = "root";
			   Connection conn=null;
			 try{
				 
			 String sql = ("CREATE TABLE FaizaMVNDB("+"id int Primary Key AUTO_INCREMENT,"
			 +"web_pages varchar(20),"
			 +"state_province varchar(10),"
			 +"alpha_two_code varchar(10),"
			 +"name varchar (20),"
			 +"country varchar(20),"
			 +"domains varchar(20))");
			 Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			   
		     DriverManager.registerDriver(driver);

		     conn = DriverManager.getConnection(url, user,
		             pass);	 
		     Statement st = conn.createStatement();

			   
	         int m=st.executeUpdate(sql);
	         if (m >=  0)
	         	System.out.println("Created table in given database..." );
	            
	         else
	             System.out.println("failed to create");
	 
	         conn.close() ; 	  
	          } catch (Exception ex) {
		           
		            System.err.println(ex);
	   }
	}
	public static void insert() throws Exception {
			String jsonUrl = "http://universities.hipolabs.com/search?country=United+States";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(jsonUrl)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		Gson gn = new Gson();
		RandomApi[] useGson = gn.fromJson(response.body(), RandomApi[].class);
		for (RandomApi RAC : useGson) {
			final String url = "jdbc:mysql://localhost:3306/MVNDB";

			   final String user = "root";
			   final String pass = "root";
			   Connection conn=null;
			 try{
				 
			 String sql ="insert into FaizaMVNDB (web_pages,state_province,alpha_two_code,name,country,domains)values ('"
			 +RAC.getWeb_pages()[0]+"','"+RAC.getState_province()+"','"+ RAC.getAlpha_two_code()+"','"
			 		+RAC.getName()+"','"
			 		+RAC.getCountry()+"','"
			 		+RAC.getDomains()[0]+"')";
			 
			 Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			   
		     DriverManager.registerDriver(driver);

		     conn = DriverManager.getConnection(url, user,
		             pass);	 
		     Statement st = conn.createStatement();

			   
	         int m=st.executeUpdate(sql);
	         if (m >=  0)
	         	System.out.println("inserted in given database..." + sql);
	            
	         else
	             System.out.println("failed");
	 
	         conn.close() ; 	  
	          } catch (Exception ex) {
		           
		            System.err.println(ex);
	   }

		}
	}
	public static void readFromTable(){

		final String url = "jdbc:mysql://localhost:3306/MVNDB";
		   final String user = "root";
		   final String pass = "root";
		   
		   
		   
		  String QUERY = "SELECT * FROM FaizaMVNDB";

		      Connection conn=null;
		      
		 try {
			 conn = DriverManager.getConnection(url, user, pass);
		 Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
         Statement stmt = conn.createStatement();
	     DriverManager.registerDriver(driver);
	     ResultSet rs=stmt.executeQuery(QUERY);
			 while(rs.next()) {
				
				int id=rs.getInt("id");
				String web_pages=rs.getString("web_pages");
				String state_province=rs.getString("state_province");
				String alpha_two_code=rs.getString("alpha_two_code");
				String name=rs.getString("name");
				String country=rs.getString("country");
				String domains=rs.getString("domains");
				
				
				
			     System.out.println("id :" + id);
			     System.out.println("web_pages :" +web_pages);
			     System.out.println("state_province" +state_province);
			     System.out.println("alpha_two_code" +alpha_two_code);
			     System.out.println("name" +name);
			     System.out.println("country"+country);
			     System.out.println("domains"+domains);
			     System.out.println("===========================================================");
			   
			 }
			 conn.close() ;
		 }  catch (Exception ex) {
	           
	            System.err.println(ex);
   }
    }
	
	public static void updateById(){
		
		final String url = "jdbc:mysql://localhost:3306/MVNDB";

		 String user = "root";
		 String pass = "root";
		 Connection conn = null;

		 try {
		 Scanner scanner = new Scanner(System.in);

		  	System.out.println ("input id you want to update");
		      Integer id=scanner.nextInt();
		      String sql = "update FaizaMVNDB set alpha_two_code='MM' where id="+id;
				 
		      
		 
			 Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			   
		     DriverManager.registerDriver(driver);

		     conn = DriverManager.getConnection(url, user,
		             pass);

		     Statement st = conn.createStatement();

		     int m = st.executeUpdate(sql);
		     if (m >=0)
		         System.out.println("update is successful of " +id);
		     else
		         System.out.println("failed");
		     conn.close() ;
		 }

			  catch (Exception ex) {
	           
	            System.err.println(ex);
   }
	    }
	
	public static void deleteById() {
		final String url = "jdbc:mysql://localhost:3306/MVNDB";

		 String user = "root";
		 String pass = "root";
		 try {
		 Scanner scanner = new Scanner(System.in);

		  	System.out.println ("inter id uou want to delete");
		      Integer id=scanner.nextInt();
		      String sql = "delete from  FaizaMVNDB where id="+id;
				 
				 Connection conn = null;
		      
		 
			 Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			   
		     DriverManager.registerDriver(driver);

		     conn = DriverManager.getConnection(url, user,
		             pass);

		     Statement st = conn.createStatement();

		     int m = st.executeUpdate(sql);
		     if (m >=0)
		         System.out.println("deleted is successful of " +id);
		     else
		         System.out.println("failed");

		     conn.close();
		 }

			  catch (Exception ex) {
	           
	            System.err.println(ex);
   }
	    }
	
	
	
}