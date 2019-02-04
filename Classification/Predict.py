from keras.models import load_model
from keras.preprocessing.text import Tokenizer
from keras.preprocessing.sequence import pad_sequences
import pickle
import numpy as np

def predict(x_title,x_keywords,x_booktitle):
    """
    Predict the classes of publications.
    input:
        The lists containing the titles, keywords and booktitles of the publications.
    output:
        A list containing strings representing the class predicted.
    """
    MAX_SEQUENCE_LENGTH = 500
    NUMBER_OF_USED_METADATA = 3
    
    ### load our saved model ###
    model = load_model('model.h5')

    ### load tokenizers ###
    tokenizer_title = Tokenizer()
    with open('tokenizer_title.pickle', 'rb') as handle:
        tokenizer_title = pickle.load(handle)

    tokenizer_keywords = Tokenizer()
    with open('tokenizer_keywords.pickle', 'rb') as handle:
        tokenizer_keywords = pickle.load(handle)

    tokenizer_booktitle = Tokenizer()
    with open('tokenizer_booktitle.pickle', 'rb') as handle:
        tokenizer_booktitle = pickle.load(handle)
       
    ### Vectorize the data ###
    data = []
    # titles
    sequences_title = tokenizer_title.texts_to_sequences(x_title)
    data_title = pad_sequences(sequences_title, maxlen=MAX_SEQUENCE_LENGTH)

    # keywords
    sequences_keywords = tokenizer_keywords.texts_to_sequences(x_keywords)
    data_keywords = pad_sequences(sequences_keywords, maxlen=MAX_SEQUENCE_LENGTH)

    # booktitle
    sequences_booktitle = tokenizer_booktitle.texts_to_sequences(x_booktitle)
    data_booktitle = pad_sequences(sequences_booktitle, maxlen=MAX_SEQUENCE_LENGTH)

    ###

    data.append(data_title)
    data.append(data_keywords)
    data.append(data_booktitle)

    data = np.moveaxis(data,0,1)
    data = np.reshape(data,[len(x_title),MAX_SEQUENCE_LENGTH*NUMBER_OF_USED_METADATA])

    data = np.array(data)
    
    ### Predict ###
    prediction = []
    
    # Run classifier
    y_pred = model.predict(data)

    # convert output label: one hot encodeing -> integer encoding e.g.: [0, 0, 1] -> 2
    y_pred_encoded = np.argmax(y_pred, -1)
    
    for i in range(len(y_pred_encoded)):
        if y_pred_encoded[i] == 0:
            prediction.append('Crypto')
        else:
            prediction.append('Others')
        
    return prediction
    