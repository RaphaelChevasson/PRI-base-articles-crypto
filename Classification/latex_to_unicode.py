import unicodedata
import re

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

# {regex: replacement} dictionary
latex_commands_whith_parametter = {
    r"\\'(.)": lambda matchgroup : unicodedata.lookup(unicodedata.name(matchgroup.group(1)) + ' WITH ACUTE'),
    r'\\`(.)': lambda matchgroup : unicodedata.lookup(unicodedata.name(matchgroup.group(1)) + ' WITH GRAVE'),
    r'\\^(.)': lambda matchgroup : unicodedata.lookup(unicodedata.name(matchgroup.group(1)) + ' WITH CIRCUMFLEX'),
    r'\\"(.)': lambda matchgroup : unicodedata.lookup(unicodedata.name(matchgroup.group(1)) + ' WITH DIAERESIS'),
    r'\\H(.)': lambda matchgroup : unicodedata.lookup(unicodedata.name(matchgroup.group(1)) + ' WITH DOUBLE ACUTE'),
    r'\\~(.)': lambda matchgroup : unicodedata.lookup(unicodedata.name(matchgroup.group(1)) + ' WITH TILDE'),
    r'\\c(.)': lambda matchgroup : unicodedata.lookup(unicodedata.name(matchgroup.group(1)) + ' WITH CEDILLA'),
    r'\\k(.)': lambda matchgroup : unicodedata.lookup(unicodedata.name(matchgroup.group(1)) + ' WITH OGONEK'),
    r'\\=(.)': lambda matchgroup : unicodedata.lookup(unicodedata.name(matchgroup.group(1)) + ' WITH MACRON'),
    r'\\.(.)': lambda matchgroup : unicodedata.lookup(unicodedata.name(matchgroup.group(1)) + ' WITH DOT ABOVE'),
    r'\\d(.)': lambda matchgroup : unicodedata.lookup(unicodedata.name(matchgroup.group(1)) + ' WITH DOT BELOW'),
    r'\\r(.)': lambda matchgroup : unicodedata.lookup(unicodedata.name(matchgroup.group(1)) + ' WITH RING ABOVE'),
    r'\\u(.)': lambda matchgroup : unicodedata.lookup(unicodedata.name(matchgroup.group(1)) + ' WITH BREVE'),
    r'\\v(.)': lambda matchgroup : unicodedata.lookup(unicodedata.name(matchgroup.group(1)) + ' WITH CARON'),
    r'\\textcircled(.)': lambda matchgroup : unicodedata.lookup(unicodedata.name('CIRCLED ' + matchgroup.group(1)))
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