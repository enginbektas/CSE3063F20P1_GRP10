import xlsxwriter

# attandance output need student excel
# quizz output need student excel
# answer chart output
# 8 için output. need everything


workbook = xlsxwriter.Workbook('Output/testOutput.xlsx')
worksheet = workbook.add_worksheet()

worksheet.write(0, 0, 'Name')
worksheet.write(0, 1, 'Sayı')

worksheet.write(1, 0, 'Mali')
worksheet.write(1, 1, 12)

worksheet.write(2, 0, 'İkinci')
worksheet.write(2, 1, 5)

chart1 = workbook.add_chart({'type': 'pie'})
chart2 = workbook.add_chart({'type': 'column'})
chart1.add_series({
    'name': 'pie adam',
    'categories': ['Sheet1', 1, 0, 3, 0],
    'values': ['Sheet1', 1, 1, 3, 1],
})
chart2.add_series({
    'name': 'column çocuk',
    'categories': ['Sheet1', 1, 0, 3, 0],
    'values': ['Sheet1', 1, 1, 3, 1],
})
chart1.set_title({'name': 'isimmmmm'})
chart2.set_title({'name': 'isimmmmmmmm'})
chart1.set_style(10)
chart2.set_style(10)
chart1.set_size({'width': 300, 'height': 150})
chart2.set_size({'width': 300, 'height': 150})
worksheet.insert_chart('C2', chart1, {'x_offset': 0, 'y_offset': 0})
worksheet.insert_chart('H2', chart2, {'x_offset': 0, 'y_offset': 0})
workbook.close()
