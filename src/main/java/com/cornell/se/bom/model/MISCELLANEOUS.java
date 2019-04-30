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
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class MISCELLANEOUS implements Serializable {

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

	@JsonProperty("AVAILABILITY")
	public String getAVAILABILITY() {
		return AVAILABILITY;
	}

	public void setAVAILABILITY(String aVAILABILITY) {
		AVAILABILITY = aVAILABILITY;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@EmbeddedId
	public MiscIdentity identity;

	public MiscIdentity getIdentity() {
		return identity;
	}

	public void setIdentity(MiscIdentity identity) {
		this.identity = identity;
	}

	@Column
	public String MATERIAL_GROUP;

	@Column
	public String PURCHASING_GROUP;

	@Column
	public String AVAILABILITY;

	@Column
	public Integer STOCK;

	@Column
	public String MRP_GROUP;

	@Column
	public String WERKS;
	
	@Column
	public String VOLUME;

	@JsonProperty("STORAGE_LOCATION")
	public String getVOLUME() {
		return VOLUME;
	}

	public void setVOLUME(String vOLUME) {
		VOLUME = vOLUME;
	}

	@JsonProperty("WERKS")
	public String getWERKS() {
		return WERKS;
	}

	public void setWERKS(String wERKS) {
		WERKS = wERKS;
	}

}