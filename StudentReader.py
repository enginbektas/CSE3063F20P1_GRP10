import csv
import pandas as pd
import numpy as np
import xlrd
from Student import Student

studentList = []
# xls = pd.ExcelFile(r"excel files/CES3063_Fall2020_rptSinifListesi.xls") #use r before absolute file path
# to open a workbook
path = 'excel files/CES3063_Fall2020_rptSinifListesi.xls'
excel_workbook = xlrd.open_workbook(path)
excel_worksheet = excel_workbook.sheet_by_index(0)


def read():
    i = 0
    j = 0
    while True:
        try:
            if len(excel_worksheet.cell_value(i, 2)) == 9:
                #if the values inside the cell has a length of '9' then
                # print(excel_worksheet.cell_value(i, 2) + " " + excel_worksheet.cell_value(i, 3) + " " + excel_worksheet.cell_value(i, 4), end=' ')
                # print(i)
                std = Student(excel_worksheet.cell_value(i, 4), excel_worksheet.cell_value(i, 7),
                              excel_worksheet.cell_value(i, 2))
                studentList.append(std)
                # print(studentList[j].get_studentID(), end = ' ')
                # print(j)
                j += 1
            i += 1
        except IndexError:
            break
    return studentList
