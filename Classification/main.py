### Import dependencies


# Make print function flush by default so it is displayed right away in console
normal_print = print
def print(*args, **kwargs):
    if 'flush' not in kwargs.items():
        kwargs['flush'] = True
    normal_print(*args, **kwargs)

import time

print("Loading classification..."); t1 = time.time()

from python_to_mysql import get_categories, add_category, get_prints, set_print_categories
from python_to_mysql import mock_classifier # classifier returning random results for quick tests
from Predict import predict

print("took", round(time.time() - t1), "s to load dependencies")


### Classify the articles


print("\nLoading articles from databaes..."); t1 = time.time()
x_ids, x_titles, x_booktitles, x_keywords = get_prints()
print("took", round(time.time() - t1), "s to load", len(x_ids), "prints")

print("\nPredicting articles categories...", flush=True); t1 = time.time()
prediction = predict(x_titles, x_keywords, x_booktitles)
print("took", round(time.time() - t1), "s to predict", len(prediction), "categories")

print("\nSaving predicted categories in databaes...", flush=True); t1 = time.time()
set_print_categories(x_ids, prediction)
print("took", round(time.time() - t1), "s to save", len(prediction), "predictions", flush=True)

print("\nClassification done!\n");
