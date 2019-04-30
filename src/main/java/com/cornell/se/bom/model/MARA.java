package com.cornell.se.bom.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "MARA")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class MARA implements Serializable{
	
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

	@JsonProperty("MEINS")
	public String getMEINS() {
		return MEINS;
	}

	public void setMEINS(String mEINS) {
		MEINS = mEINS;
	}

	@JsonProperty("MATKL")
	public String getMATKL() {
		return MATKL;
	}

	public void setMATKL(String mATKL) {
		MATKL = mATKL;
	}

	@JsonProperty("MTART")
	public String getMTART() {
		return MTART;
	}

	public void setMTART(String mTART) {
		MTART = mTART;
	}

	@JsonProperty("DATAB")
	public Date getDATAB() {
		return DATAB;
	}

	public void setDATAB(Date dATAB) {
		DATAB = dATAB;
	}

	@JsonProperty("Valid_To")
	public Date getValid_To() {
		return Valid_To;
	}

	public void setValid_To(Date valid_To) {
		Valid_To = valid_To;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column
	 public String MEINS;

	 @Column
	 public String MATKL;
	 
	 @Column
	 public String MTART;
	 
	 @Column(name = "DATAB", columnDefinition="DATETIME")
	 @Temporal(TemporalType.TIMESTAMP)
	 public Date DATAB;
	 
	 @Column(name = "Valid_To", columnDefinition="DATETIME")
	 @Temporal(TemporalType.TIMESTAMP)
	 public Date Valid_To;
	 
	 
	 
}