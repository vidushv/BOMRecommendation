package com.cornell.se.bom.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "MISCELLANEOUS")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class MISCELLANEOUS implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	
	
	 @Column
	 public String STORAGE_LOCATION;
	
	 @JsonProperty("STORAGE_LOCATION")
	 public String getSTORAGE_LOCATION() {
		return STORAGE_LOCATION;
	}

	public void setSTORAGE_LOCATION(String sTORAGE_LOCATION) {
		STORAGE_LOCATION = sTORAGE_LOCATION;
	}

	@JsonProperty("MATERIAL_GROUP")
	public String getMATERIAL_GROUP() {
		return MATERIAL_GROUP;
	}

	public void setMATERIAL_GROUP(String mATERIAL_GROUP) {
		MATERIAL_GROUP = mATERIAL_GROUP;
	}

	@JsonProperty("PURCHASING_GROUP")
	public String getPURCHASING_GROUP() {
		return PURCHASING_GROUP;
	}

	public void setPURCHASING_GROUP(String pURCHASING_GROUP) {
		PURCHASING_GROUP = pURCHASING_GROUP;
	}

	@JsonProperty("STRATEGY")
	public String getSTRATEGY() {
		return STRATEGY;
	}

	public void setSTRATEGY(String sTRATEGY) {
		STRATEGY = sTRATEGY;
	}

	@JsonProperty("STOCK")
	public Integer getSTOCK() {
		return STOCK;
	}

	public void setSTOCK(Integer sTOCK) {
		STOCK = sTOCK;
	}

	@JsonProperty("MRP_GROUP")
	public String getMRP_GROUP() {
		return MRP_GROUP;
	}

	public void setMRP_GROUP(String mRP_GROUP) {
		MRP_GROUP = mRP_GROUP;
	}

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column
	 public String MATERIAL_GROUP;
	
	
	 @Column
	 public String PURCHASING_GROUP;
	 
	 @Column
	 public String STRATEGY;
	 
	 @Column
	 public Integer STOCK;
	 
	 @Column
	 public String MRP_GROUP;
	 
	 @Id
	 @Column
	 public String STLNR;
	 
	 @Id
	 @Column
	 public String STLKN;
	 
	 @Id
	 @Column
	 public String STPOS;

}