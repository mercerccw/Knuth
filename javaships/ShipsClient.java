package javaships;


import javax.ws.rs.client.Client; 
import javax.ws.rs.client.ClientBuilder; 
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.Entity;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;

import javax.json.JsonValue;
import javax.json.JsonValue.*;
import javax.json.JsonArray;
import javax.json.JsonObject;

public class ShipsClient {

	

	public static void main( String[] args){
	
		System.out.println("Starting shipClient()");

		Client restClient = ClientBuilder.newClient();		

		getAllShips( restClient );

		deleteShip( restClient, 2);

		createShip( restClient, new Ship("Redoutable", 123456789, 25));

	}

	private static void getAllShips(Client clt){
		
		System.out.println("getAllShips()");
		String ships = clt.target("http://localhost:3000/vessels")
    	    .request(MediaType.APPLICATION_JSON)
        	.get(String.class);
		
		extractShips( ships );
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

	private static void extractShips( String jsonString ){
		JsonReader reader = Json.createReader( new StringReader( jsonString) 	);
		
		JsonValue jsonModel = reader.read();

		ValueType type = jsonModel.getValueType();

		if (type == ValueType.ARRAY){
			JsonArray shipArray = (JsonArray) jsonModel;
			for(JsonValue shipRepr: shipArray){

				JsonObject shipObj = (JsonObject) shipRepr;
				Ship ship = Ship.createShip( shipObj );

				System.out.println( ship.toJsonString());
			}
			System.out.println("Array!");
		}
	}

}
