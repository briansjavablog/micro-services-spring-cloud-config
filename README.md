# Micro Services - Spring Cloud Config 

Clone the repository with 
```
git clone git@github.com:briansjavablog/micro-services-spring-cloud-config.git
```

## Running the Config Service
```
cd config-service
mvn clean package
java -jar target/config-service-0.0.1-SNAPSHOT.jar (starts on port 8888)
```

## Running the Bank Account Service
```
cd bank-account-service
mvn clean package
java -jar target/bank-account-service-0.0.1-SNAPSHOT.jar (starts on port 8080)
```

## Retrieving Properties from Config Service
```
http://localhost:8888/bank-account-service/uat
```

## Retrieving Properties from Config Service
```
http://localhost:8888/bank-account-service/uat
```

## Retrieving Properties from Config Service
You can test the bank account service configuration by creating a new account
```
curl -i -H "Content-Type: application/json" -X POST -d '{"accountId":"B12345","accountName":"Joe Bloggs","accountType":"CURRENT_ACCOUNT","accountBlance":1250.38}' localhost:8080/bank-account
```
