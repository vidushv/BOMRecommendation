import unittest
import cosine_similarity
import parseData


class TestStringMethods(unittest.TestCase): 
    def setUp(self): 
        pass
    # Returns true if the string is stripped and  
    # matches the given output. 
    def test_cos_sim(self):         
        df = parseData.readData('data.csv')
        dataobjects = df.values.tolist()
        self.assertTrue(cosine_similarity.cosine_function(dataobjects))

if __name__ == '__main__': 
    unittest.main() 
