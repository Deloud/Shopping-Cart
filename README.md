# Shopping-Cart

MSA 구조에서의 RESTful API - **shopping cart** 장바구니  🚛  <br>

## Setting 

Pact broker 설치

```
$ docker run -d --name postgres -p  5432:5432 \
-e POSTGRES_USER=oauth -e POSTGRES_PASSWORD=oauth123 -e POSTGRES_DB=oauth postgres
```

```
$ docker run -d --name pact-broker --link postgres:postgres -p 9292:9292 \
-e PACT_BROKER_DATABASE_USERNAME=oauth \
-e PACT_BROKER_DATABASE_PASSWORD=oauth123 \
-e PACT_BROKER_DATABASE_HOST=postgres \
-e PACT_BROKER_DATABASE_NAME=oauth pactfoundation/pact-broker
```

## Consumer- Provider

1) consumer : **cart**
2) provider : **product** 

## Test Order 

Consumer - Shopping Cart Make Mockup Test and Pact Publish

1. [Service Build]
/Client
```
$ mvn install
```

2. [Pact publish]
/Client
```
$ mvn pact:publish
```





