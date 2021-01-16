class Question:
    def __init__(self, text, trueChoice, choices, numberOfSelectionsPerChoice):
        self.__text = text
        self.__choices = choices
        self.__trueChoice = trueChoice
        self.numberOfSelectionsPerChoice = numberOfSelectionsPerChoice

    def get_numberOfAnswersPerChoice(self, numberOfSelectionsPerChoice):
        return numberOfSelectionsPerChoice

    def set_numberOfAnswersPerChoice(self,numberOfSelectionsPerChoice):
        self.numberOfSelectionsPerChoice = numberOfSelectionsPerChoice

    def get_text(self, text):
        return self.__text

    def set_choices(self, choices):
        self._choices = choices

    def add_choice(self, choice):
        self.choices += choice

    def get_trueChoice(self):
        return self._trueChoice

    def set_trueChoice(self, choice):
        self._trueChoice = choice

    def get_numberOfAnswersPerChoice(self):
        return self._numberOfAnswersPerChoice

    def set_numberOfAnswersPerChoice(self, numberOfAnswersPerChoice):
        self._numberOfAnswersPerChoice = numberOfAnswersPerChoice

