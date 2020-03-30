/**
 * 
 */

package com.assosoft.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnexion {

		public Dbconnexion()
		{
			
		}
		
		public static Connection getConnectionDb()
		{
        			String url ="jdbc:mysql://localhost/assosoft?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        //			String url ="jdbc:mysql://10.119.21.5/assosoft";
//      String url ="jdbc:mysql://10.119.21.78:3306/assosoft";
			String login="root";
			String pass="";
			Connection cn =null;
			
			try {
				// chargement du driver
					Class.forName("com.mysql.cj.jdbc.Driver");
					// creation de ma connexion � la base
					cn= DriverManager.getConnection(url,login,pass);
					
				}catch(SQLException e)
				{
					e.printStackTrace();
				}catch(ClassNotFoundException e)
				{
					e.printStackTrace();
				}
			return cn;
		}
	}

