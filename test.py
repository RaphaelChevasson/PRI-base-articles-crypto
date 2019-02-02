print('this is python')

try:
	import mysql.connector
	print('this is conda (unless you have mysql-connector-python on your default python)')
except ModuleNotFoundError:
	print('this is not in conda :/')
