import pandas as pd 

def readData(file_name):
    data = pd.read_csv(file_name)
    return data