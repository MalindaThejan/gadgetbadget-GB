package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Inventor {

	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gadgetbadgetdb", "root", "");

			// For testing
			System.out.print("Successfully connected");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public String readInventor() {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Inventor Name</th>" + "<th>Inventor Email</th><th>Inventor Type</th>"
					+ "<th>Inventor Contact</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from inventor";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

				String InventorID = Integer.toString(rs.getInt("InventorID"));
				String InventorName = rs.getString("InventorName");
				String InventorEmail = rs.getString("InventorEmail");
				String InventorType = rs.getString("InventorType");
				String InventorContact = Integer.toString(rs.getInt("InventorContact"));

				// Add into the html table

				output += "<tr><td><input id='hidInventorUpdate' name='hidInventorUpdate' type='hidden' value='"
						+ InventorID + "'>" + InventorName + "</td>";

				output += "<td>" + InventorEmail + "</td>";
				output += "<td>" + InventorType + "</td>";
				output += "<td>" + InventorContact + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-InventorID='"
						+ InventorID + "'>" + "</td></tr>";

			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Customer Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Insert Customer
	public String insertInventor(String InventorName, String InventorEmail, String InventorType,
			String InventorContact) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into inventor (`InventorID`,`InventorName`,`InventorEmail`,`InventorType`,`InventorContact`)"
					+ " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, InventorName);
			preparedStmt.setString(3, InventorEmail);
			preparedStmt.setString(4, InventorType);
			preparedStmt.setString(5, InventorContact);

			// execute the statement
			preparedStmt.execute();
			con.close();

			// Create JSON Object to show successful msg.
			String newInventor = readInventor();
			output = "{\"status\":\"success\", \"data\": \"" + newInventor + "\"}";
		} catch (Exception e) {
			// Create JSON Object to show Error msg.
			output = "{\"status\":\"error\", \"data\": \"Error while Inserting Inventor.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Update Customer
	public String updateInventor(String InventorID, String InventorName, String InventorEmail, String InventorType,
			String InventorContact) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE inventor SET InventorName=?,InventorEmail=?,InventorType=?,InventorContact=? WHERE InventorID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, InventorName);
			preparedStmt.setString(2, InventorEmail);
			preparedStmt.setString(3, InventorType);
			preparedStmt.setInt(4, Integer.parseInt(InventorContact));
			preparedStmt.setInt(5, Integer.parseInt(InventorID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			// create JSON object to show successful msg
			String newInventor = readInventor();
			output = "{\"status\":\"success\", \"data\": \"" + newInventor + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while Updating Customer Details.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteInventor(String InventorID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "DELETE FROM inventor WHERE InventorID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(InventorID));
			// execute the statement
			preparedStmt.execute();
			con.close();

			// create JSON Object
			String newInventor = readInventor();
			output = "{\"status\":\"success\", \"data\": \"" + newInventor + "\"}";
		} catch (Exception e) {
			// Create JSON object
			output = "{\"status\":\"error\", \"data\": \"Error while Deleting Inventor.\"}";
			System.err.println(e.getMessage());

		}

		return output;
	}

}
