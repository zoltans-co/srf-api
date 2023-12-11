# Investonom docker-prod dokumentation

### HIPER FONTOS -> Build and push the api image to docker hub
#### Build and push in one command
````bash
docker buildx build --push --platform linux/amd64 -t zoltansco/statistiqo:statistiqo-api-2023.1 -f Dockerfile .
````

### Bash test
````bash
tldr man 
````

### MySQL only

#### start
````bash
docker-compose -f srf-api-prod-mysql-only-docker-compose.yml up -d
````

#### stop
````bash
docker-compose -f srf-api-prod-mysql-only-docker-compose.yml down
````

### API

#### start
````bash
docker-compose -f srf-api-prod-docker-compose.yml up -d
````

#### stop
````bash
docker-compose -f srf-api-prod-docker-compose.yml down
````



#### TEMP
````bash
docker buildx build -t zoltansco/statistiqo-api:4.0 .
````

````bash
docker build -t zoltansco/statistiqo-api:4.0 .
````

````bash
docker push zoltansco/statistiqo-api:4.0
````

````bash

````