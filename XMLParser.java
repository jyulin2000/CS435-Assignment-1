import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class XMLParser {
	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private Document document;
	private File file;
	private boolean isValid;
	List<int[]> boundaries;
	
	public XMLParser(File file) {
		this.file = file;
		factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		isValid = true;
		try {
			document = builder.parse(file);
		} catch (SAXException e) {
			isValid = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isValidXML() {
		return isValid;
	}
	
	public List<int[]> getBoundaries() {
		boundaries = new LinkedList<int[]>();
		findLeaves(document.getDocumentElement());
		return boundaries;
	}
	
	// Recursively finds leaf nodes and returns
	// 
	private void findLeaves(Node node) {
		if (node.hasChildNodes()) {
			NodeList children = node.getChildNodes();
			for (int i = 0; i < children.getLength(); i++) {
				findLeaves(children.item(i));
			}
		}
		else {
			if (node.getAttributes() != null) {
				String[] strings = ((Element) node).getAttribute("bounds").split("[\\[\\], ]");

				int[] bound = new int[4];
				bound[0] = Integer.parseInt(strings[1]);
				bound[1] = Integer.parseInt(strings[2]);
				bound[2] = Integer.parseInt(strings[4]);
				bound[3] = Integer.parseInt(strings[5]);
				
				boundaries.add(bound);
			}
		}
	}
}
