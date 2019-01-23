import unicodedata
import re
import sys

# {string_to_replace, replacement} dictionary
latex_commands = {
    r'\l{}': 'ø',
    r'\o': 'ł',
    r'\textless': '<',
    r'\textgreater': '>',
    r'\euro{}': '€',
    r'\EUR{}': '€',
    r'\P': '¶',
    r'\ddag': '‡',
    r'\textbar': '|',
    r'\textendash': '–',
    r'\texttrademark': '™',
    r'\textexclamdown': '¡',
    r'\pounds': '£',
    r'\S': '§',
    r'\dag': '†',
    r'\textbackslash': '',
    r'\textemdash': '—',
    r'\textregistered': '®',
    r'\textquestiondown': '¿',
    r'\copyright': '©',
    r'\%': '%',
    r'\$': '$',
    r'\{': '{',
    r'\_': '_',
    r'\#': '#',
    r'\&': '&',
    r'\}': '}'
}

def get_replacement(unicode_expression, matchgroup):
    matched_caracter = matchgroup.group(1) # caracter which take the place of '(.)'
    replacement_unicode_caracter_name = unicode_expression.replace('(.)', unicodedata.name(matched_caracter))
    try: # try to get the unicode caracter corresponding to unicode_caracter_name
        replacement_caracter = unicodedata.lookup(replacement_unicode_caracter_name)
    except KeyError as e:
        print('Cannot find unicode for', replacement_unicode_caracter_name,
              ', using', matchgroup.group(1), 'instead', file = sys.stderr)
        replacement_caracter = matchgroup.group(1)
    return replacement_caracter

# {regex: replacement} dictionary
latex_commands_whith_parametter = {
    r"\\'(.)": lambda matchgroup : get_replacement('(.) WITH ACUTE', matchgroup),
    r'\\`(.)': lambda matchgroup : get_replacement('(.) WITH GRAVE', matchgroup),
    r'\\^(.)': lambda matchgroup : get_replacement('(.) WITH CIRCUMFLEX', matchgroup),
    r'\\"(.)': lambda matchgroup : get_replacement('(.) WITH DIAERESIS', matchgroup),
    r'\\H(.)': lambda matchgroup : get_replacement('(.) WITH DOUBLE ACUTE', matchgroup),
    r'\\~(.)': lambda matchgroup : get_replacement('(.) WITH TILDE', matchgroup),
    r'\\c(.)': lambda matchgroup : get_replacement('(.) WITH CEDILLA', matchgroup),
    r'\\k(.)': lambda matchgroup : get_replacement('(.) WITH OGONEK', matchgroup),
    r'\\=(.)': lambda matchgroup : get_replacement('(.) WITH MACRON', matchgroup),
    r'\\\.(.)': lambda matchgroup : get_replacement('(.) WITH DOT ABOVE', matchgroup),
    r'\\d(.)': lambda matchgroup : get_replacement('(.) WITH DOT BELOW', matchgroup),
    r'\\r(.)': lambda matchgroup : get_replacement('(.) WITH RING ABOVE', matchgroup),
    r'\\u(.)': lambda matchgroup : get_replacement('(.) WITH BREVE', matchgroup),
    r'\\v(.)': lambda matchgroup : get_replacement('(.) WITH CARON', matchgroup),
    r'\\textcircled(.)': lambda matchgroup : get_replacement('CIRCLED (.)', matchgroup),
        # we need to escape the '.' and '\' to match the litteral '.' and '\'
        # the '(.)' match any one caracter and enable us to retriview it as a matchgroup
}

def latex_to_unicode(s):
    """Parse a LaTeX string and replace with unicode where possible"""
    # remove '{' and '}'
    s = re.sub("{", "", s)
    s = re.sub("}", "", s)
    # replace simple strings
    for latex, unicode in latex_commands.items():
        latex = re.escape(latex)     # escape characters with meaning in the regex langage, like '*',
        unicode = re.escape(unicode) # with a '\', so they are considered as normal characters
        s = re.sub(latex, unicode, s)
    # replace complex paterns
    for latex, replacement_function in latex_commands_whith_parametter.items():
        pattern = re.compile(latex)
        s = re.sub(pattern, replacement_function, s)
    return s