/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugurhalil.main;

import com.ugurhalil.database.connectors.HsqlConnectionManager;
import com.ugurhalil.database.connectors.MysqlConnectionManager;
import com.ugurhalil.models.DBLoginModel;
import com.ugurhalil.utils.ArgumentsUtil;
import java.lang.module.FindException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author halilugur
 */
public class HybrisLicenseUpdater {

    public final static String TYPE = "-t";
    public final static String URL = "-l";
    public final static String IP = "-i";
    public final static String PORT = "-o";
    public final static String DATABASE = "-d";
    public final static String USERNAME = "-u";
    public final static String PASSWORD = "-p";

    public static void main(String[] args) {
        if (args.length != 0) {
            List<String> arguments = Arrays.asList(args);
            /*
            choose database type (mysql, hsqldb, sql, postgresql, mongo) -t
            enter an database url (jdbc:mysql//localhost:3306/) -l
                if you don't use url plase enter ip and port
                ip (127.0.0.1) -i
                port (3306) -o
            database name (test) -d
            username (root) -u
            password (1234) -p
            
            if you use full url (jdbc:hsqldb:file:/home/halilugur/turkcell-epos/data/hsqldb/mydb) -url
            
             */
            DBLoginModel loginModel = fillLoginModel(arguments);
            Context context = createContext(loginModel.getType());
            updateHybrisLicence(context, loginModel);
        } else {
            System.out.println("Plase use arguments");
        }
    }

    private static void updateHybrisLicence(Context context, DBLoginModel loginModel) {
        Connection connection = context.executeStrategy(loginModel);
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate("UPDATE users SET createdTS = NOW() WHERE pk > 1");
            System.out.println("Total updated row => " + result);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(HybrisLicenseUpdater.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
    }

    private static DBLoginModel fillLoginModel(List<String> arguments) {
        DBLoginModel loginModel = new DBLoginModel();

        ArgumentsUtil.args = arguments;

        if (ArgumentsUtil.hasArgument(TYPE)) {
            loginModel.setType(ArgumentsUtil.findArgumentValue(TYPE, true));
            String dirverName = findJdbcDriver(loginModel.getType());
            loginModel.setDriver(dirverName);
        }
        if (ArgumentsUtil.hasArgument(URL)) {
            loginModel.setUrl(ArgumentsUtil.findArgumentValue(URL, true));
        }
        if (ArgumentsUtil.hasArgument(IP)) {
            loginModel.setIp(ArgumentsUtil.findArgumentValue(IP, false));
        }
        if (ArgumentsUtil.hasArgument(PORT)) {
            loginModel.setPort(ArgumentsUtil.findArgumentValue(PORT, false));
        }
        if (ArgumentsUtil.hasArgument(DATABASE)) {
            loginModel.setDatabase(ArgumentsUtil.findArgumentValue(DATABASE, true));
        }
        if (ArgumentsUtil.hasArgument(USERNAME)) {
            loginModel.setUsername(ArgumentsUtil.findArgumentValue(USERNAME, true));
        }
        if (ArgumentsUtil.hasArgument(PASSWORD)) {
            loginModel.setPassword(ArgumentsUtil.findArgumentValue(PASSWORD, true));
        }

        return loginModel;
    }

    private static String findJdbcDriver(String driver) {
        if (driver.contains("mysql")) {
            return "com.mysql.jdbc.Driver";
        } else if (driver.contains("hsqldb")) {
            return "org.hsqldb.jdbc.JDBCDriver";
        }
        throw new FindException("Database driver not found!");
    }

    private static Context createContext(String driver) {
        if (driver.equalsIgnoreCase("mysql")) {
            return new Context(new MysqlConnectionManager());
        } else if (driver.equalsIgnoreCase("hsqldb")) {
            return new Context(new HsqlConnectionManager());
        }
        throw new FindException("Connection manager not found!");
    }

}
