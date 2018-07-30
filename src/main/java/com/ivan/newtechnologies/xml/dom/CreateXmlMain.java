package com.ivan.newtechnologies.xml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class CreateXmlMain {

    public static void main(String[] args) throws Exception {
        final StringWriter stringWriter = new StringWriter();

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder documentBuilder = factory.newDocumentBuilder();

        final Document document = documentBuilder.newDocument();
        final Element money = document.createElement("money");
        document.appendChild(money);

        final Element amount = document.createElement("amount");
        amount.appendChild(document.createTextNode("200.52"));
        money.appendChild(amount);

        final Element currency = document.createElement("currency");
        currency.appendChild(document.createTextNode("USD"));
        money.appendChild(currency);

        final Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(new DOMSource(document), new StreamResult(stringWriter));

        System.out.println(stringWriter);
    }

}
