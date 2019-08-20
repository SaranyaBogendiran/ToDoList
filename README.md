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
| Edureka Naming server |      |

Zipkin	

Zuul	

MYSQL	

Cloud Config	

Hystrix	





