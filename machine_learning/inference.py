import sys
import math
import pandas as pd
import pickle
import numpy as np
from sklearn.ensemble import RandomForestClassifier
from sklearn.datasets import make_classification
import classifier
from gensim.models.keyedvectors import KeyedVectors
model = KeyedVectors.load_word2vec_format("glove_model2.txt", binary=False, limit=50000)

def predict_probabilities(data_objects):
    """
    This function is used to predict class probabilities and return the recommendations computed.
    :param data_objects:
    :return:
    """
    orig_bom = data_objects[0]
    feature_matrix = []
    for i in range(1, len(data_objects)):
        temp = classifier.feature_extractor(orig_bom, data_objects[i])
        temp.append(data_objects[i][0])
        feature_matrix.append(temp)
    X = np.array(feature_matrix)
    np.random.shuffle(X)
    Xtrain = np.array(X[:,:-1])
    Ytrain = []
    for i in range(len(Xtrain)):
        Ytrain.append(X[i][-1])
    clf = RandomForestClassifier(n_estimators=100, max_depth=2,random_state=0)
    clf.fit(Xtrain[:-2], Ytrain[:-2])
    bom_encoding = classifier.feature_extractor(orig_bom, orig_bom)
    bom = np.array(bom_encoding)
    bom = bom.reshape(1,-1)
    prob = clf.predict_proba(bom)
    #print(prob)
    print('Recommendations are:')
    for i in range(len(prob[0])):
        print('Class label ',i+1,': ',"{0:0.3f}".format(prob[0][i]))


df = pd.read_csv('csvfile.csv')
dataobjects = df.values.tolist()
predict_probabilities(dataobjects)

