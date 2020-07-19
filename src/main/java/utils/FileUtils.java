package utils;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileUtils {
    public static void selectFolderAndSave(String data) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Select Folder to save file");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(null);
        File directory = chooser.getSelectedFile();
        if (directory != null) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(directory + "\\products.json"), StandardCharsets.UTF_8))) {
                writer.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
