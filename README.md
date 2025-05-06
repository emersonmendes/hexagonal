## Hexagonal Project for study :)


#### Docs

    Archunit:
    https://www.archunit.org/userguide/html/000_Index.html#_introduction


#### Wiremock

``` bash
    
    cd ./wiremock
    
    java -jar wiremock-standalone-4.0.0-beta.2.jar --port 8082

```

#### Docker compose

``` bash

    cd docker-local
    
    docker compose up

```

#### Publish Kafka

``` bash

cd docker-local

echo '{"name":"Emerson Mendes","address":{"cep":"88134421","street":"Rua das Amoras", "number":"777","city":"Florianópolis"}}' \
  | docker compose exec -T hex-kafka kafka-console-producer --broker-list hex-kafka:9092 --topic customers
      
```