package com.cornell.se.bom.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "MAST")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class MAST implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	@EmbeddedId
	public MastIdentity identity;

	public MastIdentity getIdentity() {
		return identity;
	}

	public void setIdentity(MastIdentity identity) {
		this.identity = identity;
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

	@Column
	public Integer LOSVN;

	@Column
	public Integer LOSBS;

}