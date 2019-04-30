import unittest
import cosine_similarity
import parseData
import feature_importance
import pandas as pd
import classifier
import inference

class TestStringMethods(unittest.TestCase):
    def setUp(self):
        pass

    def test_cos_sim(self):
        """
        Test cosine similarity function by ensuring it returns a value as expected.
        """
        df = parseData.readData('data.csv')
        dataobjects = df.values.tolist()
        self.assertTrue(cosine_similarity.cosine_function(dataobjects))

    def test_feature_encoder(self):
        """
        Test feature encoding by ensuring the lengths of both the encoded and original bom are the same.
        """
        df = parseData.readData('data.csv')
        dataobjects = df.values.tolist()
        orig_bom = dataobjects[0]
        orig_feat = cosine_similarity.feature_encoder(orig_bom)
        self.assertEqual(len(orig_bom), len(orig_feat))

    def test_java_string_hashcode(self):
        """
        Test the java string hashcode function by ensuring it returns a value in the expected format.
        """
        test_string = 'a0b1Z4'
        res = cosine_similarity.java_string_hashcode(test_string)
        self.assertTrue(type(res) is int)

    def test_compute_feature_importance(self):
        """
        Test the compute_feature_importance() function to ensure each feature
        importance is less than 1 and the total feature importances add to 1.
        """
        df = pd.read_csv('csvfile.csv')
        dataobjects = df.values.tolist()
        clf = feature_importance.compute_feature_importance(dataobjects, False)
        runningTotal = 0
        for eachImportance in clf:
            runningTotal += eachImportance
            self.assertTrue (eachImportance <= 1.0)
        self.assertTrue(runningTotal, 1.0)

    def test_feature_extractor(self):
        """
        Test that the feature extractor outputs a value for every one of the nine
        expected values.
        """
        df1 = pd.read_csv('evaluate_data.csv')
        df = pd.read_csv('csvfile.csv')
        orig_objects = df.values.tolist()
        data_objects = df1.values.tolist()
        features = classifier.feature_extractor(orig_objects[0], data_objects[0])
        self.assertEqual(len(features), 9)
        for feature in features:
            self.assertFalse(feature is None)

    def test_classify(self):
        """
        Verify the results returned from the classifier contain all 5 scores.
        """
        df1 = pd.read_csv('evaluate_data.csv')
        df = pd.read_csv('csvfile.csv')
        dataobjects = df.values.tolist()
        bomobjects = df1.values.tolist()
        result = classifier.classify(dataobjects, bomobjects, False)
        self.assertEqual(len(result), 5)

    def test_predict_probabilities(self):
        """
        Verify if probabilities are being predicted correctly.
        """
        df = pd.read_csv('csvfile.csv')
        dataobjects = df.values.tolist()
        result = inference.predict_probabilities(dataobjects, False)
        self.assertFalse(result is None)

if __name__ == '__main__':
    unittest.main()
