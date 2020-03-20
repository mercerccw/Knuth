03/11/2020

This is a Java REST client for the REST NodeJS service provided as a classroom example during Week #8.

Two purposes:

- give a simple, short example of a Java client (file ShipsClient.java)
- give working code examples of how to use the Java EE Json library, aka. javax.json.* (file Ship.java, that transforms a Java Ship object into and from a JSON string)

It is a plain Java SE program, which means that it does not need to run in a container. However, becauses it uses the Java EE JSON library for encoding/decoding the data exchanged with the server, it is still necessary to provide the corresponding runtime library, which is provided with this package.

The project is not an Eclipse project, but a straightforward Maven project. Before running the client, you may verify that everything is in order by running

C:\> mvn test

To run the program, cd in the root folder (where this README resides) and:

C:\> java -cp glassfish-embedded-static-shell.jar:target/classes edu.bsu.cs420.javaships.ShipsClient

Note that the program is meant to run with Java 8. Later versions will cause exceptions (sigh): they are catched somewhere, but cause lengthy warnings.


(Ensure that the REST server is running to handle the requests placed by the client).


NPR
