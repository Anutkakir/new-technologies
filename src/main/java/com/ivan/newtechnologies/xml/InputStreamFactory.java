package com.ivan.newtechnologies.xml;

import java.io.InputStream;

public class InputStreamFactory {

    public static InputStream getTodoList() {
        return InputStreamFactory.class.getClassLoader().getResourceAsStream("xml/todo-list.xml");
    }

    public static InputStream getTodoListInvalid() {
        return InputStreamFactory.class.getClassLoader().getResourceAsStream("xml/todo-list-invalid.xml");
    }

    public static InputStream getSchema() {
        return InputStreamFactory.class.getClassLoader().getResourceAsStream("xml/todo-list.xsd");
    }
}
