package com.ivan.newtechnologies.xml.dom;

import com.ivan.newtechnologies.xml.InputStreamFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DomMain {

    public static void main(String[] args) throws Exception {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder documentBuilder = factory.newDocumentBuilder();

        final Document document = documentBuilder.parse(InputStreamFactory.getTodoList());

        final Element documentElement = document.getDocumentElement();
        System.out.println(documentElement.getTagName());

        final NodeList items = document.getElementsByTagName("item");
        for (int i = 0; i < items.getLength(); i++) {
            final Node item = items.item(i);
            System.out.println(item.getNodeName() + ":" + item.getAttributes().getNamedItem("id"));
        }
    }
}
