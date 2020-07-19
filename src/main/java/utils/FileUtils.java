package utils;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileUtils {
    public static void selectFolderAndSave(String data, String filename) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Select Folder to save file");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setVisible(true);
        chooser.showOpenDialog(chooser);
        File directory = chooser.getSelectedFile();
        if (directory != null) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(directory + "\\" + filename), StandardCharsets.UTF_8))) {
                writer.write(data);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
