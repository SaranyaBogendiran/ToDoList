# ToDoList

The ToDoList Service allow the user to perform the below usecase,

1.Create a User

2.Update the User details

3.Delete the user

4.Get the user.

For each user,the todolist can be created,

1.Create a list item

2.Update the list item

3.delete the List item


The staus change of the List Item will trigger a mail alert to the user.

For which the todolist is configured as Producer in RabbitMQ.

**RabbitMQ:**

TO configure the RabbitMQ, follow the steps in official documentation,

https://www.rabbitmq.com/download.html

1. enable the admin plugin

rabbitmq-plugins enable rabbitmq_management

2. Start the server 

cd C:\Program Files\RabbitMQ Server\rabbitmq_server-3.7.16\sbin>

rabbitmq-server start

3. The RabbitMQ by default will run in local host port 15672

http://localhost:15672/#/

4. Login by using the default user name and password.


The EmailService has been register as recevier for a particular queue and alert will be send once the message reaches the queue.



Refer the EmailService repositary






MYSQL Database is used as a data source. 

The connection string details are,

spring.datasource.url = jdbc:mysql://localhost:3306/todolist

spring.datasource.username = username

spring.datasource.password = password



| Application name | Port No |
| ----------------- | --------|
| netflix-eureka-naming-server |  8761 |
| email-service | 8183 |
| to-do-list |  8081, 8082 |
| Zipkin | 9411 |
| zuul-api-gateway | 8011 |
| MYSQL | 3306 |
| Cloud Config | 8888 |
| Hystrix |   | 

**Zipkin Configuration:**

Logs are fed to rabbitmq and Zipkin listen on the rabbitmq queue.

To have the zipkin up and running, follow the below steps,

1. Make sure RabbitMQ is up.

2. Download the Zipkin jar fom https://zipkin.io/pages/quickstart.html

3. Navigate to the Zipkin jar path in cmd promt and run, 

set RABBIT_URI=amqp://localhost

and then run the zipkin jar.

java -jar zipkin.jar

4. Zipkin can be accessed from http://localhost:9411/zipkin/







