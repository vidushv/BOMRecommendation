import unittest
import cosine_similarity
import parseData


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
    
if __name__ == '__main__': 
    unittest.main() 
