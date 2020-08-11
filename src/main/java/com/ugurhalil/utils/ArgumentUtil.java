/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugurhalil.utils;

import com.ugurhalil.enums.DatabaseJDBCPrefixEnums;
import com.ugurhalil.enums.DatabaseTypeEnums;
import com.ugurhalil.exceptions.ArgumentException;
import com.ugurhalil.models.DatabaseConnectionModel;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author halilugur
 */
public class ArgumentUtil {

    public static final String HYBRIS_PATH = "-hp";
    public static final String DATABASE_TYPE = "-t";
    public static final String URL = "-l";
    public static final String LONG_URL = "-url";
    public static final String USERNAME = "-u";
    public static final String PASSWORD = "-p";
    private static List<String> args;

    private ArgumentUtil() {
    }

    public static String findArgumentValue(String arg, boolean required) throws ArgumentException {
        if (!args.contains(arg) && required) {
            throw new ArgumentException("Plase fill " + arg + " argument!");
        } else {
            if (!args.contains(arg)) {
                return null;
            } else {
                return args.get(args.indexOf(arg) + 1);
            }
        }
    }

    public static DatabaseConnectionModel populateArgument(String[] args) {
        ArgumentUtil.args = Arrays.asList(args);
        DatabaseConnectionModel connectionModel = new DatabaseConnectionModel();
        try {
            String libPath = findArgumentValue(HYBRIS_PATH, true) + "/bin/platform/lib/dbdriver";
            ClasspathHacker.addToClasspath(libPath);

            connectionModel.setOnlyUrl(findArgumentValue(LONG_URL, false) != null);
            connectionModel.setLongUrl(findArgumentValue(LONG_URL, false));

            connectionModel.setUrl(findArgumentValue(URL, !connectionModel.isOnlyUrl()));
            connectionModel.setUsername(findArgumentValue(USERNAME, !connectionModel.isOnlyUrl()));
            connectionModel.setPassword(findArgumentValue(PASSWORD, false));

            addPrefixOnUrl(connectionModel);
        } catch (Exception ex) {
            Logger.getLogger(ArgumentUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return connectionModel;
    }

    public static void addPrefixOnUrl(DatabaseConnectionModel connectionModel) {
        if (getDatabaseType().contains(DatabaseTypeEnums.HSQLDB.getCode())) {
            connectionModel.setLongUrl(DatabaseJDBCPrefixEnums.HSQLDB.getCode() + connectionModel.getLongUrl());
            connectionModel.setUrl(DatabaseJDBCPrefixEnums.HSQLDB.getCode() + connectionModel.getUrl());
        }

    }

    public static String getDatabaseType() {
        try {
            return findArgumentValue(DATABASE_TYPE, true);
        } catch (ArgumentException ex) {
            Logger.getLogger(ArgumentUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static String findDriver() throws ArgumentException {
        if (getDatabaseType().contains(DatabaseTypeEnums.HSQLDB.getCode())) {
            return "org.hsqldb.jdbc.JDBCDriver";
        }
        throw new ArgumentException("Driver not found!");
    }
}
