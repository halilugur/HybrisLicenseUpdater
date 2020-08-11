/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugurhalil.enums;

/**
 *
 * @author halilugur
 */
public enum DatabaseDriverEnums {
    HSQLDB("org.hsqldb.jdbc.JDBCDriver"),
    MYSQL("com.mysql.jdbc.Driver"),
    ORACLE("oracle.jdbc.driver.OracleDriver");

    private final String code;

    private DatabaseDriverEnums(final String code) {
        this.code = code.intern();
    }

    public String getCode() {
        return this.code;
    }
}
