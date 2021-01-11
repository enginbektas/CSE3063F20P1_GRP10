class Student:
    def __init__(self, name, email):
        self._name = name
        self._email = email
        self._polls = []

    def myFunc(self):
        print("Hello my name is " + self._name)

    def get_name(self):
        return self._name

    def get_email(self):
        return self._email

    def set_name(self, name):
        self._name = name

    def set_email(self, email):
        self._email = email

    def get_polls(self):
        return self._polls

    def set_polls(self, polls):
        self._polls = polls

    def add_polls(self, list):
        self._polls += list


p1 = Student("Enes", 1)
p1.set_name("hasan")

p1.add_polls('a')
p1.add_polls(['c', 'b'])
print(p1.get_polls())

print(p1.get_name())
