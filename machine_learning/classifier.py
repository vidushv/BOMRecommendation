import sys
import pandas as pd
import numpy as np
from sklearn.metrics import precision_recall_fscore_support
from sklearn.metrics import accuracy_score
from sklearn.ensemble import RandomForestClassifier
from sklearn.datasets import make_classification

from gensim.models.keyedvectors import KeyedVectors
model = KeyedVectors.load_word2vec_format("/home/mukul/git/BOMRecommendation/machine_learning/glove_model2.txt", binary=False, limit=50000)


def feature_extractor(orig_object, data_object):
    """
    This function is used to extract features in the data set and return a list of all the features computed.
    :param bom_object original object to be sent
    :param data_object: historical data sent
    :return feat(list):
    """
    feat = []

    plant_similarity_score = model.similarity(str(orig_object[1]),str(data_object[1]))
    feat.append(plant_similarity_score)

    storage_similarity_score = model.similarity(str(orig_object[2]),str(data_object[2]))
    feat.append(plant_similarity_score)

    material_group_score = int(str(orig_object[3]))- int(str(data_object[3]))
    score = 0
    if material_group_score==0:
        score = 1
    else:
        score = 0
    material_group_score = score
    feat.append(material_group_score)

    volume_score = int(str(orig_object[4]))- int(str(data_object[4]))
    if volume_score <= 0:
        score = 1
    else:
        score = 0
    volume_score = score
    feat.append(volume_score)

    pricing_group_score = int(str(orig_object[5]))- int(str(data_object[5]))
    if pricing_group_score==0:
        score = 1
    else:
        score = 0
    pricing_group_score = score
    feat.append(pricing_group_score)


    mrp_score = int(str(orig_object[6]))- int(str(data_object[6]))
    if mrp_score >= 0:
        score = 1
    else:
        score = 0
    mrp_score = score
    feat.append(mrp_score)


    lot_score = int(str(orig_object[7]))- int(str(data_object[7]))
    if lot_score >= 0:
        score = 1
    else:
        score = 0
    lot_score = score
    feat.append(lot_score)

    stock_score = int(str(orig_object[8]))- int(str(data_object[8]))
    if stock_score >= 0:
        score = 1
    else:
        score = 0
    stock_score = score
    feat.append(stock_score)

    availability_score = int(str(orig_object[9]))- int(str(data_object[9]))
    if availability_score >= 0:
        score = 1
    else:
        score = 0
    availability_score = score
    feat.append(availability_score)
    #feat.append(data_object[0])
    #print(feat)
    return feat

def classify(bom_objects,data_objects, printable = True, n_estimators=100, max_depth=2,random_state=0):
    """
    This function is used to compute the accuracy, precision, recall and f-score of the model
    and evaluate the same on being averaged over a few items.
    :param bom_objects: original object to be sent
    :param data_objects: historical data sent
    :param printable: boolean check for printing values or not, default true
    :param n_estimators: ensemble estimates
    :param max_depth: max depth of random forest trees
    :param random_state: random state argument for random forests
    :return print evaluation metrics:
    """
    precision =0.0
    accuracy =0.0
    recall =0.0
    fscore =0.0
    for a in bom_objects:
        orig_bom = a
        feature_matrix = []
        for i in range(1, len(data_objects)):
            temp = feature_extractor(orig_bom, data_objects[i])
            temp.append(data_objects[i][0])
            feature_matrix.append(temp)
        X = np.array(feature_matrix)
        np.random.shuffle(X)
        Xtrain = np.array(X[:,:-1])
        Ytrain = []
        for i in range(len(Xtrain)):
            Ytrain.append(X[i][-1])
        clf = RandomForestClassifier(n_estimators=100, max_depth=2,random_state=0)
        clf.fit(Xtrain[:int(len(Xtrain)*0.7)], Ytrain[:int(len(Xtrain)*0.7)])
        Ytest = clf.predict(Xtrain[int(len(Xtrain)*0.7)+1:])
        scores = precision_recall_fscore_support(Ytrain[int(len(Xtrain)*0.7)+1:], Ytest, average='micro')
        acc = accuracy_score(Ytrain[int(len(Xtrain)*0.7)+1:], Ytest)
        precision+=scores[0]
        recall+=scores[1]
        fscore+=scores[2]
        accuracy+=acc
    lent = len(bom_objects)
    if (printable== True):
        print('precision: ',"{0:0.3f}".format(precision/lent))
        print('recall\t : ',"{0:0.3f}".format(recall/lent))
        print('f-score\t : ',"{0:0.3f}".format(fscore/lent))
        print('accuracy : ',"{0:0.3f}".format(accuracy/lent))
    result = list(scores)
    result.append(acc)
    return result


def main():
    """
    main function being called
    :return: print evaluation metrics:
    """
    df1 = pd.read_csv('evaluate_data.csv')
    df = pd.read_csv('csvfile.csv')
    dataobjects = df.values.tolist()
    bomobjects = df1.values.tolist()
    classify(bomobjects,dataobjects)

if __name__ == "__main__":
    main()
