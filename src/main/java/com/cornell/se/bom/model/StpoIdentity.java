package com.cornell.se.bom.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class StpoIdentity implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Column
	public String STLNR;

	@Column
	public String STLKN;

	@Column
	public String STPOZ;

	@JsonProperty("STLNR")
	public String getSTLNR() {
		return STLNR;
	}

	public void setSTLNR(String sTLNR) {
		STLNR = sTLNR;
	}

	@JsonProperty("STLKN")
	public String getSTLKN() {
		return STLKN;
	}

	public void setSTLKN(String sTLKN) {
		STLKN = sTLKN;
	}

	@JsonProperty("STPOS")
	public String getSTPOZ() {
		return STPOZ;
	}

	public void setSTPOS(String sTPOZ) {
		STPOZ = sTPOZ;
	}

}
