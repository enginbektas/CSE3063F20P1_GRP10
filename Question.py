class Question:
    def __init__(self, choices, trueChoice, numberOfAnswersPerChoice):
        self._choices = choices
        self._trueChoice = trueChoice
        self._numberOfAnswersPerChoice = numberOfAnswersPerChoice

    def get_choices(self):
        return self._choices

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

