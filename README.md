# Advanced Java Programming

## Guide:

1. javax.net package provides classes and intefaces for network programming.
2. for execute JDBC program we need jar of specific database driver, for mysql db download 'mysql-connector' jar specific to your mysql version and add its path in env. variables as classpath, go to 'advanced system settings' >> env. variable


## Practical Assignments:

1. [Network Programming with Java:](assignment1)
    - To implement reliable client-server communication using TCP Socket API.
Write a java program where client sends a string as a message and sever
counts the characters in the received message from client. Server sends this
value back to the client. Server should be able to serve multiple clients
simultaneously.

    - To implement client-server communication using UDP Socket API.
Write a java program using UDP to transfer a text file from one host to
another.

2. [Java Database Connectivity:](assignment2)
    - To develop JDBC application:
        1. Create a table in database named Books (BookTitle, Authorname,
        Publisher, Price).
        2. Enter 20 records in this table.
        3. Search and update the price of a given book.
        4. Delete the record of a given book.
