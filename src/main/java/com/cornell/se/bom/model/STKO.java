package com.cornell.se.bom.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "STKO")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class STKO implements Serializable{
	
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
	@JsonProperty("STKOZ")
	public String getSTKOZ() {
		return STKOZ;
	}

	public void setSTKOZ(String sTKOZ) {
		STKOZ = sTKOZ;
	}
	@JsonProperty("DATAV")
	public Date getDATUV() {
		return DATUV;
	}

	public void setDATUV(Date dATUV) {
		DATUV = dATUV;
	}
	@JsonProperty("VALID_TO")
	public Date getVALID_TO() {
		return VALID_TO;
	}

	public void setVALID_TO(Date vALID_TO) {
		VALID_TO = vALID_TO;
	}
	
	@JsonProperty("BMEIN")
	public String getBMEIN() {
		return BMEIN;
	}

	public void setBMEIN(String bMEIN) {
		BMEIN = bMEIN;
	}
	
	@JsonProperty("BMENG")
	public Integer getBMENG() {
		return BMENG;
	}

	public void setBMENG(Integer bMENG) {
		BMENG = bMENG;
	}

	@JsonProperty("AENNR")
	public String getAENNR() {
		return AENNR;
	}

	public void setAENNR(String aENNR) {
		AENNR = aENNR;
	}

	@JsonProperty("STLAL")
	public String getSTLAL() {
		return STLAL;
	}

	public void setSTLAL(String sTLAL) {
		STLAL = sTLAL;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	 @Id
	 @Column
	 public String STKOZ;
	 

	 @Column(name = "DATAV", columnDefinition="DATETIME")
	 @Temporal(TemporalType.TIMESTAMP)
	 public Date DATUV;
	 
	 @Column(name = "VALID_TO", columnDefinition="DATETIME")
	 @Temporal(TemporalType.TIMESTAMP)
	 
	 public Date VALID_TO;
	 
	 
	 @Column
	 public String BMEIN;
	 
	 @Column
	 public Integer BMENG;
	 
	 @Column
	 public String AENNR;
	 
	 @Column
	 public String STLAL;
	 
}
