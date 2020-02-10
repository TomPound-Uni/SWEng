package tools;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLParser {

	
	public static Document getDocument(String name) {
		try {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);
		factory.setValidating(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse(new InputSource(name));
		}
		catch(ParserConfigurationException | IOException | SAXException e) {
			System.out.println(e);
		}
		return null;
	}
}
