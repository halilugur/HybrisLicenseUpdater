/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugurhalil.database.connectors.impl;

import com.ugurhalil.database.connectors.ConnectionManagerStrategy;
import com.ugurhalil.database.connectors.abs.AbstractConnectionManager;
import com.ugurhalil.enums.DatabaseDriverEnums;
import com.ugurhalil.models.DatabaseConnectionModel;

/**
 * @author halilugur
 */
public class HsqlConnectionManager extends AbstractConnectionManager implements ConnectionManagerStrategy {

    @Override
    public void doOperation(DatabaseConnectionModel databaseInformation) {
        setDatabaseDriver(DatabaseDriverEnums.HSQLDB.getCode());
        chooseConnection(databaseInformation);
        updateLicence();
    }
}
