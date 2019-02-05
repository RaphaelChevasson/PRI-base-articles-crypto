"""
We used this script to parse a bibtex file and create a json template, to make it easier to put it in the database (along with manual categories etc)
Nb: can have artifact on complex latex escape sequences
"""


### File configuration


input_file = "training_data/Crypto/codebasedcryptocayrelnet.bib"
output_file = "codebasedcryptocayrelnet.json"


### Imports


import bibtexparser
from bibtexparser.bparser import BibTexParser
from latex_to_unicode import latex_to_unicode


### Main


import sys

with open(input_file) as bibtex_file:
    parser = BibTexParser(interpolate_strings=False)
    file_to_write = open(output_file,"w+")
    file_to_write.write("{\n")
    bib_database = bibtexparser.load(bibtex_file, parser)
    for publi in bib_database.entries:
        #title = publi['title'].replace("{}", "")
        title = latex_to_unicode(publi['title'])
        if "author" in  publi:
            author = latex_to_unicode(publi['author'])
            #author = publi['author'].replace("{}", "")
        else:
            author = ""
        if "year" in  publi:
            year = latex_to_unicode(publi['year'])
            #year = publi['year'].replace("{}", "")
        else:
            year = ""
        if "booktitle" in  publi:
            booktitle = latex_to_unicode(publi['booktitle'])
            #booktitle = publi['booktitle'].replace("{}", "")
        else:
            booktitle = ""
        Strings_to_write = [
            '{\n',
            '    "title":"{}",'.format(title),
            '    "author":"{}",'.format(author),
            '    "date":"{}",'.format(year),
            '    "booktitle":"{}",'.format(booktitle),
            '    "subcategory":""',
            '},'
        ]
        file_to_write.writelines(['    ' + s + '\n' for s in Strings_to_write])
    file_to_write.write("}")

