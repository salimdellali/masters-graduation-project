package databasemanager;

import java.sql.*;

public class AccessDataBaseConnector {
	
	static public Connection conn = null;
	static public Statement st = null;
	static public ResultSet rs = null;
	static private boolean connected = false;
	
	public static void connection() throws SQLException{
		
		System.out.println("getting connection ...");
		conn = DriverManager.getConnection("jdbc:ucanaccess://AccessDataBase/VpCISDataBase.accdb","","");
		System.out.println("connection successful");
		connected = true ;
		/*
		System.out.println("creating statement...");
		st = conn.createStatement();
		System.out.println("statement created");
		
		System.out.println("executing query...");
		rs = st.executeQuery("select * from Noms");
		System.out.println("query executed");
		
		System.out.println("showing results...");
		while (rs.next()){
			System.out.println(rs.getString(1)+" | "+rs.getString(2));
		}
		System.out.println("end of results");
		
		System.out.println("closing the connection...");
		conn.close();
		System.out.println("connection closed, Disconnected from database");
		*/
	}
	
	public static boolean authentificate(String userName, String password) throws SQLException{
		
		System.out.println("creating statement...");
		st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		System.out.println("statement created");
		
		System.out.println("executing query...");
		String request = String.format("SELECT * "
									+ "FROM Authentification "
									+ "WHERE userName = '%s' "
									+ "AND password = '%s'"
									,userName
									,password);
		System.out.println(request);
		rs = st.executeQuery(request);
		System.out.println("query executed");
		
		//if the result set returned null
		if (rs.next()){
			
			System.out.println("---finished");
			return true;
		}
		else //the result set has a result 
		{
			System.out.println("UserName / Password incorrect");
			return false;
		}
		
	}
	public static void testSelectDatabase() throws SQLException {
		
		System.out.println("creating statement...");
		st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		System.out.println("statement created");
		
		
		String request = String.format(	"SELECT * "
										+ "FROM numero "
										);
		System.out.println("the SQL request :"+request);
		System.out.println("**************************");
		
		rs = st.executeQuery(request);
		while (rs.next()){
			System.out.print(rs.getString("id"));
			System.out.print("\t");
			System.out.print(rs.getString("num"));
			System.out.print("\t");
			System.out.print(rs.getString("nom"));
			/*
			System.out.print("\t");
			System.out.print(rs.getString("destinationIdTeam"));
			*/
			System.out.println();
		}
	}
	
	/**
	 * this methode INSERT row using Mr SAADI methode
	 * or secondly with the methode used in the tutorial Java- MS Access
	 * 
	 */
	public static void testInsertDatabase() {
		try {
			System.out.println("creating statement...");
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			System.out.println("statement created");
			
			/*
			String request = String.format(	"INSERT INTO Actor (quality,nameActor,idTeam,idAbstractionLevel) VALUES ("
											//+ "'idTeam_0000',"
											+ "'nul',"
											+ "'NULLL',"
											+ "null,"
											+ "'1'"
											+ ")");
											*/
		
			String request = String.format(	"INSERT INTO numero (num,nom) VALUES (#,null)");
					//+ i + ")");
					//+ "'idTeam_0000',"
					
			System.out.println(request);
			st.executeUpdate(request);
			System.out.println("request : "+request+" has been executed succesfully");
			
			
			//the best
			/*
			String request = String.format(	"SELECT * "
											+ "FROM CIS "
											);
			rs = st.executeQuery(request);
			rs.moveToInsertRow();
			rs.updateString("idCIS", "idCIS_0005");
			rs.updateString("nameCIS", "       ");
			rs.updateString("description","");
			rs.insertRow();
			
			st.close();
			rs.close();
			
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery(request);
			*/
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * this methode DELETE row using Mr SAADI methode
	 * or secondly with the methode used in the tutorial Java- MS Access
	 */
	public static void testDeleteDatabase() {
		try{
			//the best
			System.out.println("creating statement...");
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			System.out.println("statement created");
			
			String request = String.format(	"DELETE FROM CIS WHERE "
					+ "idCIS='idCIS_0000'"
					);
			
			st.executeUpdate(request);
			System.out.println("request : "+request+" has been executed succesfully");
			
			/*
			String request = "SELECT * FROM CIS "
							+ "WHERE idCIS='idCIS_0003'";
		
			rs = st.executeQuery(request);
			
			if(rs.next()){
				//found that specific row
				rs.deleteRow();
				System.out.println("request "+request+" excuted succesfully");
				st.close();
				rs.close();
				
				st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				request = "SELECT * FROM CIS";
				rs = st.executeQuery(request);	
				
				
			}else{
				//couldn't find that row
				System.out.println("request : "+request+" failed to execute");
				
			}
			*/
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * this methode UPDATE row using Mr SAADI methode
	 * or secondly with the methode used in the tutorial Java- MS Access
	 */
	public static void testUpdateDatabase() {
		try{
			
			System.out.println("creating statement...");
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			System.out.println("statement created");
			
			String request =	 "UPDATE CIS "
								+ "SET nameCIS='999' "
								//+ "AND description='99999'"
								+ "WHERE idCIS=9";
			
			st.executeUpdate(request);
			System.out.println("request : "+request+" has been executed succesfully");
			
			//the bet
			/*
			String request = 	"SELECT * FROM CIS "
								+ "WHERE idCIS='idCIS_0000'";
			
			rs = st.executeQuery(request);
			
			if (rs.next()){
				//found that specific row
				rs.updateString("nameCIS", "LSI");
				rs.updateRow();
				System.out.println("request : "+request+" has been executed succesfully");
			}else{
				System.out.println("request : "+request+" failed to execute");
			}
			*/
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws SQLException{
		
		connection();
		//authentificate("s","s");
		//testSelectDatabase();
		testInsertDatabase();
		//testDeleteDatabase();
		//testUpdateDatabase();
	}
	
}
