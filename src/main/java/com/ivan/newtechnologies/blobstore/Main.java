package com.ivan.newtechnologies.blobstore;

import org.apache.commons.lang3.StringUtils;
import org.jclouds.ContextBuilder;
import org.jclouds.blobstore.BlobStore;
import org.jclouds.blobstore.BlobStoreContext;
import org.jclouds.blobstore.domain.PageSet;
import org.jclouds.blobstore.domain.StorageMetadata;
import org.jclouds.blobstore.domain.StorageType;
import org.jclouds.blobstore.options.ListContainerOptions;
import org.jclouds.filesystem.reference.FilesystemConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        listFolders();
    }

    private static void listFiles() {
        final BlobStore blobStore = createBlobStore();

        final List<String> folderContents = new ArrayList<>();

        final ListContainerOptions options = ListContainerOptions.Builder.inDirectory("reports");
        PageSet<? extends StorageMetadata> pageSet = null;
        while (true) {

            pageSet = blobStore.list("media", options);

            folderContents.addAll(keepOnlyFiles(pageSet));

            if (pageSet.isEmpty() || pageSet.getNextMarker() == null) {
                break;
            }

            options.afterMarker(pageSet.getNextMarker());
        }

        folderContents.forEach(System.out::println);
    }

    private static void listFolders() {
        final BlobStore blobStore = createBlobStore();

        final String directory = "reports";

        final List<String> folderContents = new ArrayList<>();
        final ListContainerOptions options = ListContainerOptions.Builder.inDirectory(directory);

        PageSet<? extends StorageMetadata> pageSet = null;
        while (true) {
            pageSet = blobStore.list("media", options);

            folderContents.addAll(keepOnlyFolders(pageSet));

            if (pageSet.isEmpty() || pageSet.getNextMarker() == null) {
                break;
            }

            options.afterMarker(pageSet.getNextMarker());
        }

        folderContents.forEach(System.out::println);
    }

    private static void deleteDirectory() {
        final BlobStore blobStore = createBlobStore();

        blobStore.removeBlob("media", "reports/2017-08-02");
        System.out.println("Removed");
    }

    private static BlobStore createBlobStore() {

        final ContextBuilder contextBuilder = ContextBuilder.newBuilder("filesystem");

        final Properties properties = new Properties();
        properties.setProperty(FilesystemConstants.PROPERTY_BASEDIR, "d:\\TMP");
        contextBuilder.overrides(properties);

        return contextBuilder.buildView(BlobStoreContext.class).getBlobStore();
    }

    private static List<String> keepOnlyFiles(final PageSet<? extends StorageMetadata> pageSet) {
        // @formatter:off
        return pageSet.stream()
                .map(StorageMetadata::getName)
//                .map(this::removePrefix) // Remove prefix
//                .filter(name -> !StringUtils.endsWith(name, "/")) // Remove directories from the list
                .filter(StringUtils::isNotBlank)
                .distinct()
                .collect(Collectors.toList());
        // @formatter:on
    }

    private static List<String> keepOnlyFolders(final PageSet<? extends StorageMetadata> pageSet) {
        // @formatter:off
        return pageSet.stream()
                .filter(metadata -> metadata.getType() == StorageType.RELATIVE_PATH)
                .map(StorageMetadata::getName)
//                .filter(name -> StringUtils.endsWith(name, "/")) // Directories always end with SEPARATOR
//                .map(this::removePrefix)
                .map(Main::removeEndSeparator)
                .collect(Collectors.toList());
        // @formatter:on
    }

    private static String removeEndSeparator(final String name) {
        return org.apache.commons.lang3.StringUtils.removeEnd(name, "/");
    }
}
