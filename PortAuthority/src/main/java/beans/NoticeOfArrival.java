package beans;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.persistence.*;
import java.io.Serializable;

// For JPA persistency
 @Entity
 @Table(name="NoticeOfArrival")
public class NoticeOfArrival implements Serializable {

	private static final long serialVersionUID = 1L;

//	 Uncomment if you want to use this bean as a transport object for JPA (from PortAuthority component)
//	 The extra annotations allow for using an auto-increment key in MySQL (see description of NoticeOfArrival table)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	private String name;
	private Integer mmsi;
	private Integer client;

	public NoticeOfArrival() {}

//	public String toJsonString(){
//		Jsonb jsonb = JsonbBuilder.create();
//		return jsonb.toJson( this );
//	}

	public JsonObject toJsonObject(){
		return Json.createObjectBuilder()
				.add("name", this.name)
				.add("mmsi", this.mmsi)
				.add("client", this.client)
				.build();
	}

	public static NoticeOfArrival create(JsonObject obj){
		NoticeOfArrival notice = new NoticeOfArrival();
		try {
			notice.init(
					obj.getString("name").toString(),
					obj.getInt("mmsi"),
					obj.getInt("client"));
		} catch (Exception e){
			System.err.println(e) ;
			notice = null;
		}
		return notice;
	}

	public NoticeOfArrival(String name, int mmsi, int clt){init(name,mmsi,clt);}

	public void init(String name, int mmsi, int clt){
		this.name = name;
		this.mmsi = mmsi;
		this.client = clt;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Integer getMmsi() {
		return mmsi;
	}

	public void setMmsi(Integer mmsi) {
		this.mmsi = mmsi;
	}

	public Integer getClient() {
		return client;
	}

	public void setClient(Integer client) {
		this.client = client;
	}
}
