# Commerce-Assignment

Issue Faced:

1 Errors while trying to Connect to mysql container from local windows spring boot app: go into the mysql container and follow these [steps](https://stackoverflow.com/a/22605418/6407858)

2. Public key related error while connecting to mysql container: Pass **allowPublicKeyRetrieval=true** in application.properties as `spring.datasource.url = jdbc:mysql://9.202.178.127:3306/ecommerce?allowPublicKeyRetrieval=true&useSSL=false
`
