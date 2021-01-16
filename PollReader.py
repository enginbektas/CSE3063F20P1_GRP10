import pandas

import Question
import StudentReader
from AnswerKeyReader import AnswerKeyReader
from Poll import Poll
from Student import Student


class PollReader:
    # def readPollReport(poll, fileName, studentList):
    studentList = StudentReader.read()
    answerKeyList = AnswerKeyReader().readAnswerKey()

    df = pandas.read_csv("excel files/CSE3063_20201123_Mon_zoom_PollReport.csv", keep_default_na=False)
    df = df.loc[:, ~df.columns.str.contains('^Unnamed')]
    df.fillna('')
    currentStd = None
    currentPoll = None

    tempPoll = Poll(None, None, None)
    for index, row in df.iterrows():
        # TODO find student from student list
        print(index[1] + " " + index[2] + " " + index[
            3])  # index1 = name surname, index2 = email, index3 = date, index 4 = q, index 5 = a

        stdFlag = False
        for std in studentList:
            if std.get_name().upper() == index[1].upper():
                currentStd = std
                # std.add_answered_poll(currentPoll)
                stdFlag = True
                break
        i = 4
        # for i in range(0, len(test_list)):
        std.add_answered_poll(poll)
        while stdFlag:
            try:
                if len(index[i]) == 0:  # Q and A
                    break

                tempPoll = Poll(index[i], index[i + 1], [])  # create tempPoll to compare with answerKey polls
                if "Are you attending this lecture?" in index[i]:  # if attendance
                    currentStd.increment_attendance()
                    i += 2
                    continue
                tempQuestion = Question.Question(index[i], None)
                tempPoll.add_question(tempQuestion)
                i += 2
                for poll in answerKeyList:  # poll holds iterator of polls in answerkeylist
                    for question in poll.get_questions():  # question is one question of iteratorPoll
                        if question not in tempPoll.get_questions():  # if each question in the poll from answerKeyList is contained by the current Poll
                            break
                        else:
                            currentPoll = poll
                            continue
                std.get_answered_polls()[-1].add_question_and_answers(index[i], index[i + 1])#Question&Answer

            except IndexError:
                break

# def readPollReport(poll, 'excel files/CSE3063_20201123_Mon_zoom_PollReport.csv', studentList):
