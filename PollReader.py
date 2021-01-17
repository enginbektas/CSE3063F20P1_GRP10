import re

import pandas

import Question
import StudentReader
from AnswerKeyReader import AnswerKeyReader
from Poll import Poll
from StudentAnswers import StudentAnswers
from Student import Student

class PollReader:
    def read_poll(self, student_list_param, ak_poll_list_param, path):

        # def readPollReport(poll, fileName, studentList):
        student_list = student_list_param
        ak_poll_list = ak_poll_list_param

        df = pandas.read_csv(path, keep_default_na=False)
        df = df.loc[:, ~df.columns.str.contains('^Unnamed')]
        df.fillna('')
        current_student = None
        current_poll = None

        temp_poll = Poll(None, None, None)
        for index, row in df.iterrows():  # iterates student row
            # TODO find student from student list
            try:
                print(index[1] + " " + index[2] + " " + index[
                    3])  # index1 = name surname, index2 = email, index3 = date, index 4 = q, index 5 = a
            except IndexError:
                break
            stdFlag = False  # stdFlag okunan öğreniyi student_list içinde bulamazsa kod while içine girmiyor.
            for std in student_list:
                result = ''.join([i for i in index[1] if not i.isdigit()])
                if std.get_name().upper() == result.upper():
                    current_student = std
                    current_student.set_name(result)

                    stdFlag = True
                    break
            i = 4
            temp_poll = Poll(0, 0, [])  # create tempPoll to compare with answerKey polls
            qaDict = {}  # instantiate

            while stdFlag:  # reads all questions and answers in a row
                try:
                    if len(index[i]) == 0:  # Q and A
                        break

                    if "Are you attending this lecture?" in index[i]:  # if attendance
                        current_student.increment_attendance()
                        i += 2
                        continue
                    tempQuestion = Question.Question(index[i], None)
                    temp_poll.add_question(tempQuestion)
                    qaDict[index[i]] = index[i + 1]  # add element
                    print(index[i] + " " + index[i + 1])
                    i += 2
                except IndexError:
                    break
            i = 0
            while True:
                try:
                    if len(row[i]) == 0:  # Q and A
                        break

                    if "Are you attending this lecture?" in row[i]:  # if attendance
                        current_student.increment_attendance()
                        i += 2
                        continue
                    tempQuestion = Question.Question(row[i], None)
                    temp_poll.add_question(tempQuestion)
                    qaDict[row[i]] = row[i + 1]  # add element
                    print(row[i] + " " + row[i + 1])
                    i += 2
                except IndexError:
                    break

            for poll in ak_poll_list:  # poll holds iterator of polls in answerkeylist
                for s in temp_poll.get_questions():
                    ifNotFound = True
                    for question in poll.get_questions():  # question is one question of iteratorPoll
                        if re.sub("[^0-9a-zA-Z]+", '', question.get_text().upper()) == re.sub("[^0-9a-zA-Z]+", '', s.get_text().upper()):
                            current_poll = poll
                            ifNotFound = False
                            break

                    if ifNotFound:
                        current_poll = None
                        break

                if current_poll is not None:
                    break
            current_student.add_answered_poll(current_poll)
            current_student.get_answered_polls()[-1].set_question_and_answers(qaDict)  # Question&Answer

    # def readPollReport(poll, 'excel files/CSE3063_20201123_Mon_zoom_PollReport.csv', studentList):
