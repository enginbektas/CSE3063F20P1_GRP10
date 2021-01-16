import pandas

import StudentReader
from AnswerKeyReader import AnswerKeyReader
from Student import Student


class PollReader:
    # def readPollReport(poll, fileName, studentList):
    studentList = StudentReader.read()
    AnswerKeyList = AnswerKeyReader().readAnswerKey()

    df = pandas.read_csv("excel files/CSE3063_20201123_Mon_zoom_PollReport.csv", keep_default_na=False)
    df = df.loc[:, ~df.columns.str.contains('^Unnamed')]
    df.fillna('')
    currentStd = None

    for index, row in df.iterrows():
        # TODO find student from student list
        print(index[1] + " " + index[2] + " " + index[
            3])  # index1 = name surname, index2 = email, index3 = date, index 4 = q, index 5 = a

        stdFlag = False
        for std in studentList:
            if (std.get_name().upper() == index[1].upper()):
                currentStd = std
                # std.add_answered_poll(currentPoll)
                stdFlag = True
                break
        i = 4
        # for i in range(0, len(test_list)):
        while stdFlag:
            try:
                if len(index[i]) == 0:  # Q and A
                    break
                print("Question = " + index[i])
                print("Answer = " + index[i + 1])
                if "Are you attending this lecture?" in index[i]:
                    currentStd.increment_attendance()
                # else:

                i += 2
            except IndexError:
                break

# def readPollReport(poll, 'excel files/CSE3063_20201123_Mon_zoom_PollReport.csv', studentList):
