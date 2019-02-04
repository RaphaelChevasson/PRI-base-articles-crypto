# PRI-base-articles-crypto
Automatic generation and update of a list of post-quantum cryptography scientific publications

A Télécom Saint-Etienne project by Ahmed Khadraoui, Baptiste Wolff, Bianrafat Hassan, Darios Djimado and Raphael Chevasson  
Client: Pierre-Louis Cayrel  
Tutor: Christophe Gravier  

# Features

This application aims to retrieve, update automatically and classify the scientific publications of cryptography in a database.  
It also provide a web interface to browse and manage the publications.

***< post a few scrennshots (at worst link to our presentation)>***

# Installation

## EITHER from binaries

- Install a MySQL server (we suggest [MySQL Workbench](https://dev.mysql.com/downloads/workbench/))
- Download the project folder from [here](https://drive.google.com/drive/folders/15UIyvpS-Parsxls8j62edEfAjuADWNxr?usp=sharing)
- Follow the setup steps in [the classification readme](Classification/readme.md)
- Open a Cmd or a Terminal, cd into the folder and run : `java -jar server.jar` to launch the services

## OR from source

Clone this repository:

```
git clone https://github.com/RaphaelChevasson/PRI-base-articles-crypto.git
```

### BackEnd :

First, you need to install and run a MySQL server (we suggest [MySQL Workbench](https://dev.mysql.com/downloads/workbench/)).

Then, all you have to do is to open the maven project named as server on your IDE and run ServerApplication.java as a springboot application.

Finally on your browser go to : http://localhost:8080/

### FrontEnd :

in the front folder, open a Cmd and install dependencies :
```
npm install
```
and the you start the Angular devserver :
```
npm start
```
Finally on your browser go to : http://localhost:4200/

### Setup Extraction

You have to go to : http://localhost:4200/admin
Then to start the extraction from the source that you want, you click on the start extraction button like in the picture down bellow :
***< Inserer image>***
If you want to see the total number of the extracted posts, you will have to refresh the page.
then to see all the posts extracted you have to go to : http://localhost:4200/posts


### Setup Classification

Follow the setup steps in [the classification readme](Classification/readme.md)

# Usage

## Launch the Extraction-Classification-Storage pipeline

Launch `launch_pipeline.sh` on a terminal. If you are on windows, lauch it using [git bash](https://gitforwindows.org/)

## Browse the list

## Use the administration interface

Go to : http://localhost:4200/admin/edit 
You will have the list of all the posts and you can edit or delete them.

# How it works

Here is a little diagram showing essential bricks of the project, the main technology they use, and how they connect together:
***< a little diagram of all the bricks, how they connect and what techno they use. Should be usefull for the presentation too>***

# Inquiries

Feel free to contact us via github!
