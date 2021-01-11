class User:
    def __init__(self, name, email):
        self.__name = name
        self.__email = email

    def myfunc(self):
        print("Hello my name is " + self.name)

p1 = User("John", 36)

p1.myfunc()