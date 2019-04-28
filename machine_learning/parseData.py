import pandas as pd 
import csv 
import json

def readData(file_name):
    """
    Function to read data from a given csv file.

    Inputs:
    file_name: String specifying the csv file to input from.
    """
    data = pd.read_csv(file_name)
    return data

def write_data(file_name, data):
    """
    Function specifying csv file to output data to.

    Inputs:
    file_name: String indicating which csv file to write to.
    Data: List of data that will be written to file.
    """
    with open(file_name,'w') as resultFile:
        wr = csv.writer(resultFile)
        wr.writerows(data)

def csv_to_json(csv_file, json_file):
    """
    Convert csv format to json

    Inputs:
    csv_file: String indicating which file to read from.
    json_file: String indicating which file to write to.
    """
    csvfile = open(csv_file, 'r')
    jsonfile = open(json_file, 'w')
    fieldnames = ("ID","Val")
    reader = csv.DictReader( csvfile, fieldnames)
    for row in reader: 
        json.dump(row, jsonfile)
        jsonfile.write('\n')   