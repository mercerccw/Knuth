package edu.bsu.cs420.javaships;


import javax.ws.rs.client.Client; 
import javax.ws.rs.client.ClientBuilder; 
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.Entity;

import java.util.List;

/**
 * A simple REST client for the Ship NodeJS RESTful service.
 *
 * This is only a proof-of-concept, that exemplifies the use of 
 * GET, POST, and DELETE in a Java SE client.
 * <ul>
 * <li> this is a plain old Java SE class, that does not need to run
 *      in a container: just make sure to include the RS API on the classpath
 *      (for example, by including the GlassFish lib glassfish-embedded-static-shell.jar).
 * <li> The associated Ship class is responsible for serializing Ship objects to and
 *      deserializing from JSON, using the Java EE JSON API.
 * </ul>
 */
public class ShipsClient {



	public static void main( String[] args){
	
		System.out.println("Starting shipClient()");
		Client restClient = ClientBuilder.newClient();		
		getAllShips( restClient );
		deleteShip( restClient, 2);
		createShip( restClient, new Ship("Redoutable", 123456789, 25));
		getAllShips( restClient );

	}

	private static void getAllShips(Client clt){
		
		System.out.println("getAllShips()");
		String ships = clt.target("http://localhost:3000/vessels")
    	    	.request(MediaType.APPLICATION_JSON)
        	.get(String.class);
		
		List<Ship> sl = Ship.getShips( ships );
		for(Ship s: sl){
			System.out.println( s.toJsonString());
		}
	}

	private static void deleteShip(Client clt, int id){
		System.out.println("deleteShip(" + id + ")");
		WebTarget resource = clt.target("http://localhost:3000/vessels/" + id);
		String feedback = resource.request(MediaType.TEXT_PLAIN).delete(String.class);
	}

	private static void createShip(Client clt, Ship ship ){
		System.out.println("createShip(" + ship.toJsonString() + ")");
		WebTarget resource = clt.target("http://localhost:3000/vessels");
		String feedback = resource.request(MediaType.APPLICATION_JSON).post( Entity.json(ship.toJsonObject()), String.class);
	}
}
