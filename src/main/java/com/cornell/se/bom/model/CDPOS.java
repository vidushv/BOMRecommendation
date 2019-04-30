package com.cornell.se.bom.model;

import java.io.Serializable;
import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "CDPOS")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class CDPOS implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	@JsonProperty("OLD_MATNR")
	public String getOLD_MATNR() {
		return OLD_MATNR;
	}

	public void setOLD_MATNR(String oLD_MATNR) {
		OLD_MATNR = oLD_MATNR;
	}

	@JsonProperty("OLD_UOM")
	public String getOLD_UOM() {
		return OLD_UOM;
	}

	public void setOLD_UOM(String oLD_UOM) {
		OLD_UOM = oLD_UOM;
	}

	@JsonProperty("VALUE_OLD")
	public String getVALUE_OLD() {
		return VALUE_OLD;
	}

	public void setVALUE_OLD(String vALUE_OLD) {
		VALUE_OLD = vALUE_OLD;
	}

	@JsonProperty("NEW_MATNR")
	public String getNEW_MATNR() {
		return NEW_MATNR;
	}

	public void setNEW_MATNR(String nEW_MATNR) {
		NEW_MATNR = nEW_MATNR;
	}

	@JsonProperty("NEW_UOM")
	public String getNEW_UOM() {
		return NEW_UOM;
	}

	public void setNEW_UOM(String nEW_UOM) {
		NEW_UOM = nEW_UOM;
	}

	@JsonProperty("VALUE_NEW")
	public String getVALUE_NEW() {
		return VALUE_NEW;
	}

	public void setVALUE_NEW(String vALUE_NEW) {
		VALUE_NEW = vALUE_NEW;
	}

	@JsonProperty("AENNR")
	public String getAENNR() {
		return AENNR;
	}

	public void setAENNR(String aENNR) {
		AENNR = aENNR;
	}

//	@Id
//	 @GeneratedValue(strategy = GenerationType.IDENTITY)
//	 private Long id;
	 
	 @Id
	 @Column
	 public String OLD_MATNR;
	 
	 @Column
	 public String OLD_UOM;

	 @Column
	 public String VALUE_OLD;
	 
	 @Column
	 public String NEW_MATNR;
	 
	 @Column
	 public String NEW_UOM;
	 
	 @Column
	 public String VALUE_NEW;
	 
	 @Column
	 public String AENNR;
	 
}
