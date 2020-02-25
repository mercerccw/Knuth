package dao.jsondao;

import beans.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.oracle.javafx.jmx.json.JSONException;
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
import java.util.ArrayList;
import java.util.Collections;
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

    void writeToTargetFile() {
        try {
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            File path = new File(getJSONRoot() + "WEB-INF" + File.separator + "classes" + File.separator + "books.json");
            writer.writeValue(path, bookList);
        } catch (IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private String getJSONRoot() throws ParserConfigurationException {
        File path = new File(System.getProperty("com.sun.aas.instanceRoot") + "/config/domain.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        dBuilder = dbFactory.newDocumentBuilder();
        try {
            assert dBuilder != null;
            Document doc = dBuilder.parse(path);
            doc.getDocumentElement().normalize();
            NodeList appList = doc.getElementsByTagName("application");
            for (int temp = 0; temp < appList.getLength(); temp++) {
                Node node = appList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    if (eElement.getAttribute("name").equals("Bookstore_1-0-0.0.1-SNAPSHOT")) {
                        String fileLocation = eElement.getAttribute("location");
                        fileLocation = fileLocation.replace("file:/", "");
                        fileLocation = fileLocation.replaceAll("%20", " ");
                        return fileLocation;
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
        writeToTargetFile();
    }

    public void modify(int id, String title, String desc, String author, String price) {

        Book book = new Book(id, title, desc, author, Double.parseDouble(price));
        Book oldBook = find(id);

        int index = bookList.indexOf(oldBook);

        bookList.set(index, book);
        writeToTargetFile();
    }

    public void delete(int id) {

        Book book = find(id);

        bookList.remove(book);
        writeToTargetFile();
    }

    public List<Book> list() {

        return bookList;
    }


    /*The following are methods that have taken inspiration from the BookDAOJDBCImpl.java file
    The major difficulty with this is that the webpage doesn't want to change the JSON local file.
    Furthermore, the pages within this
     */

    public List<Book> listByIdAsc() {
        return bookList;
        /*
        System.out.println(bookList);
        bookList.sort((o1, o2) -> {
            int comparator = 0;
            try {
                int keyA = o1.getId();
                int keyB = o2.getId();
                comparator = Integer.compare(keyA, keyB);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return comparator;
        });
        List<Book> newBookList = new ArrayList<Book>();
        for (int i = 0; i < bookList.size(); i++) {
            newBookList.add(bookList.get(i));
        }
        return newBookList;
         */
    }

    public List<Book> listByIdDesc() {
        return bookList;
        /*
        System.out.println(bookList);
        Collections.sort(bookList, (o1, o2) -> {
            int comparator = 0;
            try {
                int keyA = o1.getId();
                int keyB = o2.getId();
                comparator = Integer.compare(keyA, keyB);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return comparator;
        });
        List<Book> newBookList = new ArrayList<Book>();
        for (int i = bookList.size(); i > 0; i--) {
            newBookList.add(bookList.get(i));
        }
        return newBookList;
         */
    }

    public List<Book> listByTitleAsc() {
        return bookList;
    }

    public List<Book> listByTitleDesc() {

        return bookList;
    }

    public List<Book> listByPriceAsc() {

        return bookList;
    }

    public List<Book> listByPriceDesc() {

        return bookList;
    }

    public List<Book> listByAuthorAsc() {

        return bookList;
    }

    public List<Book> listByAuthorDesc() {

        return bookList;
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
