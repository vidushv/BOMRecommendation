package com.cornell.se.bom.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class MiscIdentity implements Serializable {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	 public String STLKN;

	 @Column
	 public String STPOZ;
	 
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

		@JsonProperty("STPOZ")
		public String getSTPOZ() {
			return STPOZ;
		}

		public void setSTPOZ(String sTPOZ) {
			STPOZ = sTPOZ;
		}
		
		@Override
		public boolean equals(Object obj) {
			
			if (!(obj instanceof MiscIdentity))
				return false;
			
			MiscIdentity obj1 = (MiscIdentity)obj;
			
			return obj1.STLKN.equals(STLKN) && obj1.STLNR.equals(STLNR) && obj1.STPOZ.equals(STPOZ);
			
		}
		
		  @Override
		    public int hashCode() {
		        return Objects.hash(STLKN, STLNR, STPOZ);
		    }
		  
		  @Override
		  public String toString() {
			  return STLKN+"_"+STLNR+"_"+STPOZ;
		  }

}
