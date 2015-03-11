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

/**
 *
 * @author pornmongkon
 */
public class UpdateService {

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(OrclConfig.getUrl(),
                OrclConfig.getUsername(),
                OrclConfig.getPassword()
        );
    }

    public void updateRow(EmployeeModel employee) throws SQLException {

        employee.setEmail(employee.getEmail().toUpperCase());

        Connection connection = null;

        try {

            connection = getConnection();
            String sql = "UPDATE Employees SET first_name = ?,"
                    + "last_name =?,"
                    + "email = ?,"
                    + "phone_number = ?,"
                    + "salary = ?,"
                    + "commission_pct = ?,"
                    + "manager_id = ? "
                    + "WHERE employee_id = ?";

            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setString(1, employee.getFirstName());
            prep.setString(2, employee.getLastName());
            prep.setString(3, employee.getEmail());
            prep.setString(4, employee.getPhoneNumber());
            prep.setDouble(5, employee.getSalary());
            prep.setFloat(6, employee.getCommissionPct());
            prep.setInt(7, employee.getManagerId());
            prep.setInt(8, employee.getId());
            prep.executeUpdate();
            System.out.println("Record Update Successfully");

        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
