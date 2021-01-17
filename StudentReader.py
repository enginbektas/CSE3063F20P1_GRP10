import csv
import pandas as pd
import numpy as np
import xlrd
from Student import Student



def read(path):
    std = None
    studentList = []
    # xls = pd.ExcelFile(r"excel files/CES3063_Fall2020_rptSinifListesi.xls") #use r before absolute file path
    # to open a workbook
    excel_workbook = xlrd.open_workbook(path)
    excel_worksheet = excel_workbook.sheet_by_index(0)
    i = 0
    j = 0
    while True:
        try:
            if len(excel_worksheet.cell_value(i, 2)) == 9:
                # print(excel_worksheet.cell_value(i, 2) + " " + excel_worksheet.cell_value(i, 3) + " " + excel_worksheet.cell_value(i, 4), end=' ')
                # print(i)
                std = Student(excel_worksheet.cell_value(i, 4).lower() + " " + excel_worksheet.cell_value(i, 7).lower(), 0)
                studentList.append(std)
                # print(studentList[j].get_studentID(), end = ' ')
                # print(j)
                j += 1
            i += 1
        except IndexError:
            break
    return studentList
