/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugurhalil.database.connectors;

import com.ugurhalil.models.DatabaseConnectionModel;

/**
 *
 * @author halilugur
 */
public interface ConnectionManagerStrategy {
    void doOperation(DatabaseConnectionModel loginModel);
}
