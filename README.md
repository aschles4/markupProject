MarkupProject
=============
Instructions
------------
* Setup
	 - Set up MySql Database 
		- In DBConnect.java on line 12 find the //TODO and update the mysql server information
		- Run Create Table MySql query found in schema CreateTable.txt
		- avgSql query is located in avgSql.txt

* Running From Command Line 
	- Locate The markupProject folder directory
	- CD into the markupProject folder with command line
	- run this command: java  -classpath bin;vendor\jsoup-1.9.2.jar;vendor\mysql-connector-java-5.1.40-bin.jar markup.Main
		- This assumes that java is set to the computers path

* Input and What to expect
	- Application automatically scores all html files in the data folder
		- Output: filename | score
	- Then the user is prompted to enter for:
		- Getting all scores for name("unique id")
			- Output: unique id | score
		- Getting all scores and name for date ranges
			- Beginning (yyyy-mm-dd)
			- Ending (yyyy-mm-dd)
				- Output: unique id | score | date
		- Getting highest score for name("unique id")
			- Output: unique id | score
		- Getting lowest score for name("unique id")
			- Output: unique id | score
			
