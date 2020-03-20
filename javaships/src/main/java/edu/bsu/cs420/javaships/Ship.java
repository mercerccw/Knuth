package edu.bsu.cs420.javaships;

import javax.json.JsonObject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonException;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.JsonValue;
import javax.json.JsonValue.*;
import javax.json.JsonArray;
import java.util.List;
import java.util.ArrayList;
import java.io.StringReader;


public class Ship {

	private String name ;
	private int mmsi;
	private int client;
		
	public Ship(){};

	/**
	 * Construct a new Ship object.
	 *
	 * @param name the ship name
	 * @param mmsi the MMSI (9-digit identifier of the AIS transmitter)
	 * @param client the ship's operator number
	 */
	public Ship(String name, int mmsi, int clt){
		init(name, mmsi, clt);
	}



	public String getName(){ return name ; }
	public int getMmsi(){ return mmsi ; }
	public int getClient(){ return client ; }
	public void setName(String name){this.name=name;}
	public void setMmsi(int mmsi){ this.mmsi=mmsi;}
	public void setClient(int client){this.client=client;}

	/**
	 * Return a Ship object from a JSON object string.
	 *
	 * @param String A JSON object string (f.i. '{"name": "Star", "mmsi": 123456789, "client": 123}')
	 * @return a Ship object, or null if the string is invalid.
	 */
	public static Ship create(String repr){

		Jsonb jsonb = JsonbBuilder.create();
		Ship ship = jsonb.fromJson( repr, Ship.class);
		return ship;
	}

	
	/**
	 * Initializes a Ship object from a Java JSON object:
	 *
	 * @param String A JSON object string (f.i. '{"name": "Star", "mmsi": 123456789, "client": 123}')
	 * @return A Ship object, or null if the Json object's values are invalid.
	 */
	public static Ship create(JsonObject obj){
		Ship ship = new Ship();
		try {
			ship.init( 
				obj.getString("name").toString(),
				obj.getInt("mmsi"),
				obj.getInt("client"));
		} catch (Exception e){
			System.err.println(e) ;
			ship = null;
		} 
		return ship;
	}

	
	/**
	 * Return a JSON string representation of this Ship object.
	 *
	 * @return a JSON string representation of a ship object (f.i.'{"name": "Star", "mmsi": 123456789, "client": 123}')
	 */
	public String toJsonString(){
		Jsonb jsonb = JsonbBuilder.create();
		return jsonb.toJson( this );
	}


	/**
	 * Return a Java JSON object representation of this Ship object.
	 *
	 * @return a Java JSON representation of the ship.
	 */
	public JsonObject toJsonObject(){
		JsonObject model = Json.createObjectBuilder()
			.add("name", name)
			.add("mmsi", mmsi)
			.add("client", client)
			.build();
		return model;
	}

	public void init(String name, int mmsi, int clt){
		this.name = name; 
		this.mmsi = mmsi;
		this.client = clt;
	}


	/**
	 * Return a list of Ship objects from a JSON string.
	 *
	 * @param String A JSON array string (f.i. '[{"name": "Star", "mmsi": 123456789, "client": 123}, { ... }, ... ]'), or a JSON object string representing a single ship.
	 * @return a List of Ship objects, or null if the string is invalid.
	 */
	public static List<Ship> getShips( String repr ){
		List<Ship> sl = new ArrayList<Ship>();
		JsonValue jsonVal = Json.createReader( new StringReader( repr )).read();

		ValueType type = jsonVal.getValueType();
		if (type==ValueType.OBJECT){
			Ship s = create( (JsonObject) jsonVal);
			if (s != null){
				sl.add( s );
			}
		} else if (type==ValueType.ARRAY){
			JsonArray shipArray = (JsonArray) jsonVal;
			for( JsonValue shipVal: shipArray){
				if (shipVal.getValueType()==ValueType.OBJECT){
					Ship s = create( (JsonObject) shipVal );
					if (s != null){
						sl.add( s );
					}
				}
			}
		}
		return sl;
	}
}
