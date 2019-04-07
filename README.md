# adidasChallenge

# Technologies

* Java 8
* H2 as a relational DataBases
* Spring Boot
* Docker
* Maven

# Usage

Clone git repository from: https://github.com/emolinero/adidasChallenge

A folder structure representeing each module:
* subscriptionService: Main Service, receiving subscription requests and acting according to them for incoming events
* emailService: Service for email sending
* eventService: Service for Event creation and notification
* jmsService: helper moule to have a local jms broker

will be obtained

each of the projects represents a part of the exercise. With jmsService as a helper project to instantiate a broker for messaging between the modules.

To run each project run

```
	emailService/mvn spring-boot:run
	eventService/mvn spring-boot:run
	subscriptionService/mv spring-boot:run
	jmsService/mvn spring-boot:run
```

Authentication for Subscription Service

http://localhost:8080/login?username=user&password=123456

Subscription Creation

POST to

localhost:8080/subscriptions?Authorization Bearer=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTU1NTE0Njk0fQ.e-91FAiqp-4AGtpCD4cY-ghKVyP7qyrm4sjuiTare551Zoojq4TKJLyFFAg8o4cE359TdZr-tNrLOX-lr-zUxQ

with body

{
	"email": "prueba@prueba.com",
	"firstName": "Prueba",
	"gender": "M",
	"dateOfBirth": "2019-04-02T18:28:34.273Z",
	"consent": true,
	"newsletterId": "1"
}

Event Creation

POST to

localhost:8090/event

with body

```{ "description": "Campaign Newsletter 1", "newsletterId": "1" }```

# Docker

Create base image
```
sudo docker build --tag=alpine-java:base --rm=true .
```
Create Volumen
```
sudo docker volume create --name=adidasChallenge
```

Build each module jar
```
mvn package spring-boot:repackage
```

Create each service image
```cd jmsService
sudo docker build --file=Dockerfile.jmsService --tag=config-server:latest --rm=true .
```
Register the module in the local machine
```
docker run --name=jmsService --publish=61616:61616 --volume=spring-cloud-config-repo:/var/lib/spring-cloud/config-repo config-server:latest
```

Run the image with port redirection
```
sudo docker run --publish=8080:8080 config-server:latest
```
