print('this is python')

try:
	import bibtexparser
	print('this is conda (unless you have bibtexparser on your default python)')
except ModuleNotFoundError:
	print('this is not in conda :/')
