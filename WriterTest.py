import xlsxwriter
from openpyxl import load_workbook

# attandance output need student excel
# quizz output need student excel
# answer chart output
# 8 için output. need everything


workbook = xlsxwriter.Workbook('testOutput.xlsx')
worksheet = workbook.add_worksheet()

worksheet.write(0, 0, 'Name')
worksheet.write(0, 1, 'Sayı')

worksheet.write(1, 0, 'Mali')
worksheet.write(1, 1, 12)

worksheet.write(2, 0, 'İkinci')
worksheet.write(2, 1, 5)

chart1 = workbook.add_chart({'type': 'pie'})
chart2 = workbook.add_chart({'type': 'column'})
chart1.add_series({
    'name': 'pie adam',
    'categories': ['Sheet1', 1, 0, 3, 0],
    'values': ['Sheet1', 1, 1, 3, 1],
})
chart2.add_series({
    'name': 'column çocuk',
    'categories': ['Sheet1', 1, 0, 3, 0],
    'values': ['Sheet1', 1, 1, 3, 1],
})
chart1.set_title({'name': 'isimmmmm'})
chart2.set_title({'name': 'isimmmmmmmm'})
chart1.set_style(10)
chart2.set_style(10)
chart1.set_size({'width': 300, 'height': 150})
chart2.set_size({'width': 300, 'height': 150})
worksheet.insert_chart('C2', chart1, {'x_offset': 0, 'y_offset': 0})
worksheet.insert_chart('H2', chart2, {'x_offset': 0, 'y_offset': 0})
workbook.close()


def createAttendanceOutput(studentList):
    if path.exits('excel files/CSE3063_Fall2020_att_SinifListesi.xlsx'):
        pass
    else:
        p.save_book_as(file_name='excel files/CES3063_Fall2020_rptSinifListesi.xls',
                       dest_file_name='excel files/CSE3063_Fall2020_att_SinifListesi.xlsx')

    wb = load_workbook('excel files/CSE3063_Fall2020_att_SinifListesi.xlsx')
    ws = wb.worksheets[0]

    ws['N14'] = "Deneme"

    wb.save('CSE3063_Fall2020_rptSinifListesiAttendence.xlsx')


def createPollOutput(studentList, poll):
    if path.exits('excel files/CSE3063_Fall2020_' + poll.get_name() + '.xlsx'):
        pass
    else:
        p.save_book_as(file_name='excel files/CSE3063_Fall2020_rptSinifListesi.xls',
                       dest_file_name='excel files/CSE3063_Fall2020_' + poll.get_name() + '.xlsx')

    wb = load_workbook('excel files/CSE3063_Fall2020_' + poll.get_name() + '.xlsx')
    ws = wb.worksheets[0]
    lenPoll = len(poll.get_questions())

    i = 14
    columnChr = 78
    for j in lenPoll:
        ws[chr(columnChr) + '13'] = "Q" + str(j)
        j += 1
        columnChr += 1
    successChr = columnChr
    columnChr = 78
    ws[chr(successChr) + '13'] = "Number of questions"
    ws[chr(successChr + 1) + '13'] = "Success Percentage"

    for student in studentList:
        answeredPoll = None
        for ap in student.get_answered_polls:
            if poll.get_name() == ap.get_poll().get_name():
                answeredPoll = ap
                break
        count = 0
        correctAnswer = 0
        while successChr > columnChr:
            if answeredPoll.get_answer(poll.get_questions()[count]) == poll.get_questions()[count].get_trueChoice:
                ws[chr(columnChr) + str(i)] = "1"
                correctAnswer += 1
            else:
                ws[chr(columnChr) + str(i)] = "0"
            columnChr += 1
            count += 1
        ws[chr(successChr) + str(i)] = str(successChr - columnChr)
        ws[chr(successChr + 1) + str(i)] = str(correctAnswer / (successChr - columnChr) * 100)
        i += 1


def createGlobalOutput(studentList, poll):
    if path.exits('excel files/CSE33063_Fall2020_glbSinifListesi.xlsx'):
        pass
    else:
        p.save_book_as(file_name='excel files/CSE3063_Fall2020_rptSinifListesi.xls',
                       dest_file_name='excel files/CSE33063_Fall2020_glbSinifListesi.xlsx')
    wb = load_workbook('excel files/CSE33063_Fall2020_glbSinifListesi.xlsx')
    ws = wb.worksheets[0]
    ws['N13'] = "q1_name"
    ws['P13'] = "q1_numOfQuestions"
    ws['R13'] = "q1_successRate"

    i = 14
    for student in studentList:
        ws['N' + str(i)] = str(poll.get_name())
        ws['P' + str(i)] = str(len(poll.get_questions()))
        ws['Q' + str(i)] = "25" #success

        answeredPoll = None
        for ap in student.get_answered_polls:
            if poll.get_name() == ap.get_poll().get_name():
                answeredPoll = ap
                break

        for q in poll.get_questions:
            if answeredPoll.get_answer(q) == q.get_trueChoice:
                correctAnswer += 1

        i += 1