package com.napkinstudio.sapcommunicationmodels;

public class DataTransferFromSAP {
	private String field;
	private String ff;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return "DataTransferStore [field=" + field + "]";
	}
}
