Hello World sample shows how to deploy [Carrito-Compra](https://gitlab.com/softdevelop/carrito-compra/) RESTful web service application with [Docker](https://www.docker.com/)

#### Prerequisite

Installed:   
[Docker](https://www.docker.com/)   
[git](https://www.digitalocean.com/community/tutorials/how-to-contribute-to-open-source-getting-started-with-git)    
[Docker-Compose](https://docs.docker.com/compose/install/)   
[Java 1.8](https://www.oracle.com/technetwork/java/javase/overview/index.html)   
[Maven 3.x](https://maven.apache.org/install.html)

#### Steps

##### Clone source code from git
```
$  git clone https://gitlab.com/softdevelop/carrito-compra.git .
```

##### Build Maven project
```
If running enviroment is Docker
    Replace application.properties database connection line with  spring.datasource.url=jdbc:postgresql://demo-postgres:5432/tiendacarrito
Else
    Replace application.properties database connection line with  spring.datasource.url=jdbc:postgresql://localhost:5432/tiendacarrito    

$ mvn clean install .
```

##### Run SpringBoot project with Maven
```
$ mvn sprintboot:run .
```

##### Test application

```
$ curl http://localhost:8080/actuator/health
```

the respone should be:
```
{"status":"UP","components":{"db":{"status":"UP","details":{"database":"PostgreSQL","validationQuery":"isValid()"}},"diskSpace":{"status":"UP","details":{"total":244549410816,"free":18844389376,"threshold":10485760,"exists":true}},"ping":{"status":"UP"}}}
```

## Run with docker-compose 

Build and start the container by running 

```
$ docker-compose up
```

##### Test application with ***curl*** command
```
$ curl http://localhost:8080/actuator/health
```

the respone should be:
```
{"status":"UP","components":{"db":{"status":"UP","details":{"database":"PostgreSQL","validationQuery":"isValid()"}},"diskSpace":{"status":"UP","details":{"total":244549410816,"free":18844389376,"threshold":10485760,"exists":true}},"ping":{"status":"UP"}}}
```

##### Stop Docker Container:
```
docker-compose down
```

##### RxJava API description (getAllSalesByUserId):
```
In this Api the user get All sales for clientId in path param:
request: api/sales/{userId}
response: [
              {
                  "id": 1,
                  "date": null,
                  "clientId": 1,
                  "clientName": "Yosley"
              }
          ]
```
