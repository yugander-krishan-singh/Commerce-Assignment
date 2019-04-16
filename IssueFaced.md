# Personal Use Points

To start the mysql Docker container follow [this](https://www.techrepublic.com/article/how-to-deploy-and-use-a-mysql-docker-container/) article

Issue Faced:

1. Errors while trying to Connect to mysql container from local windows spring boot app: go into the mysql container and follow these [steps](https://stackoverflow.com/a/22605418/6407858)


2. Public key related error while connecting to mysql container: Pass **allowPublicKeyRetrieval=true** in application.properties as `spring.datasource.url = jdbc:mysql://9.202.178.127:3306/ecommerce?allowPublicKeyRetrieval=true&useSSL=false
`
3. Bidirectiona relationship recursion - Using [this](https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion) and [this](https://stackoverflow.com/a/49008167/6407858), and [this](https://stackoverflow.com/q/12505141/6407858)

4. Reset all db tables when spring boot is started use [this](https://stackoverflow.com/q/21113154/6407858)

5. Crud resp's save method performs upsert operation(update and insert)
