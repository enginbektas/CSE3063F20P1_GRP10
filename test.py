import csv
import pandas

# Başlık; netten buldum aşağıdkai kodu, row ları vs değiştirmmiz lazım
#MALİ:
#ENGİN;
#HASAN:
#MAHMUT;

df = pandas.read_csv("excel files/CSE3063_20201123_Mon_zoom_PollReport.csv", keep_default_na=False)
# del df['^Unnamed']
df = df.loc[:, ~df.columns.str.contains('^Unnamed')]
df.fillna('')

for index, row in df.iterrows():
    #TODO  Create an object of 'student'
    print(index[1] + " " + index[2] + " " +  index[3])
    i = 4
    #for i in range(0, len(test_list)):
    while(True):
        try:
            # TODO Create an object of 'answer'
            if len(index[i]) == 0:
                break
            print("Question = " + index[i])
            print("Answer = " + index[i+1])
            i += 2
        except IndexError:
            break
