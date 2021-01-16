class Student:

    def __init__(self, name, attendance):
        self.__name = name
        self.__attendance = attendance
        self.__answeredPolls = None  # list of StudentAnswers

    def myFunc(self):
        print("Hello my name is " + self.__name)

    def get_name(self):
        return self.__name

    def set_name(self, name):
        self.__name = name

    def update_attendance(self):
        self.__attendance =+ 1

    def get_attendance(self):
        return self.__attendance
