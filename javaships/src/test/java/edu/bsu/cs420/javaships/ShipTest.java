package edu.bsu.cs420.javaships;

import edu.bsu.cs420.javaships.Ship;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import javax.json.JsonObject;
import java.util.List;
import java.util.ArrayList;

class ShipTest {

	private static Ship shipForAllTests;

	@BeforeAll
	static void setUpClass(){
		shipForAllTests = new Ship( "Fantomas", 987654321, 765);
	}
	
	@Test
	void testCreateShipFromString_Name(){
		String jsonShipStr = "{\"name\": \"Hermione\", \"mmsi\": 123456890, \"client\": 123}";
		Ship ship = Ship.create( jsonShipStr );
		assertEquals( "Hermione", ship.getName());
	}

	@Test
	void testCreateShipFromString_Mmsi(){
		String jsonShipStr = "{\"name\": \"Hermione\", \"mmsi\": 123456890, \"client\": 123}";
		Ship ship = Ship.create( jsonShipStr );
		assertEquals(123456890, ship.getMmsi());
	}

	@Test
	void testCreateShipFromString_Client(){
		String jsonShipStr = "{\"name\": \"Hermione\", \"mmsi\": 123456890, \"client\": 123}";
		Ship ship = Ship.create( jsonShipStr );
		assertEquals(123, ship.getClient());
	}

	/**
	 * Check that integers passed as strings are interpreted as integer
	 */
	@Test
	void testCreateShipFromString_not_null(){
		String jsonShipStr = "{\"name\": \"Hermione\", \"mmsi\": 123456890, \"client\": \"123\"}";
		Ship ship = Ship.create( jsonShipStr );
		assertNotEquals(null, ship);
		assertEquals(123, ship.getClient());
	}

	@Test
	void testToJsonObject_Name(){
		JsonObject obj = shipForAllTests.toJsonObject();
		assertEquals("Fantomas", obj.getString("name"));
	}

	@Test
	void testToJsonObject_Mmsi(){
		JsonObject obj = shipForAllTests.toJsonObject();
		assertEquals(987654321, obj.getInt("mmsi"));
	}

	@Test
	void testToJsonObject_Client(){
		JsonObject obj = shipForAllTests.toJsonObject();
		assertEquals(3, obj.size());
	}

	@Test
	void testShipCollectionFromString_Size(){
		String jsonShipStr = "[{\"name\": \"Hermione\", \"mmsi\": 123456890, \"client\": 123},";
		jsonShipStr += "{\"name\": \"Sirius\", \"mmsi\": 908321747, \"client\": 124},";
		jsonShipStr += "{\"name\": \"Poseidon\", \"mmsi\": 120391821, \"client\": 125}]";

		List<Ship> shipCollection = Ship.getShips( jsonShipStr );
		assertEquals( 3, shipCollection.size());
	}

	@Test
	void testShipCollectionFromString_Content(){
		String jsonShipStr = "[{\"name\": \"Hermione\", \"mmsi\": 123456890, \"client\": 123},";
		jsonShipStr += "{\"name\": \"Sirius\", \"mmsi\": 908321747, \"client\": 124},";
		jsonShipStr += "{\"name\": \"Poseidon\", \"mmsi\": 120391821, \"client\": 125}]";

		List<Ship> shipCollection = Ship.getShips( jsonShipStr );
		assertEquals( "Hermione", shipCollection.get(0).getName());
	}

	@Test
	void testShipCollectionFromString_ShipAsList(){
		String jsonShipStr = "{\"name\": \"Hermione\", \"mmsi\": 123456890, \"client\": 123}";

		List<Ship> shipCollection = Ship.getShips( jsonShipStr );
		assertEquals( "Hermione", shipCollection.get(0).getName());
	}


}
