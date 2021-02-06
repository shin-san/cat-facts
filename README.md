# cat-facts

This service collects API data from https://cat-fact.herokuapp.com/ and returns a response of each user details and all of its corresponding votes.

# Local
## Build
### Maven
    mvn clean package

## Run
Run the service using maven spring-boot plugin

    mvn spring-boot:run

# Docker
## Build
### Prerequisites
Local Docker instance must be installed
The Dockerfile also relies on build-args

Note: the following container images have been tested in Raspberry PI
Note: `sudo` might also be required if it is being installed in unix OS
#### Local
    docker build \
    --build-arg MYAPP_IMAGE=openjdk:15-jdk-slim \
    --build-arg MAVEN_BUILD=maven:3.6.3-openjdk-15-slim \
    -t cat-facts
#### Remote
    docker build https://github.com/shin-san/cat-facts.git \
    --build-arg MYAPP_IMAGE=openjdk:15-jdk-slim \
    --build-arg MAVEN_BUILD=maven:3.6.3-openjdk-15-slim \
    -t cat-facts
    
## Run
    docker run -p 8080:8080 cat-facts

# REST API
The REST API to the example app is described below.
## Get list of User Details
### Request
`GET /api/v1/getUsers`

    curl -k -v http://localhost:8080/api/v1/getUsers
### Response
    HTTP/1.1 200
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Sat, 06 Feb 2021 10:39:58 GMT
    
    [
      {
          "name": {
              "first": "Kasimir",
              "last": "Schulz"
          },
          "votes": 1
      },
      {
          "name": {
              "first": "Alex",
              "last": "Wohlbruck"
          },
          "votes": 2
      },
      {
          "name": {
              "first": "Jackson",
              "last": "Sippe"
          },
          "votes": 1
      },
      {
          "name": {
              "first": "Thomas",
              "last": "Lockwood"
          },
          "votes": 1
      }
    ]
