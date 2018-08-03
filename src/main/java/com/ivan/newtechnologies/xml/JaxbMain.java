package com.ivan.newtechnologies.xml;

import com.ivan.newtechnologies.xml.model.TodoList;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

public class JaxbMain {

    public static void main(String[] args) throws Exception {
        final SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        final Schema schema = schemaFactory.newSchema(new StreamSource(InputStreamFactory.getSchema()));

        final JAXBContext jaxbContext = JAXBContext.newInstance(TodoList.class);
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        //unmarshaller.setSchema(schema);

        final TodoList todoList = (TodoList) unmarshaller.unmarshal(InputStreamFactory.getTodoList());
        System.out.println(todoList);
    }

}
