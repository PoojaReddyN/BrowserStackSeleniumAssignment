package com.browserStack.utilities;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ImageDownloader {

    public static void downloadImage(String title, String imageSource){

        String imagesPath = ConfigLoader.getProperty("images_path");
        Path imageDirectory = Paths.get(imagesPath);

        Path outputPath = imageDirectory.resolve("image_" + title.substring(0, 1).toUpperCase() + ".jpg");

        try (InputStream in = new URL(imageSource).openStream()) {
            Files.copy(in, outputPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Downloaded image: " + outputPath);
        } catch (Exception e) {
            System.out.println("Failed to download image: " + e.getMessage());
        }



    }
}
