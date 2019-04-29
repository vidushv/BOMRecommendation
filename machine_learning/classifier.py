import sys
import pandas as pd
import numpy as np
from sklearn.metrics import precision_recall_fscore_support
from sklearn.metrics import accuracy_score
from sklearn.ensemble import RandomForestClassifier
from sklearn.datasets import make_classification

from gensim.models.keyedvectors import KeyedVectors
model = KeyedVectors.load_word2vec_format("glove_model2.txt", binary=False, limit=50000)


def feature_extractor(orig_object, data_object):
    """
    This function is used to extract features in the data set and return a list of all the features computed.
    :param orig_object:
    :param data_object:
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

    pricing_group_score = int(str(orig_object[4]))- int(str(data_object[4]))
    if pricing_group_score==0:
        score = 1
    else:
        score = 0
    pricing_group_score = score
    feat.append(pricing_group_score)

    volume_score = int(str(orig_object[5]))- int(str(data_object[5]))
    if volume_score <= 0:
        score = 1
    else:
        score = 0
    volume_score = score
    feat.append(volume_score)

    lot_score = int(str(orig_object[6]))- int(str(data_object[6]))
    if lot_score >= 0:
        score = 1
    else:
        score = 0
    lot_score = score
    feat.append(lot_score)

    stock_score = int(str(orig_object[7]))- int(str(data_object[7]))
    if stock_score >= 0:
        score = 1
    else:
        score = 0
    stock_score = score
    feat.append(stock_score)

    availability_score = int(str(orig_object[8]))- int(str(data_object[8]))
    if availability_score >= 0:
        score = 1
    else:
        score = 0
    availability_score = score
    feat.append(availability_score)
    feat.append(data_object[0])
    return feat

def classify(bom_objects,data_objects):
    """
    This function is used to compute the accuracy, precision, recall and f-score of the model
    and evaluate the same on being averaged over a few items.
    :param bom_objects:
    :param data_objects:
    :return:
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
        clf.fit(Xtrain[:350], Ytrain[:350])
        Ytest = clf.predict(Xtrain[351:553])
        scores = precision_recall_fscore_support(Ytrain[351:553], Ytest, average='micro')
        acc = accuracy_score(Ytrain[351:553], Ytest)
        precision+=scores[0]
        recall+=scores[1]
        fscore+=scores[2]
        accuracy+=acc
    lent = len(bom_objects)
    print('precision: ',"{0:0.3f}".format(precision/lent))
    print('recall\t : ',"{0:0.3f}".format(recall/lent))
    print('f-score\t : ',"{0:0.3f}".format(fscore/lent))
    print('accuracy : ',"{0:0.3f}".format(accuracy/lent))


def main():
    df1 = pd.read_csv('evaluate_data.csv')
    df = pd.read_csv('csvfile.csv')
    dataobjects = df.values.tolist()
    bomobjects = df1.values.tolist()
    classify(bomobjects,dataobjects)

main()
