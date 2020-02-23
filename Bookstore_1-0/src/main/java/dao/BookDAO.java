package dao;
import java.util.List;
import beans.Book;

public interface BookDAO {
	
	Book find( int it);
	void add(int it, String title, String desc, String author, String price);
	void modify(int id, String title, String desc, String author, String price);
	void delete(int id);
	
	List<Book> list();
	List<Book> listByIdAsc();
	List<Book> listByIdDesc();
	List<Book> listByTitleAsc();
	List<Book> listByTitleDesc();
	List<Book> listByPriceAsc();
	List<Book> listByPriceDesc();
	List<Book> listByAuthorAsc();
	List<Book> listByAuthorDesc();
	
}
