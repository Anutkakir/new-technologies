package com.ivan.newtechnologies.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

public class XPathMain {

    public static void main(String[] args) throws Exception {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder documentBuilder = factory.newDocumentBuilder();

        final Document document = documentBuilder.parse(InputStreamFactory.getTodoList());

        final XPath xPath = XPathFactory.newInstance().newXPath();
        final NodeList nodeList = (NodeList) xPath.compile("/todo-list/items/item").evaluate(document, XPathConstants.NODESET);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.println(node.getAttributes().getNamedItem("id"));
        }
    }

}
