# Knuth
## 'Donald Knuth' Team Repository

__There are two sections to the Final Project__
1. Jave EE application
2. JavaScript application

### Setup Instructions
__Java EE application SoftHaven setup__
1. Download the Glassfish 4.1 installation from https://javaee.github.io/glassfish/download
2. Download the MySQL Connector J 8.0.19 from https://dev.mysql.com/downloads/connector/j/
3. Place the `mysql-connector-java-8.0.19.jar` file into the `glassfish4/glassfish/lib/` folder
4. Start Glassfish with the `glassfish4/bin/asadmin.bat` untility using the command `start-domain` (`stop-domain` to stop the server)
5. Navigate in your web browser to http://localhost:4848 
6. Select the option in the navigation bar on the left side under __Resources__ that says __JDBC__
7. Under JDBC Connection Pools, create a new pool
8. Enter the information provided below and save<br>
![JDBC Pool Page](1.png)<br>
![JDBC Pool Settings](2.png)<br>
9. Under __Resources__ that says __JDBC__ in the navigation bar select JDBC Resources
10. Create a new Resources with the information provided below and save<br>
![JDBC Resources Page](3.png)<br>
11. Clone the repo
        (The Java side is inside the __SoftHaven__ folder.)
12. Create a folder in the `src/main/java/com/sofhaven/` called `config`.
13. Create a file in that directory call Database.java and paste in the code from the `src/main/resources/com/softhaven/config/Database_PLACEHOLDER.java.txt`.
14. Change the username and password variables to match your own MySQL credentials. 
15. Login to your MySQL server and import the `softhaven.sql` located in the repository root directory
16. Cd into the `Softhaven` directory 
17. Run `mvn package` (Make sure you have Maven CLI installed on your machine for this!!!)
18. A target folder should appear
19. Inside that folder there should be a `SoftHaven.war` file
20. Go back to http://localhost:4848/ and click __Home__ in the top left corner of the screen
21. Select __Deploy and Aplication__
22. Drag or select from the file explorer your `SoftHaven.war` file and click __OK__
23. Wait a second or two and a list of Deployed Applications should appear with Softhaven present
24. Click __Launch__
25. Click the http:// link
26. Now you're all done! Enjoy using SoftHaven!


__Node and React Traffic Monitor setup__
1. Download the Node 12.16.2 LTS version from https://nodejs.org/en/download/
2. Download Mongo 4.2 by following the official steps from https://docs.mongodb.com/manual/installation/
3. Insert Mongo bin folder directory into Path environment variables for command line execution.<br>
    These windows are provided in the image below for a Windows installation.<br>
![image](MongoPath.png)
5. Open up the terminal and type `mongo` to log into the Mongo database server.
6. While in Mongo, execute `use TrafficManager` to create the database.
7. Continuing in Mongo, execute `db.createCollection("aisMessage")`  to create the aisMessage collection.
8. Execute `use DenmarkTraffic` to create the database for the `ais_transmitter`.
9. Lastly in Mongo, execute `db.createCollection("aisdk_201809")`  for the `ais_transmitter`.
10. Clone the repository at https://github.com/nicolasrenet/Knuth.git.
   (Both the `TrafficService` and the `trafficmonitor` folders will be used for this application.
11. Ensure that `ais_transmitter` is installed in the same directory as the `Knuth` directory.
12. cd into the `ais_transmitter` directory, run `npm install` to download any necessary dependencies.
13. cd into the `trafficmonitor` directory, run `npm install` to download any necessary dependencies.
14. Lastly, cd in the `TrafficService` directory and run `npm install` to download any necessary dependencies.
15. Open up a new terminal and run `mongod` to start the MongoDB server.
16. Return to the the previous terminal, cd to the `TrafficService` directory, and run `npm start` to start the backend server.
17. Open a new terminal and cd into the `ais_transmitter` directory, and run `node index.js` to start the application.
17. Open a new terminal an cd into the `trafficmonitor` directory, and run `npm start` to start the application.<br>
    (If prompted about running the application on another port, type in "y").
18. Go to the URL listed by the `trafficmonitor` program (which should be http://localhost:3001).<br>
    The final application should look similar to the image below.
![Traffic Monitor](TrafficMonitor.png)
19. Now you're all complete! Enjoy using Traffic Monitor!


### Project Structure
__AIS Transmitter__<br>
* _Purpose_: Program used to simulate the uploading of vessel AIS messages.
* _Location_: Contained within the `ais_transmitter` directory.
* _Application Uses_: Information used by the JavaScript application.
__Softhaven__<br>
* _Purpose_: Program used by ship masters, ship agents, customs officers to view vessel information and arrival/departure forms.
* _Location_: Contained within the `Softhaven` directory.
* _Application Uses_: Used within the JavaEE application.
__Traffic Monitor__<br>
* _Purpose_: Frontend application to view AIS messages through a map GUI and showcases related statistics.
* _Location_: Contained within the `trafficmonitor` directory.
* _Application Uses_: Used within the JavaScript application.
__TrafficService__<br>
* _Purpose_: Backend REST service used by anyone to interact with any data contained within recent AIS messages.
* _Location_: Contained within the `TrafficService` directory.
* _Appliction Uses_: Used within the JavaScript application.

### Tech/Framework/Libraries Used
__JavaScript application uses:__

