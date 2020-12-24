import csv
import sys
from faker import Faker

fake = Faker()
names = [fake.name() for i in range(int(sys.argv[1]))]
emails = [fake.email() for i in range(int(sys.argv[1]))]
phoneNumbers= [fake.phone_number() for i in range(int(sys.argv[1]))]


with open('employee_file.csv', mode='w') as employee_file:
    employee_writer = csv.writer(employee_file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
    for i in range(int(sys.argv[1])):
     employee_writer.writerow([names[i], emails[i], phoneNumbers[i]])
