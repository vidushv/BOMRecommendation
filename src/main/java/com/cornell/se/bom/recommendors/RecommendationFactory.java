package com.cornell.se.bom.recommendors;

public class RecommendationFactory {
	
	public Recommendor getRecommendor(String name) {
		
		if (name.equals("Random Forest"))
			return new RandomForestRecommendor();
		
		return null;
	}

}
