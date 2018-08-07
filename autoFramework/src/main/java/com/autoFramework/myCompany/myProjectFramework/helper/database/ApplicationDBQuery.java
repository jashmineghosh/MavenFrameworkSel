package com.autoFramework.myCompany.myProjectFramework.helper.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationDBQuery {

	public int getEmpAge(int empId) throws NumberFormatException, SQLException {
		int age = 0;
		String dbQuery = "SELECT age FROM Employeeinfo where id=" + empId;
		ResultSet result = DataBaseHelper.getResultSet(dbQuery);
		while (result.next()) {
			age = Integer.parseInt(result.getString("age"));
		}
		return age;
	}

public static void main(String[] args) throws NumberFormatException, SQLException {
	ApplicationDBQuery ap = new ApplicationDBQuery();
//	ap.getEmpAge(21);
	System.out.println(ap.getEmpAge(1));
}
}
