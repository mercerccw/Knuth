package beans;

import com.google.gson.Gson;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.jms.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	private String serializeJSON(NoticeOfArrival notice) {
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

	
	public void mesg() {
		String msg = serializeJSON(notice);
		sendMessage(msg);
		System.out.println(msg);
	}

}
