package com.example.amq.myactivemq.model;

import java.io.Serializable;

public class MyMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String action;

	public MyMessage() {
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public MyMessage(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "MyMessage [action=" + action + "]";
	}

}
