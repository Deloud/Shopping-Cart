# Shopping-Cart

Contract Test 수행을 위한 REST API 'Shopping-Cart' 제작 <br>


## install pact
Docker - postgres , pact-broker 설치 및 실행
```
$ docker run -d --name postgres -p  5432:5432 \
-e POSTGRES_USER=oauth -e POSTGRES_PASSWORD=oauth123 -e POSTGRES_DB=oauth postgres

$ docker run -d --name pact-broker --link postgres:postgres -p 9292:9292 \
-e PACT_BROKER_DATABASE_USERNAME=oauth \
-e PACT_BROKER_DATABASE_PASSWORD=oauth123 \
-e PACT_BROKER_DATABASE_HOST=postgres \
-e PACT_BROKER_DATABASE_NAME=oauth pactfoundation/pact-broker
d9f90ea83a58458dd515c542dcc8d0c9c9b7078b01730630c2779a3ca8ec4fa9
```

## process 
1. git clone 
```
git clone https://github.com/Deloud/Shopping-Cart.git
```
2. contract-test branch에서 실행
```
git checkout -t origin/contract-test
```

3. cart consumer contract test 진행 중
```
contract test file 있는 경로 : <br>
shopping-mall-cart > src > test > com > shopping > shoppingcart > CartConsumerContractTest.java
```

