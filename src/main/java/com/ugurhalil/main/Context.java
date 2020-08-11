/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugurhalil.main;

import com.ugurhalil.database.connectors.ConnectionManagerStrategy;
import com.ugurhalil.models.DatabaseConnectionModel;

/**
 *
 * @author halilugur
 */
public class Context {

    private final ConnectionManagerStrategy cms;

    public Context(ConnectionManagerStrategy cms) {
        this.cms = cms;
    }
    
    public void executeStrategy(DatabaseConnectionModel loginModel){
        cms.doOperation(loginModel);
    }
}
