class StudentAnswers:
    def __init__(self, student, polls, answers, percentages):
        self._student = student
        self._polls = polls
        self._answers = answers
        self._percentages = percentages

    def get_poll(self):
        return self._poll

    def set_poll(self, poll):
        self._poll = poll

    def get_percentage(self):
        return self._percentage
