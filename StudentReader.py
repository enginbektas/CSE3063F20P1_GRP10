import xlrd
from Student import Student


def read(path):
    studentList = []
    excel_workbook = xlrd.open_workbook(path)
    excel_worksheet = excel_workbook.sheet_by_index(0)
    i = 0
    j = 0
    while True:
        try:
            if len(excel_worksheet.cell_value(i, 2)) == 9:
                std = Student(excel_worksheet.cell_value(i, 4).lower() + " " + excel_worksheet.cell_value(i, 7).lower(),
                              0)
                studentList.append(std)
                j += 1
            i += 1
        except IndexError:
            break
    return studentList
