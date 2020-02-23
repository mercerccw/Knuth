package commands;


public class Action {

	private String path;
	private boolean redirect;
	
	public Action( String path, boolean redirect) {
		this.path = path;
		this.redirect = redirect;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public boolean hasRedirect() {
		return this.redirect;
	}
	
	public void setRedirect(boolean redirect) {
		this.redirect = redirect ;
	}
	

}
