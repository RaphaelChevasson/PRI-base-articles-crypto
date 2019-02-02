# Classification setup

- Download Dict2Vec :
	- Download `dict2vec-vectors-dim100.vec` from  https://github.com/tca19/dict2vec
	- Put `dict2vec-vectors-dim100.vec` in the `Classification/data/dict2vec` directory
	
- Download and install [Anaconda Navigator](https://www.anaconda.com/distribution/) (the python 3 version)

- Install keras :
	- on GPU (nvidia):
		- install [cudnn](https://developer.nvidia.com/cudnn)
		- install keras-GPU on Anaconda
 	- on CPU :
		- install keras on Anaconda

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
