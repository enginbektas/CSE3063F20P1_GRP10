class Student:
    def __init__(self, name, email):
        self._name = name
        self._email = email
        self._polls = []

    def myFunc(self):
        print("Hello my name is " + self.__name)

    def get_name(self):
        return self._name

    def get_email(self):
        return self.__email

    def set_name(self, name):
        self.__name = name

    def set_email(self, email):
        self.__email = email

    def get_polls(self):
        return self.__polls

    def set_polls(self, polls):
        self.__polls = polls

    def add_polls(self, list):
        self.__polls += list


p1 = Student("Enes", "enes", "123")

print(p1.get_studentID())
