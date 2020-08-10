/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugurhalil.database.connectors;

import com.ugurhalil.models.DBLoginModel;
import java.sql.Connection;

/**
 *
 * @author halilugur
 */
public class OracleConnectionManager implements ConnectionManagerStrategy {

    @Override
    public Connection doConnection(DBLoginModel loginModel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
