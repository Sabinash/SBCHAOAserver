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
public class NominateMyself {
    
    private String name;
    private String flatNbr;
    private String catagoryName;
    private String dateTime;
   transient private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlatNbr() {
        return flatNbr;
    }

    public void setFlatNbr(String flatNbr) {
        this.flatNbr = flatNbr;
    }

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
