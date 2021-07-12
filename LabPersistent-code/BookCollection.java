/**
 * The given code is provided to assist you to complete the required tasks. But the 
 * given code is often incomplete. You have to read and understand the given code 
 * carefully, before you can apply the code properly. You might need to implement 
 * additional procedures, such as error checking and handling, in order to apply the 
 * code properly.
 */
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.xml.sax.SAXException;

// you need to import some xml libraries

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

// import any standard library if needed

/**
 * A book collection holds 0 or more books in a collection.
 */
public class BookCollection {
	private List<Book> books;

	/**
	 * Creates a new collection with no books by default.
	 */
	public BookCollection() {
		this.books = new ArrayList<Book>();
	}

	/**
	 * Creates a new book collection with the specified list of books pre-defined.
	 *
	 * @param books A books list.
	 */
	public BookCollection(List<Book> books) {
		this.books = books;
	}

	/**
	 * Returns the current list of books stored by this collection.
	 *
	 * @return A (mutable) list of books.
	 */
	public List<Book> getList() {
		return books;
	}

	/**
	 * Sets the list of books in this collection to the specified value.
	 */
	public void setList(List<Book> books) {
		this.books = books;
	}

	/**
	 * A simple human-readable toString implementation. Not particularly useful to
	 * save to disk.
	 *
	 * @return A human-readable string for printing
	 */
	@Override
	public String toString() {
		return this.books.stream().map(book -> " - " + book.display() + "\n").collect(Collectors.joining());
	}

	/**
	 * Saves this collection to the specified "bespoke" file.
	 *
	 * @param file The path to a file.
	 */
	public void saveToBespokeFile(File file) {
		// TODO: Implement this function yourself. The specific hierarchy is up to you,
		// but it must be in a bespoke format and should match the
		// load function.

		try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(file.getPath()))){
			for (Book book : books) {
				String line = book.title + "%" + book.authorName + "%" + book.yearReleased + "%" + book.bookGenre;
				bWriter.write(line);
				bWriter.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Saves this collection to the specified JSON file.
	 *
	 * @param file The path to a file.
	 */
	public void saveToJSONFile(File file) {
		// TODO: Implement this function yourself. The specific hierarchy is up to you,
		// but it must be in a JSON format and should match the load function.

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		try (FileWriter fWriter = new FileWriter(file.getPath())) {
			gson.toJson(books,fWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	/**
	 * Saves this collection to the specified XML file.
	 *
	 * @param file The path to a file.
	 */
	public void saveToXMLFile(File file) {
		// TODO: Implement this function yourself. The specific hierarchy is up to you,
		// but it must be in an XML format and should match the
		// load function.
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			Element rootElement = doc.createElement("books");
			doc.appendChild(rootElement);

			for (Book book : books){
				Element bookElement = doc.createElement("book");
				rootElement.appendChild(bookElement);

				Element title = doc.createElement("title");
				title.appendChild(doc.createTextNode(book.title));
				bookElement.appendChild(title);

				Element authorName = doc.createElement("authorName");
				authorName.appendChild(doc.createTextNode(book.authorName));
				bookElement.appendChild(authorName);

				Element yearReleased = doc.createElement("yearReleased");
				yearReleased.appendChild(doc.createTextNode(Integer.toString(book.yearReleased)));
				bookElement.appendChild(yearReleased);

				Element bookGenre = doc.createElement("bookGenre");
				bookGenre.appendChild(doc.createTextNode(String.valueOf(book.bookGenre)));
				bookElement.appendChild(bookGenre);
			}
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING,"utf-8");
			transformer.setOutputProperty(OutputKeys.INDENT,"yes");

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);
			transformer.transform(source,result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Load a pre-existing book collection from a "bespoke" file.
	 *
	 * @param file The file to load from. This is guaranteed to exist.
	 * @return An initialised book collection.
	 */
	public static BookCollection loadFromBespokeFile(File file) {
		// TODO: Implement this function yourself.
		BookCollection bookCollection = new BookCollection();
		try (BufferedReader bReader = new BufferedReader(new FileReader(file.getPath()))){
			String line;
			while ((line = bReader.readLine()) != null){
				String[] items = line.split("%");
				String title = items[0];
				String authorName = items[1];
				int yearReleased = Integer.parseInt(items[2]);
				BookGenre bGenre = BookGenre.valueOf(items[3]);
				Book book = new Book(title,authorName,yearReleased,bGenre);
				bookCollection.books.add(book);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bookCollection;

	}

	/**
	 * Load a pre-existing book collection from a JSON file.
	 *
	 * @param file The file to load from. This is guaranteed to exist.
	 * @return An initialised book collection.
	 */
	public static BookCollection loadFromJSONFile(File file) {
		// TODO: Implement this function yourself.
		BookCollection bookCollection = new BookCollection();
		Gson gson = new Gson();
		JsonReader jsonReader = null;
		final Type BOOK_LIST_TYPE = new TypeToken<List<Book>>() {}.getType();
		try {
			jsonReader = new JsonReader(new FileReader(file.getPath()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		assert jsonReader != null;
		bookCollection.books = gson.fromJson(jsonReader,BOOK_LIST_TYPE);
		return bookCollection;
	}

	/**
	 * Load a pre-existing book collection from an XML file.
	 *
	 * @param file The file to load from. This is guaranteed to exist.
	 * @return An initialised book collection.
	 */
	public static BookCollection loadFromXMLFile(File file) {
		// TODO: Implement this function yourself.
		BookCollection bookCollection = new BookCollection();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("book");

			for (int i=0; i<nodeList.getLength();i++){
				Node node = nodeList.item(i);
				if (node.getNodeType()==Node.ELEMENT_NODE){
					Element element = (Element) node;
					String title = element.getElementsByTagName("title").item(0).getTextContent();
					String authorName = element.getElementsByTagName("authorName").item(0).getTextContent();
					int yearReleased = Integer.parseInt(element.getElementsByTagName("yearReleased").item(0).getTextContent());
					BookGenre bookGenre = BookGenre.valueOf(element.getElementsByTagName("bookGenre").item(0).getTextContent());
					Book book = new Book(title,authorName,yearReleased,bookGenre);
					bookCollection.books.add(book);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bookCollection;
		
	}
}
