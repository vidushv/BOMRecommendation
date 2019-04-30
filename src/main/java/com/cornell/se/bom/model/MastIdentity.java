package com.cornell.se.bom.model;

import java.io.Serializable;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MastIdentity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	public String MATNR;
	
	@Column
	public String STLNR;
	
	@Column
	public String STLAL;
	
	@Column
	public String STLAN;
	
	@Column
	public String WERKS;

	@JsonProperty("MATNR")
	public String getMATNR() {
		return MATNR;
	}

	public void setMATNR(String mATNR) {
		MATNR = mATNR;
	}

	@JsonProperty("STLNR")
	public String getSTLNR() {
		return STLNR;
	}

	public void setSTLNR(String sTLNR) {
		STLNR = sTLNR;
	}

	@JsonProperty("STLAL")
	public String getSTLAL() {
		return STLAL;
	}

	public void setSTLAL(String sTLAL) {
		STLAL = sTLAL;
	}

	@JsonProperty("STLAN")
	public String getSTLAN() {
		return STLAN;
	}

	public void setSTLAN(String sTLAN) {
		STLAN = sTLAN;
	}

	@JsonProperty("WERKS")
	public String getWERKS() {
		return WERKS;
	}

	public void setWERKS(String wERKS) {
		WERKS = wERKS;
	}
	
	
	@Override
	public String toString() {
		return MATNR+"_"+STLNR+"_"+STLAL+"_"+STLAN+"_"+WERKS;
	}
	
	

}
