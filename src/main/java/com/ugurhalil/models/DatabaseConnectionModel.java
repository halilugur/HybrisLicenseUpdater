/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugurhalil.models;

/**
 *
 * @author halilugur
 */
public class DatabaseConnectionModel {

    private String path;
    private String url;
    private String username;
    private String password;
    private String longUrl;
    private boolean onlyUrl;
    

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public boolean isOnlyUrl() {
        return onlyUrl;
    }

    public void setOnlyUrl(boolean onlyUrl) {
        this.onlyUrl = onlyUrl;
    }
    
    

    @Override
    public String toString() {
        return this.path + "\n"
                + this.url + "\n"
                + this.username + "\n"
                + this.password + "\n";
    }

}
