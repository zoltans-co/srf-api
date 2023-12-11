# Investonom docker-local dokumentation

### MySQL only

#### start
````bash
docker-compose -f srf-api-local-mysql-only-docker-compose.yml up -d
````

#### stop
````bash
docker-compose -f srf-api-local-mysql-only-docker-compose.yml down
````

### API

#### start
````bash
docker-compose -f srf-api-local-docker-compose.yml up -d
````

#### stop
````bash
docker-compose -f srf-api-local-docker-compose.yml down
````