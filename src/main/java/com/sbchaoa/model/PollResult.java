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
public class PollResult {

    private String flatNbr;
    private String candidateName;
    private String candidateFlatNbr;
    private String catagoryName;
    private String dateTime;

    public String getFlatNbr() {
        return flatNbr;
    }

    public void setFlatNbr(String flatNbr) {
        this.flatNbr = flatNbr;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
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

    public String getCandidateFlatNbr() {
        return candidateFlatNbr;
    }

    public void setCandidateFlatNbr(String candidateFlatNbr) {
        this.candidateFlatNbr = candidateFlatNbr;
    }

}
