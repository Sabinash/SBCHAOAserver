/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sbchaoa.exception;

/**
 *
 * @author abinash_s
 */

public class ServiceException extends Exception {

	private static final long serialVersionUID = 360303044166816987L;

	private String errorCode;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace,
			String errorCode) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.errorCode = errorCode;
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ServiceException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public ServiceException(Throwable cause, String errorCode) {
		super(cause);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

}