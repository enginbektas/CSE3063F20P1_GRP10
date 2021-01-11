import csv
import pandas

# Başlık; netten buldum aşağıdkai kodu, row ları vs değiştirmmiz lazım
#MALİ:
#ENGİN;
#MAHMUT;nereyi eksik basıyor? Netim iyi değil guys

df = pandas.read_csv('excel files/CSE3063_20201123_Mon_zoom_PollReport.csv')
print(df.to_string())
