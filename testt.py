import re

print(re.sub("[^0-9a-zA-Z]+",'', "asdas \r\n asd".lower()))
print("asd \r\n sad".rstrip(''))