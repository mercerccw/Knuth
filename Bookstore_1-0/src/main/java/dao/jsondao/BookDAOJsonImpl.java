package dao.jsondao;

import beans.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dao.BookDAO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@ApplicationScoped
@JSON
public class BookDAOJsonImpl implements BookDAO {

    private List<Book> bookList;
    private ObjectMapper mapper = new ObjectMapper();

    @Inject
    public BookDAOJsonImpl() throws IOException {

        InputStream stream = getClass().getClassLoader().getResourceAsStream("books.json");
        this.bookList = mapper.readValue(stream, new TypeReference<List<Book>>() {
        });
    }

    void writeToFile() {
        try {
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            File path = new File(getDocRoot() + "WEB-INF" + File.separator + "classes" + File.separator + "books.json");
            writer.writeValue(path, bookList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getDocRoot() {
        File path = new File(System.getProperty("com.sun.aas.instanceRoot") + "/config/domain.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        try {
            assert dBuilder != null;
            Document doc = dBuilder.parse(path);
            doc.getDocumentElement().normalize();
            NodeList appList = doc.getElementsByTagName("application");
            for (int temp = 0; temp < appList.getLength(); temp++) {

                Node nNode = appList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    if (eElement.getAttribute("name").equals("Bookstore_1-0-0.0.1-SNAPSHOT")) {
                        String location = eElement.getAttribute("location");
                        location = location.replace("file:/", "");
                        location = location.replaceAll("%20", " ");
                        return location;
                    }
                }
            }
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Book find(final int id) {

        for (Book currentBook : bookList) {
            if (currentBook.getId() == id) {

                return currentBook;
            }
        }

        return null;
    }

    public void add(int id, String title, String desc, String author, String price) {

        Book book = new Book(id, title, desc, author, Double.parseDouble(price));

        bookList.add(book);
        writeToFile();
    }

    public void modify(int id, String title, String desc, String author, String price) {

        Book book = new Book(id, title, desc, author, Double.parseDouble(price));
        Book oldBook = find(id);

        int index = bookList.indexOf(oldBook);

        bookList.set(index, book);
        writeToFile();
    }

    public void delete(int id) {

        Book book = find(id);

        bookList.remove(book);
        writeToFile();
    }

    public List<Book> list() {

        return bookList;
    }

    public List<Book> listByIdAsc() {

        return list();
    }

    public List<Book> listByIdDesc() {

        return list();
    }

    public List<Book> listByTitleAsc() {

        return list();
    }

    public List<Book> listByTitleDesc() {

        return list();
    }

    public List<Book> listByPriceAsc() {

        return list();
    }

    public List<Book> listByPriceDesc() {

        return list();
    }

    public List<Book> listByAuthorAsc() {

        return list();
    }

    public List<Book> listByAuthorDesc() {

        return list();
    }

    public int bookCount() {

        int count = 0;

        for (Book ignored : bookList) {

            count++;
        }

        return count;
    }

    public double totalPrice() {

        double total = 0;

        for (Book currentBook : bookList) {

            total += currentBook.getPrice();
        }

        return total;
    }

}
