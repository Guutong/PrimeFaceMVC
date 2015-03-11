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
public class DeleteService {
    
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(OrclConfig.getUrl(),
                OrclConfig.getUsername(),
                OrclConfig.getPassword()
        );
    }
    
    public void deleteRow(EmployeeModel employee) throws ClassNotFoundException, SQLException{
        
        System.out.println("Employee_id : "+employee.getId());
        
        Class.forName(OrclConfig.getDriver());
        
        Connection connection = null;
       
        
        try{
            
            connection = getConnection();

            String sql = "DELETE FROM Employees WHERE employee_id = ?";
                        
            
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, employee.getId());
            prep.executeUpdate();
            System.out.println("Record Delete Successfully");
            
        }finally{    
            if (connection != null) {
                connection.close();
            }
        }
    }
}
