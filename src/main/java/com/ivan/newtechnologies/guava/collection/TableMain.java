package com.ivan.newtechnologies.guava.collection;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class TableMain {

    public static void main(String[] args) {
        final Table<String, Integer, String> table = HashBasedTable.create();

        table.put("one", 1, "один");
        table.put("one", 11, "одинодин");
        table.put("two", 2, "два");
        table.put("three", 3, "три");

        System.out.println(table);
        System.out.println(table.row("one"));
    }
}
