/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sbchaoa.model;

/**
 *
 * @author abinash_s
 */

public class Owner {
    
    private String type;
    private String name;
    private String pswd;
    private String flatNbr;
    private String isAdmin;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlatNbr() {
        return flatNbr;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public void setFlatNbr(String flatNbr) {
        this.flatNbr = flatNbr;
    }
    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    
    
    
    
    
}
