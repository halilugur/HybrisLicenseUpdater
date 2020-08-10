/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugurhalil.database.connectors;

import com.ugurhalil.models.DBLoginModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author halilugur
 */
public class MysqlConnectionManager implements ConnectionManagerStrategy {

    private Connection connection;
    private final String URL_PREFIX = "jdbc:mysql:";

    @Override
    public Connection doConnection(DBLoginModel loginModel) {

        if (!loginModel.getUrl().contains(URL_PREFIX)) {
            loginModel.setUrl(URL_PREFIX + loginModel.getUrl());
        }
        try {
            Class.forName(loginModel.getDriver());
            try {
                connection = DriverManager.getConnection(loginModel.getUrl(), loginModel.getUsername(), loginModel.getPassword());
            } catch (SQLException ex) {
                System.out.println("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found.");
        }
        return connection;
    }
}
