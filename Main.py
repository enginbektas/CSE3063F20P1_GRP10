import StudentReader
from AnswerKeyReader import AnswerKeyReader
from PollReader import PollReader


class main:
    student_list = StudentReader.read()  # Get student list
    poll_list = AnswerKeyReader().readAnswerKey()  # get poll list from answer key
    pollReader = PollReader()  # initialize PollReader
    # TODO Loop through current directory
    path = "excel files/CSE3063_20201123_Mon_zoom_PollReport.csv"
    pollReader.read_poll(student_list, poll_list, path)  # Poll reader updates students and polls in lists
