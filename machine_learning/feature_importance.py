import sys
import pandas as pd
import numpy as np
from sklearn.ensemble import RandomForestClassifier
from sklearn.datasets import make_classification

from gensim.models.keyedvectors import KeyedVectors
model = KeyedVectors.load_word2vec_format("glove_model2.txt", binary=False, limit=50000)
import classifier

def compute_feature_importance(data_objects):
    """
    This function is used to compute the importance of different features in the dataset
    using Random Forest Classifiers.
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
    clf.fit(Xtrain[:550], Ytrain[:550])
    print('Material Group\t: '+"{0:0.3f}".format(clf.feature_importances_[1]))
    print('Plant Location\t: '+"{0:0.3f}".format(clf.feature_importances_[-1]))
    print('Storage Location: '+"{0:0.3f}".format(clf.feature_importances_[0]))
    print('MRP Group\t: '+"{0:0.3f}".format(clf.feature_importances_[4]))
    print('Volume\t\t: '+"{0:0.3f}".format(clf.feature_importances_[2]))
    print('Stock\t\t: '+"{0:0.3f}".format(clf.feature_importances_[6]))
    print('Availability\t: '+"{0:0.3f}".format(clf.feature_importances_[5]))
    print('Lot size\t:'+" {0:0.3f}".format(clf.feature_importances_[3]))

df = pd.read_csv('csvfile.csv')
dataobjects = df.values.tolist()
compute_feature_importance(dataobjects)

