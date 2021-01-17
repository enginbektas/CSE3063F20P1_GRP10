import StudentReader
from AnswerKeyReader import AnswerKeyReader
from PollReader import PollReader
import os
import re
import WriterTest

class main:
    studentListPath = path = 'excel files/CES3063_Fall2020_rptSinifListesi.xls'
    pollListPath = 'excel files/CSE3063_20201123_Mon_zoom_PollReport.csv'
    directory = 'excel files'
    pollReader = PollReader()
    poll_list = AnswerKeyReader().readAnswerKey()  # get poll list from answer key

    # TODO Loop through current directory

    student_list = StudentReader.read(studentListPath)  # create student list

    for filename in os.listdir(directory):
        if filename.endswith('PollReport.csv'):
            pollReader.read_poll(student_list, poll_list, 'excel files/' + filename) # all students and polls are updated


    # TODO Call functions for 6 and 8
    # TODO Call functions for 7a and 7b for every poll
    #WriterTest.create_poll_output(student_list, poll_list[0])
    #WriterTest.create_attendance_output(student_list) #6 output
    print(re.sub("[^0-9a-zA-Z]+",'',"s^  asd:".upper()))