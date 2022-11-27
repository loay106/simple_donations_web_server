# Simple Donations server

The project includes the files neccessary to run a web application server that handles donations using Spring Boot

# Running Example
* Download the zip or clone the Git repository.
* Unzip the zip file (if you downloaded one)
* Open Command Prompt and Change directory (cd) to folder containing pom.xml
* Open Eclipse (or other IDE)
* File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
* Select the right project
* Choose the Spring Boot Application file (search for @DonationsApplication)
* Right Click on the file and Run as Java Application
* You are all Set

The server will run on port 8081 (can be changed on application.properties file).

# REST API:

The server includes the implemenation of the following:

1) (GET) /donations/get/{id}: 
The endpoint receives a donation id (String) as a parameter and returns the donation info in a JSON object.
JSON format of a donation is of the following: 
{
  "id": String
  "amount": Double
  "date": String
}

2) (POST) /donations/add: 
The endpoint recives an amount (Integer) and persists the donation info (above format) to the MongoDB.
The date and id of the documents are auto generated in the server side.

3) (GET) /donations/amount: 
The endpoint receives no parameters and returns the total amount of the current donations in the database.

# Notes

* Data is persisted in an embedded MongoDB
* The server creates a random id for the documents to store in MongoDB but it can be improved by having the MongoDB service auto generate the id field.
