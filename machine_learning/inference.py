import sys
import math
import pandas as pd
import pickle
import numpy as np
from sklearn.ensemble import RandomForestClassifier
from sklearn.datasets import make_classification
import classifier

from sklearn import preprocessing


def predict_probabilities(data_objects, printable = True, n_estimators=100, max_depth=2,random_state=0):
    """
    This function is used to predict class probabilities and return the recommendations computed.
    :param data_objects: data objects to be trained, historical data
    :param printable: boolean check for printing values or not, default true
    :param n_estimators: ensemble estimates
    :param max_depth: max depth of random forest trees
    :param random_state: random state argument for random forests
    :return recommendation list as being printed on the console:
    """
    orig_bom = data_objects[0]
    feature_matrix = []
    for i in range(1, len(data_objects)):
        if data_objects[i][0] == orig_bom[0]:
                continue
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
    classes = {}
    inverse_class = {}
    cnt=0
    for i in range(len(Ytrain)):
        if Ytrain[i] not in classes:
            classes[Ytrain[i]]=cnt
            inverse_class[cnt]=Ytrain[i]
            cnt+=1
    Ytrain1 = []
    for i in range(len(Ytrain)):
        Ytrain1.append(classes[Ytrain[i]])

    clf.fit(Xtrain[:-2], Ytrain1[:-2])
    bom_encoding = classifier.feature_extractor(orig_bom, orig_bom)
    bom = np.array(bom_encoding)
    bom = bom.reshape(1,-1)
    prob = clf.predict_proba(bom)
    classes = clf.classes_
    if printable == True:
        for i in range(len(prob[0])):
            print(inverse_class[classes[i]],' , ',"{0:0.3f}".format(prob[0][i]))
    return prob


def main():
    """
    main function being called
    :return: print recommendations:
    """
    df = pd.read_csv('csvfile.csv')
    dataobjects = df.values.tolist()
    predict_probabilities(dataobjects)

if __name__ == "__main__":
    main()

