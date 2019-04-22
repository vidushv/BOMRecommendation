package com.cornell.se.bom.forms;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.cornell.se.bom.model.STPO;

public class BOMSearchForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 2, max = 300)
	private String selectedIDNRK;

	private String selectedIdentity;
	List<STPO> result;

	public List<STPO> getResult() {
		return result;
	}

	public void setResult(List<STPO> result) {
		this.result = result;
	}

	private boolean loadedByPost;

	public boolean isLoadedByPost() {
		return loadedByPost;
	}

	public void setLoadedByPost(boolean loadedByPost) {
		this.loadedByPost = loadedByPost;
	}

	public String getSelectedIdentity() {
		return selectedIdentity;
	}

	public void setSelectedIdentity(String selectedIdentity) {
		this.selectedIdentity = selectedIdentity;
	}

	public String getSelectedIDNRK() {
		return selectedIDNRK;
	}

	public void setSelectedIDNRK(String selectedIDNRK) {
		this.selectedIDNRK = selectedIDNRK;
	}

	public List<String> getRecommendedIDNRK() {
		return recommendedIDNRK;
	}

	public void setRecommendedIDNRK(List<String> recommendedIDNRK) {
		this.recommendedIDNRK = recommendedIDNRK;
	}

	List<String> recommendedIDNRK;

	@Override
	public String toString() {
		return "BOMSearchForm [selectedIDNRK=" + selectedIDNRK + ", recommendedIDNRK=" + recommendedIDNRK + "]";
	}
}
