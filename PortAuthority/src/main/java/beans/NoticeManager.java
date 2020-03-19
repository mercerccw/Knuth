package beans;

import com.google.gson.Gson;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named("noticeMgr")
public class NoticeManager implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private NoticeOfArrival notice = new NoticeOfArrival();
	
	public void setNotice( NoticeOfArrival notice ) {
		this.notice = notice;
	}
	private String serializeJSON(NoticeOfArrival notice) {
		Gson gson = new Gson();
		return gson.toJson(notice);
	}
	
	public NoticeOfArrival getNotice() { return notice ; }

	
	public void mesg() { System.out.println(serializeJSON(notice));}

}
