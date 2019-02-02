# Database configuration


config = {
    "user": "root",
    "password": "tseinfo",
    "database": "pri_database",
    "host": "localhost",
    "port": 3306
}

Categories = ["Others", "Crypto"] # These are added to the database if not present


# Database queries


posts_query = "SELECT post_id, title, date, url, book_title FROM posts"
keywords_of_a_post_query = ("SELECT KEYWORD_NAME FROM posts_keywords pk "
                            "JOIN keywords k "
                            "ON pk.KEYWORD_ID = k.KEYWORD_ID "
                            "WHERE pk.POST_ID = {};")
delete_post_query = "DELETE FROM posts WHERE POST_ID = {}"

categories_query = "SELECT category_id, category_name, subcategory_id FROM categories"
add_categories_query = 'INSERT INTO categories (CATEGORY_NAME) VALUES ("{}")'

set_post_category_query = "INSERT INTO posts_categories (POST_ID, CATEGORY_ID) VALUES ({}, {})"
delete_post_category_query = "DELETE FROM posts_categories WHERE POST_ID = {}"


# Helper functions


# You need to pip install mysql-connector-python
import sys
try:
    import mysql.connector
except ModuleNotFoundError:
    print("Error: You need to `pip install mysql-connector-python`", file=sys.stderr)
    quit()


cursor = None
cnx = None

def connect():
    global cursor, cnx
    #print("Connecting to database...")
    try:
        cnx = mysql.connector.connect(**config)
    except mysql.connector.errors.DatabaseError as e: # Can't connect to MySQL server
        print("\nError:", e, "\nAre you sure the database server is running?", file=sys.stderr)
        quit()
    cursor = cnx.cursor()
    cnx.autocommit = True

def disconnect():
    global cursor, cnx
    cursor.close()
    cnx.close()

def query(query, fetch=True, close_connexion=True, limit_count=None, limit_offset=0):
    global cursor, cnx
    if limit_count != None:
        query = query + " LIMIT {}, {}".format(limit_offset, limit_count)
    try:
        cursor.execute(query)
    except AttributeError: # cursor is None
        connect()
        cursor.execute(query)
    except mysql.connector.errors.ProgrammingError: # cursor is not connected
        connect()
        cursor.execute(query)
    
    #print("executed query:\n", query) # useful for debugging
    if fetch:
        result = cursor.fetchall()
    if close_connexion:
        disconnect()
    if fetch:
        return result


# Database functions : get data


def get_prints(limit=None):
    Ids, Titles, Book_titles, Keywords = [], [], [], []
    results = query(posts_query, limit_count=limit, close_connexion=False)
    for (post_id, title, date, url, book_title) in results:
        #print("{:%d %b %Y}: {}".format(date, title))
        #print(" | title:", title)
        #print(" | book title:", book_title)
        keywords = []
        kw_results = query(keywords_of_a_post_query.format(post_id), close_connexion=False)
        for fields in kw_results:
            keywords.append(fields[0]) # fields[0] = keyword field
        #print(" |", len(keywords), "keywords:", " ; ".join(keywords))
        Ids.append(post_id)
        Titles.append(title if title else "")                # if None, put "" instead
        Book_titles.append(book_title if book_title else "") # if None, put "" instead
        Keywords.append(keywords if keywords else "")        # if None, put "" instead
    disconnect()
    return Ids, Titles, Book_titles, Keywords


def get_categories(debug = False):
    Category_ids = []
    Category_names = []
    
    categories = query(categories_query)
    for (id_, name, subcategories) in categories:
        if debug:
            print("{}: {}".format(id_, name))
        Category_ids.append(id_)
        Category_names.append(name)
    return {name: id_ for name, id_ in zip(Category_names, Category_ids)}


# Database functions : set data


Database_categories = None

def add_category(name):
    query(add_categories_query.format(name), fetch=False)

def add_categories(Names):
    for name in Names:
        add_category(name)

def load_categories():
    global Database_categories

    # Make sure all categories are in the database, create them if not
    Database_categories = get_categories()
    for category in Categories:
        if category not in Database_categories:
            print("Warning: category '{}' not found in the database. Adding it to the 'categories' table.".format(category))
            add_category(category)
    
    # reload categories from database
    Database_categories = get_categories() # get_categories(debug=True) to print the categories in the database

def set_print_category(post_id, category_name, delete_post_if_category_is="Other", close_connexion=True):
    # delete current value
    query(delete_post_category_query.format(post_id), fetch=False, close_connexion=False)
    # create new value, or delete the post if delete_post_if_category_is == category_name
    if delete_post_if_category_is != None and category_name == delete_post_if_category_is:
            # if delete_post_if_category_is is specified and match the post category, delete the post
        query(delete_post_query, fetch=False, close_connexion=close_connexion)
    else:
        if Database_categories == None:
            load_categories()
        assert category_name in Categories, "category {} is not in the list Categories {}, but it should".format(category_name, Categories)
        category_id = Database_categories[category_name]
        query(set_post_category_query.format(post_id, category_id), fetch=False, close_connexion=close_connexion)

def set_print_categories(Post_ids, Category_names):
    assert len(Post_ids) == len(Category_names), "There should be one category per id, but there is not"
    for i in range(len(Post_ids)):
        set_print_category(Post_ids[i], Category_names[i], close_connexion=False)
    disconnect()


# Debugging functions :


def print_all_articles(limit=None):
    results = query(posts_query, limit_count=limit)
    for (post_id, title, date, url, book_title) in results:
        print("{:%d %b %Y}: {}".format(date, title))
        print(" | url:", url)
        
def print_all_articles_with_info(limit=None):
    results = query(posts_query, limit_count=limit, close_connexion=False)
    for (post_id, title, date, url, book_title) in results:
        print("{:%d %b %Y}: {}".format(date, title))
        print(" | title:", title)
        print(" | book title:", book_title)
        keywords = []
        kw_results = query(keywords_of_a_post_query.format(post_id), close_connexion=False)
        for fields in kw_results:
            keywords.append(fields[0]) # fields[0] = keyword field
        print(" |", len(keywords), "keywords:", " ; ".join(keywords))
        disconnect()
        
def print_articles_publicated_between(start_year, end_year, limit=None):
    results = query(posts_between_dates_query.format(datetime.date(start_year, 1, 1),
                                                     datetime.date(end_year, 12, 31)),
                    limit_count=limit)
    for (post_id, title, date, url, book_title) in results:
        print("{:%d %b %Y}: {}".format(date, title))
        print(" | title:", title)
        print(" | book title:", book_title)

def mock_classifier(x_titles, x_keywords, x_booktitles):
    import random
    assert len(x_titles) == len(x_booktitles) and len(x_titles) == len(x_keywords)
    return [random.choice(["Others", "Crypto"]) for i in range(len(x_titles))]
