/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugurhalil.main;

import com.ugurhalil.database.connectors.impl.HsqlConnectionManager;
import com.ugurhalil.database.connectors.impl.MysqlConnectionManager;
import com.ugurhalil.database.connectors.impl.OracleConnectionManager;
import com.ugurhalil.enums.DatabaseTypeEnums;
import com.ugurhalil.exceptions.ConnectionManagerException;
import com.ugurhalil.models.DatabaseConnectionModel;
import com.ugurhalil.utils.ArgumentUtil;

import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author halilugur
 */
public class HybrisLicenseUpdater {

    public static void main(String[] args) {
        if (args.length != 0) {
            /*
            required -hp arguments, the argument is a hybris 
            choose database type (mysql, hsqldb, sql, postgresql, mongo) -t
            enter an database url (jdbc:mysql//localhost:3306/) -l
            username (root) -u
            password (1234) -p
            
            if you use full url (jdbc:hsqldb:file:/home/halilugur/turkcell-epos/data/hsqldb/mydb) -url
            
             */
            DatabaseConnectionModel loginModel = ArgumentUtil.populateArgument(args);
            Context context;
            try {
                context = createContext(ArgumentUtil.getDatabaseType());
                context.executeStrategy(loginModel);
            } catch (ConnectionManagerException ex) {
                Logger.getLogger(HybrisLicenseUpdater.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Plase use arguments");
        }
    }

    private static Context createContext(String driver) throws ConnectionManagerException {
        if (DatabaseTypeEnums.MYSQL.getCode().equalsIgnoreCase(driver)) {
            return new Context(new MysqlConnectionManager());
        } else if (DatabaseTypeEnums.HSQLDB.getCode().equalsIgnoreCase(driver)) {
            return new Context(new HsqlConnectionManager());
        } else if (DatabaseTypeEnums.ORACLE.getCode().equalsIgnoreCase(driver)) {
            return new Context(new OracleConnectionManager());
        }
        throw new ConnectionManagerException();
    }

}
