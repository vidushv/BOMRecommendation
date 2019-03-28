import pandas as pd 
import csv 
import json

def readData(file_name):
    data = pd.read_csv(file_name)
    return data

def write_data(file_name, data):
    with open(file_name,'w') as resultFile:
        wr = csv.writer(resultFile)
        wr.writerows(data)

def csv_to_json(csv_file, json_file):
    csvfile = open(csv_file, 'r')
    jsonfile = open(json_file, 'w')
    fieldnames = ("ID","Val")
    reader = csv.DictReader( csvfile, fieldnames)
    for row in reader: 
        json.dump(row, jsonfile)
        jsonfile.write('\n')   