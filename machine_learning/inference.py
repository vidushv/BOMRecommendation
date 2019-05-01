import sys
import math
import pandas as pd
import pickle
import numpy as np
from sklearn.ensemble import RandomForestClassifier
from sklearn.svm import SVC
from sklearn.naive_bayes import GaussianNB
from sklearn.naive_bayes import MultinomialNB
from sklearn import linear_model
from sklearn.datasets import make_classification
import classifier
from sklearn import preprocessing
import warnings
warnings.filterwarnings("ignore")

L2_REGULARIZATION_STRENGTH = 0.9

def Sort(sub_li):
    """
    Sort results in descending order (by second value in rows). The purpose of this function
    is to print the results from best to worst.

    Inputs:
    sub_li: List to be sorted by second feature.
    """
    sub_li.sort(key = lambda x: x[1], reverse=True)
    return sub_li

def predict_probabilities(data_objects, printable = True, n_estimators=100, max_depth=2,random_state=0, ml_algorithm='rf'):
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
    #np.random.shuffle(X)
    Xtrain = np.array(X[:,:-1])
    Ytrain = []
    for i in range(len(Xtrain)):
        Ytrain.append(X[i][-1])
    clf = RandomForestClassifier(n_estimators=100, max_depth=2,random_state=0)
    if ml_algorithm == 'rf':
        clf = RandomForestClassifier(n_estimators=100, max_depth=2,random_state=0)
    elif ml_algorithm == 'lr':
        clf = linear_model.LogisticRegression(C=L2_REGULARIZATION_STRENGTH, penalty='l2', n_jobs=4)
    elif ml_algorithm == 'gnb':
        clf = GaussianNB(priors=None, var_smoothing=1e-09)
    elif ml_algorithm == 'mnb':
        clf = MultinomialNB()
    elif ml_algorithm == 'svm':
        clf = SVC(kernel = 'linear', C = 1, probability = True)
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

    clf.fit(Xtrain[:-2].astype(np.float), Ytrain1[:-2])
    bom_encoding = classifier.feature_extractor(orig_bom, orig_bom)
    bom = np.array(bom_encoding)
    bom = bom.reshape(1,-1)
    prob = clf.predict_proba(bom)
    classes = clf.classes_

    if printable == True:
        result = []
        for i in range(len(prob[0])):
            temp = []
            temp.append(inverse_class[classes[i]])
            temp.append(prob[0][i])
            result.append(temp)

        result1 = Sort(result)
        for a in result1:
            print(a[0],' , ',"{0:0.3f}".format(a[1]))
    return prob


def main():
    """
    main function being called
    :return: print recommendations:
    """
    df = pd.read_csv(sys.argv[1])
    dataobjects = df.values.tolist()
    predict_probabilities(dataobjects, ml_algorithm=sys.argv[2])

if __name__ == "__main__":
    main()

