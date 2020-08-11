/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugurhalil.database.connectors.abs;

import com.ugurhalil.database.connectors.ConnectionManagerStrategy;
import com.ugurhalil.exceptions.ConnectionManagerException;
import com.ugurhalil.models.DatabaseConnectionModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author halilugur
 */
public abstract class AbstractConnectionManager implements ConnectionManagerStrategy {

    private Connection connection;
    private Statement statement;
    private String databaseDriver;

    public void chooseConnection(DatabaseConnectionModel loginModel) {
        try {
            Class.forName(getDatabaseDriver());
            if (loginModel.isOnlyUrl()) {
                connectionSetup(loginModel.getLongUrl());
            } else {
                connectionSetup(loginModel.getUrl(), loginModel.getUsername(), loginModel.getPassword());
            }
            if (Objects.isNull(connection)) {
                throw new ConnectionManagerException();
            }
            statement = getConnection().createStatement();
        } catch (SQLException | ConnectionManagerException | ClassNotFoundException ex) {
            Logger.getLogger(AbstractConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateLicence() {
        try {
            int row = getStatement().executeUpdate(query());
            System.out.println("Total updated row => " + row);
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void connectionSetup(String url, String username, String password) {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            System.out.println("Failed to create the database connection.");
        }
    }

    public void connectionSetup(String url) {
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            System.out.println("Failed to create the database connection.");
        }
    }

    public String query() {
        return "UPDATE users SET createdTS = NOW() WHERE pk > 1";
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public String getDatabaseDriver() {
        return databaseDriver;
    }

    public void setDatabaseDriver(String databaseDriver) {
        this.databaseDriver = databaseDriver;
    }
}
