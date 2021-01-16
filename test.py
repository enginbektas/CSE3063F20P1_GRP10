import csv
import pandas



def readPollReport(poll, fileName, studentList):
    df = pandas.read_csv(fileName, keep_default_na=False)

    df = df.loc[:, ~df.columns.str.contains('^Unnamed')]
    df.fillna('')


    for index, row in df.iterrows():
        # TODO find student from student list
        print(index[1] + " " + index[2] + " " +  index[3])
        i = 4
        #for i in range(0, len(test_list)):
        while True:
            try:
                if len(index[i]) == 0:
                    break
                print("Question = " + index[i])
                print("Answer = " + index[i + 1])
                #studentList or read
                #createAttendanceOutput(studentList)
                #if question = attendence sorusu
                    #student.att +=1
                #else
                    # TODO Add question to poll if not added before
                    # TODO Increment numberOfSelections of this answer
                i += 2
            except IndexError:
                break


def readPollReport(poll, 'excel files/CSE3063_20201123_Mon_zoom_PollReport.csv', studentList):