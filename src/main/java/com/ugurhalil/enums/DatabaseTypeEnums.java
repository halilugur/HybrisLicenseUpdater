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
public enum DatabaseTypeEnums {
    HSQLDB("hsqldb"),
    MYSQL("mysql"),
    ORACLE("oracle");

    private final String code;

    private DatabaseTypeEnums(final String code) {
        this.code = code.intern();
    }

    public String getCode() {
        return this.code;
    }
}
