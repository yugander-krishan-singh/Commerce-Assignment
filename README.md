# Commerce-Assignment

## Intro: 
We have made use of **spring-boot**, **jdk11**, **JPA** and **Hibernate** for creating this application.

### Data-Modelling:  
The application consists of 3 entities named **items**, **orders** and **order_details**. There is **one to many relationship** between order and order deatils. orderNo is the foreign key inside order_details table.

## Steps to Run the application: 

### Prerequisites: Jdk 11, Mysql

### Steps:

1. Have a Mysql DB locally or have it's docker container running .
2. Once mysql server is running then replace the ip of mysql server in the application.properties file `spring.datasource.url== jdbc:mysql://9.202.178.127:3306/ecommerce?allowPublicKeyRetrieval=true&useSSL=false`
3. Now run the comand `./gradlew build && java -jar PATH_TO/build/libs/ecommerce-0.0.1-SNAPSHOT.jar`
4. Now the spring boot's embedded servlet container will start in few seconds and you can access the app using `IP:8080`
5. Open postman and can use the following endpoints:

### ITEMS:

**_CREATE_**: Http Method: POST , URL: `http://localhost:8080/api/items` , BODY: 

```
{
	"itemName":"Item2",
	"availableQuantity":300
}

```

_**GET All Items**_: Http Method: GET, URL: `http://localhost:8080/api/items`

_**GET An Item**_: Http Method: GET, URL: `http://localhost:8080/api/items/{item-id}`

_**Update An Item**_: Http Method: PUT, URL: `http://localhost:8080/api/items/{item-id}`, BODY: 
```

{
	"itemName":"item2",
	"availableQuantity":1500
}

```

_**Delete An Item**_: Http Method: DELETE, URL: `http://localhost:8080/api/items/{item-id}`


### Orders:

_**CREATE(Single order/Bulk Order)**_: Http Method: POST , URL: `http://localhost:8080/api/orders` , BODY: 

```

{
	"email":"xyz@gmail.com",
	"orderDetails":[{
		
		"itemId":1,
		"quantityOrdered":50
		
	},{
		"itemId":3,
		"quantityOrdered":50
	}]
}		
```

_**Get All Orders**_: Http Method: GET, URL: `http://localhost:8080/api/orders`

_**Get An Order**_: Http Method: GET, URL: `http://localhost:8080/api/orders/{order-id}`



### TODO: 
- one to one relationship between order_details and item.
- Transaction support while updating the table
- Better error handling
- Delete the order api
- Dockerize the application

