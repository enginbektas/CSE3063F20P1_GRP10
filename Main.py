import StudentReader
from AnswerKeyReader import AnswerKeyReader
from PollReader import PollReader
import os
import WriterTest


class main:
    studentListPath = path = 'excel files/CES3063_Fall2020_rptSinifListesi.xls'
    pollListPath = 'excel files/CSE3063_20201123_Mon_zoom_PollReport.csv'
    directory = 'excel files'
    pollReader = PollReader()
    poll_list = AnswerKeyReader().readAnswerKey()  # get poll list from answer key

    student_list = StudentReader.read(studentListPath)  # create student list

    for filename in os.listdir(directory):
        if filename.endswith('PollReport.csv'):
            print("s")
            pollReader.read_poll(student_list, poll_list,
                                 'excel files/' + filename)  # all students and polls are updated

    for poll in poll_list:
        WriterTest.create_statistics(student_list, poll)  # 7-b output
        WriterTest.create_poll_output(student_list, poll)  # 7-a output

    WriterTest.create_attendance_output(student_list)  # 6 output
    WriterTest.create_global_output(student_list, poll_list)  # 8 output
