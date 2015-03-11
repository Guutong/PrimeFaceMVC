/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guutong.guutongprimeface.service;

import com.guutong.guutongprimeface.connectdb.OrclConfig;
import com.guutong.guutongprimeface.model.EmployeeModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author pornmongkon
 */
public class InsertService {

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(OrclConfig.getUrl(),
                OrclConfig.getUsername(),
                OrclConfig.getPassword()
        );
    }

    public void insertRow(EmployeeModel employee) throws ClassNotFoundException, SQLException {
        
        employee.setEmail(employee.getEmail().toUpperCase());
        
        Class.forName(OrclConfig.getDriver());
        
        Connection connection = null;

        try {

            connection = getConnection();

            String sql = "INSERT INTO Employees"
                    + "(employee_id,first_name,last_name,email,phone_number,"
                    + "hire_date,job_id,salary,manager_id,department_id)"
                    + "VALUES(?,?,?,?,?,SYSDATE,?,?,?,?)";

            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, employee.getId());
            prep.setString(2, employee.getFirstName());
            prep.setString(3, employee.getLastName());
            prep.setString(4, employee.getEmail());
            prep.setString(5, employee.getPhoneNumber());
            prep.setString(6, employee.getJobId());
            prep.setDouble(7, employee.getSalary());
            prep.setInt(8, employee.getManagerId());
            prep.setInt(9, employee.getDepartmentId());
            prep.executeUpdate();

            System.out.println("Record Inserted Successfully");

        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }
}
