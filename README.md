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
The EmailService has been register as recevier for a particular queue and alert will be send once the message reaches the queue.

Refer the EmailService repositary

