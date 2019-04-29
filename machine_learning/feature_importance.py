import sys
import pandas as pd
import numpy as np
from sklearn.ensemble import RandomForestClassifier
from sklearn.datasets import make_classification

from gensim.models.keyedvectors import KeyedVectors
model = KeyedVectors.load_word2vec_format("glove_model2.txt", binary=False, limit=50000)
import classifier

def compute_feature_importance(data_objects, printable = True, n_estimators=100, max_depth=2,random_state=0):
    """

    :param data_objects: data objects to be trained for extracting feature importance, historical data
    :param printable: boolean check for printing values or not, default true
    :param n_estimators: ensemble estimates
    :param max_depth: max depth of random forest trees
    :param random_state: random state argument for random forests
    :return:clf.feature_importances_: feature importance list of dataset
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
    clf.fit(Xtrain[:], Ytrain[:])
    if(printable == True):
        print('Material Group\t: '+"{0:0.3f}".format(clf.feature_importances_[1]))
        print('Plant Location\t: '+"{0:0.3f}".format(clf.feature_importances_[-1]))
        print('Storage Location: '+"{0:0.3f}".format(clf.feature_importances_[0]))
        print('MRP Group\t: '+"{0:0.3f}".format(clf.feature_importances_[4]))
        print('Volume\t\t: '+"{0:0.3f}".format(clf.feature_importances_[2]))
        print('Stock\t\t: '+"{0:0.3f}".format(clf.feature_importances_[6]))
        print('Availability\t: '+"{0:0.3f}".format(clf.feature_importances_[5]))
        print('Lot size\t:'+" {0:0.3f}".format(clf.feature_importances_[3]))
    return clf.feature_importances_

def main():
    """
    main function for the script
    :return feature importance to be printed:
    """
    df = pd.read_csv('csvfile.csv')
    dataobjects = df.values.tolist()
    compute_feature_importance(dataobjects)

if __name__ == "__main__":
    main()
