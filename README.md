## Overview

The naces is a backend to get and create order.

## Setup

### Java

Install java 8 or 11

### Database

We have used embedded h2 database. schema [Database Schema](src/main/resources/docs/schema.sql)



## API Documentation 

1.  you can view the swager doumentation after running the application  for the apis on http://localhost:8080/swagger-ui.html#/nace-controller

## Build the application with following commands

1. mvn package

 Command wil perform the following tasks

1. Download all the dependencies
2. Clean the build dir
3. compile the code
4. Executes the test cases like unit test cases, integration test cases etc.
5. Create a new jar file in target dir

## run the application jar by following command
java -jar  ms-naces-service-0.0.1-SNAPSHOT.jar



