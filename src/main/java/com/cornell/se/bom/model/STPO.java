package com.cornell.se.bom.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "STPO")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class STPO implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	 @Column
	 public String STLNR;
	
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
	public String getSTPOS() {
		return STPOS;
	}

	public void setSTPOS(String sTPOS) {
		STPOS = sTPOS;
	}

	@JsonProperty("DATUV")
	public Date getDATUV() {
		return DATUV;
	}

	public void setDATUV(Date dATUV) {
		DATUV = dATUV;
	}

	@JsonProperty("IDNRK")
	public String getIDNRK() {
		return IDNRK;
	}

	public void setIDNRK(String iDNRK) {
		IDNRK = iDNRK;
	}

	@JsonProperty("MEINS")
	public String getMEINS() {
		return MEINS;
	}

	public void setMEINS(String mEINS) {
		MEINS = mEINS;
	}

	@JsonProperty("MENGE")
	public Integer getMENGE() {
		return MENGE;
	}

	public void setMENGE(Integer mENGE) {
		MENGE = mENGE;
	}

	@JsonProperty("EKGRP")
	public String getEKGRP() {
		return EKGRP;
	}

	public void setEKGRP(String eKGRP) {
		EKGRP = eKGRP;
	}

	@JsonProperty("MATKL")
	public String getMATKL() {
		return MATKL;
	}

	public void setMATKL(String mATKL) {
		MATKL = mATKL;
	}

	@Id
	 @Column
	 public String STLKN;
	
	@Id
	 @Column
	 public String STPOS;
	 

	 @Column(name = "DATAV", columnDefinition="DATETIME")
	 @Temporal(TemporalType.TIMESTAMP)
	 public Date DATUV;
	 
	 @Column
	 public String IDNRK;
	 
	 
	 @Column
	 public String MEINS;
	 
	 @Column
	 public Integer MENGE;
	 
	 @Column
	 public String EKGRP;
	 
	 @Column
	 public String MATKL;
	 
}
	 
	 
