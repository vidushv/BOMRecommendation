# BOMRecommendation

System Requirements
-------------------

1. Java 8 or above
2. MySQL v14.14 or above. Port, username, password can be set/updated in application.properties
3. Maven 3.5
4. Python 3
    Please ensure following dependencies are installed.
    
            Numpy (https://www.numpy.org/), 
            Pickle (https://docs.python.org/3/library/pickle.html), 
            SKLearn (https://scikit-learn.org/stable/), 
            Warning (https://docs.python.org/3/library/warnings.html), 
            Pandas (https://pandas.pydata.org/), 
            Sys (https://docs.python.org/3/library/sys.html), 
            Gensim (https://pypi.org/project/gensim/)

5. Chrome v29, Firefox v66

Installation Guide (Ubuntu 18.04)
---------------------------------

Note - The system is compatible on Windows. The instructions however focus on Ubuntu.

1. Clone this repository.

2. Install MySQL (https://phoenixnap.com/kb/how-to-install-mysql-on-ubuntu-18-04)
   As mentioned before. The port, username, password must be updated in application.properties.
   
3. Repository root has a dump.sql. This should create a database named 'BOM' in mySQL.

4. Download glove (http://nlp.stanford.edu/data/glove.42B.300d.zip). Unzip the file. Copy the unzipped file to $repositoryRoot/machine_learning/

5. Run convert.py (same directory location as above) - to generate glove_model2.txt

6. Move back to repository root. Run mvn clean install to compile and download relevant Java dependencies.

7. To start the application run the main method inside - src/main/java/com/cornell/se/bom/bomRecom/BomRecomApplication.java

8. Access the application from localhost:8080

Continuous Integration
----------------------

This repository is periodically compiled and tested using Jenkins and Maven.

To monitor repository health tunnel into Jenkins VM -

    ssh -L 127.0.0.1:8080:localhost:8080 ms3528@bomrecom.eastus.cloudapp.azure.com

    Jenkins is running on port 8080
