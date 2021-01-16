import csv
import pandas
import numpy as np

df = pandas.read_csv("excel files/Answer Key.csv", keep_default_na=False)
df = df.loc[:, ~df.columns.str.contains('^Unnamed')]
df.fillna('')
with open("excel files/Answer Key.csv", newline='') as csvfile:
    c = csv.reader(csvfile, delimiter=',', quotechar='"')
    isFirst = True
    for row in c:
        if row[1] == '':
            isFirst = True
        if isFirst:
            print("first!")
            print("pollname is" + row[0]) #row 0 is pollName
            isFirst = False
        else:
            print(row[0] + " " + row[1]) #row 0 is Q, row 1 is A


