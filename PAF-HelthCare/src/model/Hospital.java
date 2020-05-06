package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hospital {

	//Hospital hosObj = new Hospital();
	private Connection connect() {

		Connection con = null;
		try

		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital","root","");
			System.out.println("Database connected");
		}

		catch (Exception e) {

			e.printStackTrace();

		}

		return con;
	}

	public String insertHospital(String hospitalName, String address, String hotline, String contactNumber,
			String disctription) {

		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into patient(`hos_Id`, `hospitalName`, `address`, `contactNumber`, `disctription`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, hospitalName);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, hotline);
			preparedStmt.setString(5, contactNumber);
			preparedStmt.setString(6, disctription);

			// execute the statement
			preparedStmt.execute();
			con.close();
			String newHos = readHospital();
			output = "{\"status\":\"success\", \"data\": \"" + newHos + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
			System.err.println(e.getMessage());

		}
		return output;
	}

	public String readHospital() {

		String output = "";
		try {

			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed

//			output = "<table border='1'><tr><th>Hospital Id</th><th>Hospital</th><th>Address</th><th>Hotline</th><th>Contat Number</th>"
//					+ "<th>Discription</th>" + "<th>Update</th><th>Remove</th></tr>";
//			
			   
			output = "<table border='1'><tr><th>Hospital Id</th><th>Hospital</th><th>Address</th><th>Hotline</th><th>Contact Number</th>"
					+ "<th>Discription</th>"
			        + "<th>Update</th><th>Remove</th></tr>";
			

			String query = "select * from Hospital";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

				String hosId = Integer.toString(rs.getInt("hos_Id"));
				String hosname = rs.getString("hospitalName");
				String hosaddress = rs.getString("address");
				String hoshot = rs.getString("hotline");
				String hoscntact = rs.getString("contactNumber");
				String hosdis = rs.getString("disctription");

				// Add into the html table
			    output += "<tr><td><input id='hidIdUpdate' name='hidIdUpdate' type='hidden' "
			    		+ "value='"+hosId+"'>" + hosId + "</td>";


				//output += "<tr><td>" + hosId + "</td>";
				output += "<td>" + hosname + "</td>";
				output += "<td>" + hosaddress + "</td>";
				output += "<td>" + hoshot + "</td>";
				output += "<td>" + hoscntact + "</td>";
				output += "<td>" + hosdis + "</td>";

				// buttons
			/*	output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"hospital.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" " + "class=\"btn btn-danger\">"
						+ "<input name=\"itemID\" type=\"hidden\" value=\"" + hosId + "\">" + "</form></td></tr>";
			}*/

	        output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td> "
					+ "<td><button name='btnRemove' type='button' value='"+ hosId + "' class='btnRemove btn btn-danger'  data-ID= '" + hosId + "'>remove</button></td></tr>";
			}

			con.close();

			// Complete the html table
			output += "</table>";
		}

		catch (Exception e) {

			output = "Error while reading the patients.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String updateHospital(String hos_Id, String hospitalName, String address, String hotline, String contactNumber, String disctription) {
	 
	String output = "";
	try {
	 Connection con = connect();
	 
	 if (con == null) {
		 return "Error while connecting to the database for updating.";
		 }
	 
	 // create a prepared statement 
	 String query ="UPDATE Hospital SET hospitalName=?,address=?,hotline=?,contactNumber=?,disctription=? WHERE host_Id=?";
	 
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values preparedStmt.setString(1, hospitalName);
	 preparedStmt.setString(2, address); 
	 preparedStmt.setString(3, hotline);
	 preparedStmt.setString(4, contactNumber); 
	 preparedStmt.setString(5,disctription); 
	 preparedStmt.setInt(6, Integer.parseInt(hos_Id));
	 
	 // execute the statement 
	 preparedStmt.execute(); con.close(); 
	 String newHos = readHospital(); output = "{\"status\":\"success\", \"data\": \"" + newHos +"\"}"; 
	 } catch (Exception e) { 
		 output ="{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
	 System.err.println(e.getMessage()); 
	 }
	 return output; 
	 }
	 
	
	public String deleteHospital(String hos_Id) { String output = "";
	 
	try {
	 Connection con = connect();
	 
	 if (con == null) { 
		 return "Error while connecting to the database for deleting."; 
	 }
	 // create a prepared statement 
	 String query = "delete from Hospital where hos_Id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	  // binding values preparedStmt.setInt(1, Integer.parseInt(hos_Id));
	 // execute the statement preparedStmt.execute(); con.close();
	 String newHos = readHospital(); 
	 output = "{\"status\":\"success\", \"data\": \"" + newHos + "\"}"; }
	 
	catch (Exception e) {
	 output ="{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
	System.err.println(e.getMessage()); 
	} 
	return output;
	  }
	 

}
