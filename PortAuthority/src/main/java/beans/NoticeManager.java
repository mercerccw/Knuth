package beans;

import com.google.gson.Gson;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.jms.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@SessionScoped
@Named("noticeMgr")
public class NoticeManager implements Serializable {

	@PersistenceContext(name="myShips")
	EntityManager em;

	@Resource(mappedName="jms/myLog")
	private Queue logMessages;
	@Resource(mappedName="jms/myMessageFactory")
	private ConnectionFactory logFactory;
	
	private static final long serialVersionUID = 1L;

	private NoticeOfArrival notice = new NoticeOfArrival();
	
	public void setNotice( NoticeOfArrival notice ) {
		this.notice = notice;
	}
	private static String serializeJSON(NoticeOfArrival notice) {
		Gson gson = new Gson();
		return gson.toJson(notice);
	}
	private void sendMessage(String msg){
		try (JMSContext context = logFactory.createContext()){
			JMSProducer mp = context.createProducer();
			Message tm = context.createTextMessage(msg);
			mp.send(logMessages,tm);
		} catch( Exception e) {
			System.out.println("Error: could not send message "+ e);
		}
	}
	public NoticeOfArrival getNotice() { return notice ; }

	private static void createShip(Client clt, NoticeOfArrival notice ){
		System.out.println("createShip(" + serializeJSON(notice) + ")");
		WebTarget resource = clt.target("http://localhost:3000/vessels");
		String feedback = resource.request(MediaType.APPLICATION_JSON).post( Entity.json(notice.toJsonObject()), String.class);
	}
	
	public void mesg() {
		System.out.println("Starting shipClient()");
		Client restClient = ClientBuilder.newClient();
		String msg = serializeJSON(notice);
		createShip(restClient, new NoticeOfArrival("Clayton", 4322343,34));
		sendMessage(msg);
		System.out.println(msg);
	}

}
