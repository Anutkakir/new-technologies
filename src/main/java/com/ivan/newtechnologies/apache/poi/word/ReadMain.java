package com.ivan.newtechnologies.apache.poi.word;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFComment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class ReadMain {

    public static void main(String[] args) throws Exception {
        System.out.println("File without changes");
        reviseFile("c:\\Users\\Иван\\Desktop\\without_tracked.docx");

        System.out.println();
        System.out.println("File with changes");
        reviseFile("c:\\Users\\Иван\\Desktop\\with_tracked_changes.docx");
    }

    private static void reviseFile(String filePath) throws Exception {
        try (final InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath))) {
            XWPFDocument adoc = new XWPFDocument(inputStream);
            XWPFWordExtractor xwe = new XWPFWordExtractor(adoc);

            System.out.println("Tracking changes? " + adoc.isTrackRevisions());
            System.out.println(xwe);
        }
    }
}
