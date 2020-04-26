# Knuth
'Donald Knuth' Team Repository

There are two sections to the Final Project
The...
1. Jave EE application
2. Node and React side

Setup Instructions:
Java EE application SoftHaven setup
1. Download the Glassfish 4.1 installation from https://javaee.github.io/glassfish/download
2. Download the MySQL Connector J 8.0.19 from https://dev.mysql.com/downloads/connector/j/
3. Place the <code>mysql-connector-java-8.0.19.jar</code> file into the <code>glassfish4/glassfish/lib/</code> folder
4. Start Glassfish with the <code>glassfish4/bin/asadmin.bat</code> untility using the command <code>start-domain</code> (<code>stop-domain</code> to stop the server)
5. Navigate in your web browser to http://localhost:4848 
6. Select the option in the navigation bar on the left side under <strong>Resources</strong> that says <strong>JDBC</strong>
7. Under JDBC Connection Pools, create a new pool
8. Enter the information provided below and save
![image 1](readmeImages\1.png)
![image 1](readmeImages\2.png)
9. Under <strong>Resources</strong> that says <strong>JDBC</strong> in the navigation bar select JDBC Resources
10. Create a new Resources with the information provided below and save
![image 1](readmeImages\3.png)
11. Clone the repo
        (The Java side is inside the <strong>SoftHaven</strong> folder.)
12. Create a folder in the <code>src/main/java/com/sofhaven/</code> called <code>config</code>.
13. Create a file in that directory call Database.java and paste in the code from the <code>src/main/resources/com/softhaven/config/Database_PLACEHOLDER.java.txt</code>.
14. Change the username and password variables to match your own MySQL credentials. 
15. Login to your MySQL server and import the <code>softhaven.sql</code> located in the repository root directory
16. Cd into the <code>Softhaven</code> directory 
17. Run <code>mvn package</code> (Make sure you have Maven CLI installed on your machine for this!!!)
18. A target folder should appear
19. Inside that folder there should be a <code>SoftHaven.war</code> file
20. Go back to http://localhost:4848/ and click <strong>Home</strong> in the top left corner of the screen
21. Select <strong>Deploy and Aplication</strong>
22. Drag or select from the file explorer your <code>SoftHaven.war</code> file and click <strong>OK</strong>
23. Wait a second or two and a list of Deployed Applications should appear with Softhaven present
24. Click <strong>Launch</strong>
25. Click the http:// link
26. Now you're all done! Enjoy using SoftHaven!
