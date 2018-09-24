package com.ivan.newtechnologies.aspose.words;

import com.aspose.words.Document;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class AsposeReadMain {

    public static void main(String[] args) throws Exception {
        System.out.println("Without tracked");
        reviseDoc("c:\\Users\\Иван\\Desktop\\without_tracked.docx");

        System.out.println();
        System.out.println("With tracked");
        reviseDoc("c:\\Users\\Иван\\Desktop\\with_tracked_changes.docx");

        reviseDoc("c:\\Users\\Иван\\Desktop\\price.xls");
    }

    private static void reviseDoc(final String filePath) throws Exception {
        try (final InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath))) {
            final Document document = new Document(inputStream);
            final boolean trackRevisions = document.getTrackRevisions();
            System.out.println("Tracked? " + trackRevisions);

            if (trackRevisions) {
                System.out.println("Revisions: " + document.getRevisions().getCount());
            }
        }
    }

}
