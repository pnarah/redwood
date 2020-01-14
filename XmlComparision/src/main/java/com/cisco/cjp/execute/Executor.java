package com.cisco.cjp.execute;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Executor {
	
	private static final Logger logger = LogManager.getLogger(Executor.class.getName());

	public static void main(String[] args) {
		Document tacgxmlfileDoc;
		Node tacgEvent;

		String baseDirectory = System.getProperty("user.dir");
		File tacgxmlfile = new File(baseDirectory + "/src/main/resources/tacgAgentEvents.xml");
		
		
		File jacgxmlfile = new File(baseDirectory + "/src/main/resources/jacgAgentEvents.xml");
		XmlEventComparator eventComparator = new XmlEventComparator(jacgxmlfile);
		

		try {
			tacgxmlfileDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(tacgxmlfile);
				
			
			
			tacgEvent = getFirstChildNode(getFirstChildNode(tacgxmlfileDoc));
			logger.info("This is the tacgNode : {}",tacgEvent);
			
			while(tacgEvent!=null) {
				logger.info("=============================this is generated actual event  compareEvent=============================");
				boolean isPass = eventComparator.compareEvent(tacgEvent);
				tacgEvent = getNextSiblingNode(tacgEvent);
				logger.info("=================*****************============[{}]==============*****************===============",isPass);
			}
			
			
			eventComparator.printAgentStatus();
			
			
			
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	public static Node getFirstChildNode(Node n){
		if(n != null) {
			n = n.getFirstChild();
			while(n != null && n.getNodeType() != Node.ELEMENT_NODE) {
				n = n.getNextSibling();
			}
		}
		return n;
	}
	
	
	
	public static Node getNextSiblingNode(Node n ) {
		if(n != null) {
			n = n.getNextSibling();
			while(n!= null && n.getNodeType() != Node.ELEMENT_NODE) {
				n = n.getNextSibling();
			}
		}
		return n;
	}
	
	
}
