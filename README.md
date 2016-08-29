# A simple K-Nearest Neighbor (KNN) Java library  #

### What is this repository for? ###

* Its a very simple implementation of K-Nearest Neighbor algorithm for Supervide Learning (user labeled data)
* __Version 1.0__

### Features presents ###

* The following similarities metrics are presents:
	- Euclidian Distance
	- Jaccard Distance
	- Pearson Correlation
	- Cosine Distance
	- Minkowski Distance (to be done)
	- Manhattan Distance (to be done)
	- Mahalanobis Distance (to be done)
* A naive knn implementation with (or without) k-fold cross-validation.

### How to use ###

* Just clone the project
* Setup your project
* Init a ``SimpleKNNClassifier``
* Inject the ``SimilarityCalculator`` of your choice into ``SimpleKNNClassifier`` instance
* Fit with some labeled data
* Train the classifier (you can choose if you want to train using k-fold cross validation or not)
* Pass some data to ``predict()`` method and see the label predicted