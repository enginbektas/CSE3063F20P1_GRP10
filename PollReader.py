import pandas
# sssssss
import Question
import StudentReader
from AnswerKeyReader import AnswerKeyReader
from Poll import Poll
from StudentAnswers import StudentAnswers
from Student import Student


class PollReader:
    # def readPollReport(poll, fileName, studentList):
    student_list = StudentReader.read()
    ak_poll_list = AnswerKeyReader().readAnswerKey()

    df = pandas.read_csv("excel files/CSE3063_20201123_Mon_zoom_PollReport.csv", keep_default_na=False)
    df = df.loc[:, ~df.columns.str.contains('^Unnamed')]
    df.fillna('')
    current_student = None
    current_poll = None


    temp_poll = Poll(None, None, None)
    for index, row in df.iterrows(): #iterates student row
        # TODO find student from student list
        print(index[1] + " " + index[2] + " " + index[
            3])  # index1 = name surname, index2 = email, index3 = date, index 4 = q, index 5 = a

        stdFlag = False  # stdFlag okunan öğreniyi student_list içinde bulamazsa kod while içine girmiyor.
        for std in student_list:
            if std.get_name().upper() == index[1].upper():
                current_student = std
                # std.add_answered_poll(currentPoll)
                stdFlag = True
                break
        i = 4
        temp_poll = Poll(0, 0, [])  # create tempPoll to compare with answerKey polls
        qaDict = {}  # instantiate

        while stdFlag: #reads all questions and answers in a row
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
                i += 2
            except IndexError:
                break

        for poll in ak_poll_list:  # poll holds iterator of polls in answerkeylist
            for question in poll.get_questions():  # question is one question of iteratorPoll
                if question not in temp_poll.get_questions():  # if each question in the poll from answerKeyList is contained by the current Poll
                    current_poll = None
                    break
                else:
                    current_poll = poll
                    continue
            break
        current_student.add_answered_poll(current_poll)
        current_student.get_answered_polls()[-1].set_question_and_answers(qaDict)  # Question&Answer

# def readPollReport(poll, 'excel files/CSE3063_20201123_Mon_zoom_PollReport.csv', studentList):
