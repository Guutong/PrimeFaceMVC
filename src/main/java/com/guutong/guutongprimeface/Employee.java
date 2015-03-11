/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guutong.guutongprimeface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author pornmongkon
 */
public class Employee {
    public static ArrayList<EmployeeBeanView> getEmployee() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:ORCL", "hr",
					"1234");
            PreparedStatement ps = con.prepareStatement("select * from employees");
            ArrayList<EmployeeBeanView> al = new ArrayList<EmployeeBeanView>();
            ResultSet rs = ps.executeQuery();
            boolean found = false;
            while (rs.next()) {
                EmployeeBeanView e = new EmployeeBeanView();
                e.setId(rs.getInt("employee_id"));
                e.setFirstName(rs.getString("first_name"));
                e.setLastName(rs.getString("last_name"));
                e.setEmail(rs.getString("email"));
                e.setJobId(rs.getString("job_id"));
                e.setHireDate(rs.getDate("hire_date"));
                e.setPhoneNumber(rs.getString("phone_number"));
                e.setSalary(rs.getFloat("salary"));
                al.add(e);
                found = true;
            }
            rs.close();
            if (found) {
                return al;
            } else {
                return null; // no entires found
            }
        } catch (Exception e) {
            System.out.println("Error In getEmployee() -->" + e.getMessage());
            return (null);
        }
    }
}
