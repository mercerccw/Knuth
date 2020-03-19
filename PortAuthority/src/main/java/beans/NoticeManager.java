package beans;

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
	
	
	public NoticeOfArrival getNotice() { return notice ; }

	
	public void mesg() { System.out.println("NoticeManager: mesg()");}

}
