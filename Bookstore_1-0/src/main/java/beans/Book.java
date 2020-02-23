package beans;

public class Book implements Cloneable {
	
	private String title;
	private String description;
	private double price; 
	private String author;
	private int id;


	public Book( int id, String ttl, String desc, String auth, double prc) {
		this.id = id;
		this.title = ttl;
		this.description = desc;
		this.price=prc;
		this.author=auth;
		
	}
	
	public Book() {}

	public String getTitle() {
		return title;
	}
	
	public void setAuthor( String t) {
		author = t;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setDescription( String t) {
		description = t;
	}

	public String getDescription() {
		return description;
	}
	
	public void setPrice( double p) {
		price = p;
	}

	public double getPrice() {
		return price;
	}
	
	public void setId( int i) {
		this.id = i;
	}

	public int getId() {
		return this.id;
	}
	
	public void setTitle( String t) {
		title = t;
	}
	
	public Book cloneMe() {
		try {
			return (Book) super.clone();
		} catch (CloneNotSupportedException e) {
			return null ;
		}
	}

}
