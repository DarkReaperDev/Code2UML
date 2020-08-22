package com.TextUML.FileManager;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileManager {
    public static void SaveTextAsFile(String path, String text){
        try{
            //file is only created if it does not exist yet
            File file = new File(path);

            FileWriter writer = new FileWriter(path);
            writer.write(text);
            writer.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public static String LoadTextFromFile(String path){
        String fileText = "";
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                fileText += scanner.nextLine() + System.getProperty("line.separator");
            }
            scanner.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return fileText;
    }

    public static String LetUserChooseFilePath(){
        JButton chooseFileBtn = new JButton();
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("choose file");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if(chooser.showOpenDialog(chooseFileBtn) == JFileChooser.APPROVE_OPTION){

        }
        try {
            return chooser.getSelectedFile().getAbsolutePath();
        }
        catch(Exception e) {
            return "";
        }
    }
}
