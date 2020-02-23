package dao;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import beans.Book;


@ApplicationScoped @JDBC
public class BookDAOJDBCImpl implements BookDAO {
	
	@Resource( lookup="jdbc/myDatasource")
	private DataSource dataSource;
	
	public Book find( final int id) {
	
		return withDB( new RunJDBC<Book>() {
			public Book run( Connection con) throws Exception {
				PreparedStatement req = con.prepareStatement(
						"select * from Book where id = ?");
				req.setInt(1,  id);
				ResultSet rs = req.executeQuery();
				if (rs.next()) {
					Book book = new Book();
					book.setId(rs.getInt("id"));
					book.setPrice(rs.getDouble("price"));
					book.setDescription(rs.getString("description"));
					book.setAuthor(rs.getString("author"));
					book.setTitle(rs.getString("title"));
					return book;
				} else {
					return null;
				}
			}
		});
	}
	
	public void add( final int id, final String title, final String desc, final String author, final String price) {
		
		withDB( new RunJDBC<Book>() {
			public Book run( Connection con) throws Exception {
				PreparedStatement req = con.prepareStatement(
						"insert into Book (id, title, description, " +
						"author, price) values (?,?,?,?,?)" );
				req.setInt(1,  id);
				req.setString(2,  title);
				req.setString(3, desc);
				req.setString(4, author);
	
				try {
					req.setBigDecimal(5, new BigDecimal(price));
				} catch ( NumberFormatException e) {
					req.setBigDecimal(5,  new BigDecimal("0.0"));
				}
				int nbLines = req.executeUpdate();
				if (nbLines!=1) {
					throw new BookstoreException("Failed insertion!");
				}
				return null;
			}
		});
	}

	public void modify( final int id, final String title, final String desc, final String author, final String price) {
		
		System.out.println("in Modify()");

		withDB( new RunJDBC<Book>() {
			public Book run( Connection con) throws Exception {
				
				PreparedStatement req = con.prepareStatement(						
						"update Book set title=?, description=?, " +
						"author=?, price=? where id=?");
				req.setString(1,  title);
				req.setString(2,  desc);
				req.setString(3, author);
				
				try {
					req.setBigDecimal(4, new BigDecimal(price));
				} catch ( NumberFormatException e) {
					req.setBigDecimal(4,  new BigDecimal("0.0"));
				}
				req.setInt(5,  id);
				int nbLines = req.executeUpdate();
				System.out.println("in Modify()");
				if (nbLines!=1) {
					System.out.println("Exception during modify");
					throw new BookstoreException("Failed update!");
				}
				return null;
			}
		});
	}
	
	
	public void delete( final int id ) {
		withDB( new RunJDBC<Book> () {
			public Book run( Connection con) throws Exception {
				PreparedStatement req = con.prepareStatement(
						"delete from Book where id = ?"
						);
				req.setInt(1, id);
				int nbLines = req.executeUpdate();
				if (nbLines != 1){
					throw new BookstoreException("Deletion failed");
				}
				return null;
			}
		});
	}
	
	
	static interface RunJDBC <T>{
		T run(Connection con) throws Exception;
	}
	
	private <T> T withDB(RunJDBC<T> runner) {
		Connection con = null;
		T result = null;
		try {
			con = dataSource.getConnection();
			boolean auto = con.getAutoCommit();
			con.setAutoCommit(false);
			result = runner.run(con);
			con.commit();
			con.setAutoCommit(auto);
		} catch (Exception e) {
			System.out.println(e);
			try { con.rollback(); } catch( SQLException ex) {}
		} finally {
			if (con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	
	
	
	
	private class BookstoreException extends Exception {
			/**
		 * 
		 */
		private static final long serialVersionUID = 7418836201378164160L;

			public BookstoreException( String mess) {
				super( mess) ;
			}
	}


	public List<Book> list() {
		return list(null);
	}

	public List<Book> listByIdAsc() {
		return list( "id ASC");
	}

	public List<Book> listByIdDesc() {
		return list("id DESC");
	}

	public List<Book> listByTitleAsc() {
		return list("title ASC");
	}

	public List<Book> listByTitleDesc() {
		return list("title DESC");
	}

	public List<Book> listByPriceAsc() {
		return list("price ASC");
	}

	public List<Book> listByPriceDesc() {
		return list("price DESC");
	}

	@Override
	public List<Book> listByAuthorAsc() {
		return list("author ASC");
	}

	@Override
	public List<Book> listByAuthorDesc() {
		return list("author DESC");
	}
	
	private List<Book> list( final String sortkey ){
		return withDB( new RunJDBC<List<Book>>() {
			public List<Book> run (Connection con) throws Exception {
				List<Book> list = new ArrayList<Book>();
				Statement stt = con.createStatement();
				final String req = "select * from Book" + 
				(sortkey!=null? " ORDER BY " + sortkey + ";" : ";");
				ResultSet rs = stt.executeQuery(req);
				while (rs.next()) {
					Book book = new Book();
					book.setId(rs.getInt("id"));
					book.setPrice(rs.getBigDecimal("price").doubleValue());
					book.setAuthor(rs.getString("author"));
					book.setDescription(rs.getString("description"));
					book.setTitle(rs.getString("title"));
					list.add( book );
				}
				
				return list;
			}
		});
	}
}
