package javaships;

import javax.json.JsonObject;
import java.lang.StringBuffer;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonValue;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonNumber;
import java.io.StringWriter;
import javax.json.JsonWriter;


public class Ship {

	private String name ;
	private int mmsi;
	private int client;
		
	public Ship(){};

	public Ship(String name, int mmsi, int clt){
		init(name, mmsi, clt);
	}


	public static Ship createShip(String repr){
		JsonValue jsonVal = Json.createReader( new StringReader( repr )).read();
		JsonObject obj = (JsonObject) jsonVal;
		return createShip(obj);
	}

	public static Ship createShip(JsonObject obj){
		JsonString nameObj = (JsonString) obj.get("name");
		int mmsi = new Integer(((JsonNumber) obj.get("mmsi")).toString()).intValue();	
		int client = new Integer(((JsonNumber) obj.get("client")).toString()).intValue();	
		return new Ship(nameObj.toString(), mmsi, client);
	}

	public String toJsonString(){
		StringWriter sw = new StringWriter();
		JsonWriter jw = Json.createWriter( sw );
		jw.writeObject( toJsonObject() );
		jw.close();
		return sw.toString();
	}

	public JsonObject toJsonObject(){
		JsonObject model = Json.createObjectBuilder()
			.add("name", name)
			.add("mmsi", mmsi)
			.add("client", client)
			.build();
		return model;
	}

	private void init(String name, int mmsi, int clt){
		this.name = name; 
		this.mmsi = mmsi;
		this.client = clt;
	}

}
