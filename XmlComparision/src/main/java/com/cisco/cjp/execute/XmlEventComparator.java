package com.cisco.cjp.execute;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class XmlEventComparator {
	private static final Logger logger = LogManager.getLogger(XmlEventComparator.class.getName());

	File jacgXmlFile;
	Node nextExpectedJacgNode;
	Document jacgxmlfileDoc;
	String fileName;
	Integer actualEventCnt = 0;
	Long previousTimestamp;

	List<String> ignoreCompare = new ArrayList<String>();

	Map<String, List<String>> agetSubStates = new HashMap<String, List<String>>();

	String currentActualNodeKey;
	String currentExpectedNodeKey;

	public XmlEventComparator(File jacgxmlfile) {
		jacgXmlFile = jacgxmlfile;
		fileName = jacgXmlFile.getName();
		try {
			jacgxmlfileDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(jacgXmlFile);
		} catch (SAXException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (ParserConfigurationException e) {

			e.printStackTrace();
		}
		nextExpectedJacgNode = getFirstChildNode(getFirstChildNode(jacgxmlfileDoc));

		actualEventCnt++;
		previousTimestamp = Long.parseLong("1572520549226"); // System.currentTimeMillis();

		ignoreCompare.add("xmlns");
		ignoreCompare.add("aepUrl");
		ignoreCompare.add("agentsessionid");
		ignoreCompare.add("callId");
		ignoreCompare.add("channelId");

	}

	public boolean compareEvent(Node actualTacgEvent) {

		String actualTacgNodeName = actualTacgEvent.getNodeName();
		String expectedJacgNodeName = nextExpectedJacgNode.getNodeName();

		if (!actualTacgNodeName.equals(expectedJacgNodeName)) {
			logger.error("actualTacgNodeName : {} and  actualJacgNodeName {} doesnot match.", actualTacgNodeName,
					expectedJacgNodeName);

			return false;
		}

		logger.info("actualTacgNodeName : {} and expectedJacgNodeName {}", actualTacgNodeName, expectedJacgNodeName);

		String actualAgentId;
		String expectedAgentId;
		String actualDn;
		String expectedDn;
		String actualSubStatus;
		String expectedSubStatus;
		String actualCommandName;
		String expectedCommandName;

		if (actualTacgNodeName.equalsIgnoreCase("agent")) {
			actualAgentId = actualTacgEvent.getAttributes().getNamedItem("agentId").getNodeValue();
			actualDn = actualTacgEvent.getAttributes().getNamedItem("dn").getNodeValue();
			actualSubStatus = actualTacgEvent.getAttributes().getNamedItem("subStatus").getNodeValue();

			currentActualNodeKey = actualAgentId + "_" + actualSubStatus + "_" + actualDn;

			logger.info("*********Actual :    [    {}  ]", currentActualNodeKey);

			expectedAgentId = nextExpectedJacgNode.getAttributes().getNamedItem("agentId").getNodeValue();
			expectedDn = nextExpectedJacgNode.getAttributes().getNamedItem("dn").getNodeValue();
			expectedSubStatus = nextExpectedJacgNode.getAttributes().getNamedItem("subStatus").getNodeValue();

			currentExpectedNodeKey = expectedAgentId + "_" + expectedSubStatus + "_" + expectedDn;
			logger.info("*********EventCnt = {}  : expectedAgentId -[ {} ]", actualEventCnt, currentExpectedNodeKey);

			List<String> subStates = new ArrayList<String>();

			String actualStatus = actualTacgEvent.getAttributes().getNamedItem("status").getNodeValue();

			if (agetSubStates.containsKey(actualDn)) {
				subStates = agetSubStates.get(actualDn);
				subStates.add(actualSubStatus);
			} else {
				subStates.add(actualSubStatus);
			}

			if (actualStatus.equalsIgnoreCase("LoggedOut")) {
				subStates.add("LoggedOut");
			}
			
			agetSubStates.put(actualDn, subStates);
			

		} else if (actualTacgNodeName.equalsIgnoreCase("agent-command")) {
			actualAgentId = actualTacgEvent.getAttributes().getNamedItem("agentId").getNodeValue();
			actualDn = actualTacgEvent.getAttributes().getNamedItem("agentDn").getNodeValue();
			actualCommandName = actualTacgEvent.getAttributes().getNamedItem("name").getNodeValue();

			currentActualNodeKey = actualAgentId + "_" + actualCommandName + "_" + actualDn;

			logger.info("@@@@@@@@@@             Actual :  [ {} ]", currentActualNodeKey);

			expectedAgentId = nextExpectedJacgNode.getAttributes().getNamedItem("agentId").getNodeValue();
			expectedDn = nextExpectedJacgNode.getAttributes().getNamedItem("agentDn").getNodeValue();
			expectedCommandName = nextExpectedJacgNode.getAttributes().getNamedItem("name").getNodeValue();

			currentExpectedNodeKey = expectedAgentId + "_" + expectedCommandName + "_" + expectedDn;

			logger.info("@@@@@@@@@@EventCnt = {} Expected : [ {} ]", actualEventCnt, currentExpectedNodeKey);

		}

		boolean isPass = compareNode(actualTacgEvent, nextExpectedJacgNode);
		nextExpectedJacgNode = getNextSiblingNode(nextExpectedJacgNode);
		actualEventCnt++;
		return isPass;

	}

	public boolean compareNode(Node actualTacgNode, Node expectedJacgNode) {
		boolean isPass = true;

		if (expectedJacgNode == null) {
			logger.error("nextExpectedJacgNode is null...event arrived is extra which is not expected!!!!");
			return false;
		}

		if (fileName.equalsIgnoreCase("jacgAgentEvents.xml")) {

			String actualTacgNodeName = actualTacgNode.getNodeName();
			String expectedJacgNodeName = expectedJacgNode.getNodeName();

			currentActualNodeKey = currentActualNodeKey + "_" + actualTacgNodeName;
			currentExpectedNodeKey = currentExpectedNodeKey + "_" + expectedJacgNodeName;

			if (!compareNodeAttributes(actualTacgNode, expectedJacgNode)) {
				logger.error("{} - Compare attributes failed", currentExpectedNodeKey);
				// return false;
				isPass = false;
			}

			Node expectedChildNode = getFirstChildNode(expectedJacgNode);

			for (Node actualChildNode = getFirstChildNode(
					actualTacgNode); actualChildNode != null; actualChildNode = getNextSiblingNode(actualChildNode)) {

				if (!compareNode(actualChildNode, expectedChildNode)) {
					logger.error("{} - Compare node failed.", currentExpectedNodeKey);
					// return false;
					isPass = false;
				}

				if (expectedChildNode != null)
					expectedChildNode = getNextSiblingNode(expectedChildNode);

			}
		} else if (fileName.equalsIgnoreCase("jacgCallEvents")) {

		}

		return isPass;
	}

	private boolean compareNodeAttributes(Node actualTacgNode, Node expectedJacgNode) {

		NamedNodeMap actualTacgAttributes = actualTacgNode.getAttributes();
		NamedNodeMap expectedJacgAttributes = expectedJacgNode.getAttributes();

		boolean isPass = true;
		Node actualTacgNodeAttribute;
		Node expectedJacgNodeAttribute;
		String errorMsgAttr = currentActualNodeKey;

		for (Integer i = 0; i < actualTacgAttributes.getLength(); i++) {
			actualTacgNodeAttribute = actualTacgAttributes.item(i);
			String actualAttributeName = actualTacgNodeAttribute.getNodeName();

			expectedJacgNodeAttribute = expectedJacgAttributes.getNamedItem(actualAttributeName);

			if (expectedJacgNodeAttribute == null) {
				errorMsgAttr = errorMsgAttr + " Extra Attribute :" + actualAttributeName
						+ " present in generatred event.\n";
				isPass = false;
			}

			if (actualAttributeName.equals("timestamp")) {
				Long timestamp = Long.parseLong(actualTacgNodeAttribute.getNodeValue());
				if (timestamp < previousTimestamp) {
					errorMsgAttr = errorMsgAttr + " current timestamp : " + timestamp
							+ " is older then previous timestamp : " + previousTimestamp + ".\n";
					isPass = false;
				}
				previousTimestamp = timestamp;

			} else if (!ignoreCompare.contains(actualAttributeName)) {

				String actualTacgAttValue = actualTacgNodeAttribute.getNodeValue();
				String expectedJacgAttValue = expectedJacgNodeAttribute.getNodeValue();

				if (!actualTacgAttValue.equals(expectedJacgAttValue)) {
					errorMsgAttr = errorMsgAttr + " Expected attribute " + actualAttributeName + " values ["
							+ expectedJacgAttValue + "] doesnot match with actual [" + actualTacgAttValue + "] \n";
					isPass = false;
				}

			}

			expectedJacgAttributes.removeNamedItem(actualAttributeName);
		}

		if (expectedJacgAttributes.getLength() > 0) {
			errorMsgAttr = errorMsgAttr + " some attributes are missing in generated event\n";
			isPass = false;

			for (Integer i = 0; i < expectedJacgAttributes.getLength(); i++) {
				Node nddd = expectedJacgAttributes.item(i);
				String actualAtName = nddd.getNodeName();
				logger.info("Extra event attribute: {}", actualAtName);
			}
		}

		if (isPass) {
			logger.info("{} - All event attribute values are matched......", currentActualNodeKey);
		} else {
			logger.info("Error = {}", errorMsgAttr);
		}

		return isPass;

	}
	
	
	public void printAgentStatus() {
		for(String k : agetSubStates.keySet()) {
			logger.info("Agent DN : {} with subStatus:  {}", k, agetSubStates.get(k));
		}
	}

	public Node getFirstChildNode(Node n) {
		if (n != null) {
			n = n.getFirstChild();
			while (n != null && n.getNodeType() != Node.ELEMENT_NODE) {
				n = n.getNextSibling();
			}
		}
		return n;
	}

	public Node getNextSiblingNode(Node n) {
		if (n != null) {
			n = n.getNextSibling();
			while (n != null && n.getNodeType() != Node.ELEMENT_NODE) {
				n = n.getNextSibling();
			}
		}
		return n;
	}

}
