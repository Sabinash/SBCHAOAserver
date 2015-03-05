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
public class PaymentStatus {

    private String flatNbr;
    private String status;
    private String datetime;
    private String paymentStatus;
    private String modeOfPayment;

    public String getFlatNbr() {
        return flatNbr;
    }

    public void setFlatNbr(String flatNbr) {
        this.flatNbr = flatNbr;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

}
