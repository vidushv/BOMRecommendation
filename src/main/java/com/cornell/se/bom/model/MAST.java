package com.cornell.se.bom.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "MAST")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class MAST implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@Column
	 public String MATNR;

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
	@JsonProperty("LOSVN")
	public Integer getLOSVN() {
		return LOSVN;
	}

	public void setLOSVN(Integer lOSVN) {
		LOSVN = lOSVN;
	}
	@JsonProperty("LOSBS")
	public Integer getLOSBS() {
		return LOSBS;
	}

	public void setLOSBS(Integer lOSBS) {
		LOSBS = lOSBS;
	}
	@Id
	@Column
	 public String STLNR;
	 @Id
	 @Column
	 public String STLAL;
	 @Id
	 @Column
	 public String STLAN;
	 @Id
	 @Column
	 public String WERKS;
	 
	 @Column
	 public Integer LOSVN;
	 
	 @Column
	 public Integer LOSBS;
	 
	 
	 
	 
	 
}