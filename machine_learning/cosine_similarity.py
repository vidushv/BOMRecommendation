import sys
import math
import pandas as pd
import parseData

def java_string_hashcode(s):
    """
    Convert any string to a java hashcode to represent it as an integer.

    Inputs:
    s: String to be converted.
    """
    h = 0
    s=str(s)
    for c in s:
        c1 = str(c)
        h = (31 * h + ord(c1)) & 0xFFFFFFFF
    return ((h + 0x80000000) & 0xFFFFFFFF) - 0x80000000

def feature_encoder(feature_ob):
    """
    Convert every entry in a feature object to its integer encoded counter part.

    Inputs:
    feature_ob: Feature object to be encoded.
    """
    count=1
    feat = []
    for a in feature_ob:
        x = java_string_hashcode(a)
        feat.append(x%count)
        count+=1
    return feat

def Sort(sub_li):
    """
    Sort results in descending order (by second value in rows). The purpose of this function 
    is to print the results from best to worst.

    Inputs:
    sub_li: List to be sorted by second feature.
    """
    sub_li.sort(key = lambda x: x[1], reverse=True)
    return sub_li

#data_objects is a list of list (model it as pandas data frame)
def cosine_function(data_objects):
    """
    Calculate the cosine similarity for each object in the passed on objects.

    Inputs: data_objects: List of lists representing different objects.
    """
    orig_bom = data_objects[0]
    orig_feat = feature_encoder(orig_bom)
    result = []
    for index in range(1, len(data_objects)):
        current_feat = feature_encoder(data_objects[index])
        sum = 0.0
        v_one = 0.0
        v_two = 0.0
        for i in range(1,len(current_feat)):
            sum+=current_feat[i]*orig_feat[i]
            v_one += current_feat[i]*current_feat[i]
            v_two += orig_feat[i]*orig_feat[i]
        v1 = math.sqrt(v_one)
        v2 = math.sqrt(v_two)
        rating = sum/(v1*v2)
        result.append([data_objects[index][0],rating])
    return result

def main():
    """
    main function to run cosine_similarity.
    """
    df = parseData.readData('csvfile.csv')
    dataobjects = df.values.tolist()
    result = cosine_function(dataobjects)
    result = Sort(result)
    print('id , scores')
    for i in range(len(result)):
        print(result[i][0],',',result[i][1])

if __name__ == "__main__":
    main()
