package com.cornell.se.bom.recommendors;

public class RecommendationFactory {
	
	public static final String RANDOM_FOREST = "rf";
	public static final String LOGISTIC_REGRESSION = "lr";
	public static final String GAUSSIAN_NAIVE_BAYES = "gnb";
	public static final String MULTINOMIAL_NAIVE_BAYES = "mnb";
	public static final String SUPPORT_VECTOR_MACHINE = "svm";
	
	public Recommendor getRecommendor(String name) {
		
		if (name.equals("Random Forest"))
			return new RandomForestRecommendor();
		else if (name.equals("Logistic Regression"))
			return new LogisticRegressionRecommendor();
		else if (name.equals("Gaussian Naive Bayes"))
			return new GaussianNaiveBayesRecommendor();
		else if (name.equals("Multinomial Naive Bayes"))
			return new MultinomialNaiveBayesRecommendor();
		else 
			return new SupportVectorMachineRecommendor();

	}

}
