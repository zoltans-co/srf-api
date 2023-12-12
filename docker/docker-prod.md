# Investonom docker-prod dokumentation

### HIPER FONTOS -> Build and push the api image to docker hub
#### Build and push in one command
````bash
docker buildx build --push --platform linux/amd64 -t zoltansco/srf:srf-api-2023.2 -f Dockerfile .
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
docker build -t zoltansco/srf-api:4.0 .
````

````bash
docker push zoltansco/srf-api:4.0
````

````bash

````