# Classification setup

- If you are installing via sources, download Dict2Vec (already done if you are installing via binaries):
	- Download `dict2vec-vectors-dim100.vec` from  https://github.com/tca19/dict2vec (go to section download pre-trained vector and click on the link to download the dimension 100 word embedding)
	- Put `dict2vec-vectors-dim100.vec` in the `Classification/data/dict2vec` directory
	
- Download and install [Anaconda Navigator](https://www.anaconda.com/distribution/) (the python 3 version)

- Install keras :	
 	- on CPU :
		- install keras on Anaconda
	- OR on GPU (nvidia):
		- install [cudnn](https://developer.nvidia.com/cudnn)
		- install keras-GPU on Anaconda

- Installer sklearn :
	- On the anaconda prompt :
		- conda install scikit-learn

- Installer Adversary :
	- On the anaconda prompt :
		- pip install Adversary
		- python -m textblob.download_corpora
		
- Install bibtexparser (needed for training only) :
	- On the anaconda prompt :
		- pip install bibtexparser

# Usage

In production, the classification is used via the `launch_pipeline.sh` script (in the repo root folder). You can also:

## Redo the classification training

Use the `CassificationTraining.ipynb` jupyter notebook.  
The trained model is saved in the file `model.h5`, and the `Tokenizers` are also saved  

## Get the prediction results directly in python

To use the trained CNN, use the function predict in the file `Predict.py`.  
There is an example on how to use it in the `PredictExample.ipynb` notebook.
