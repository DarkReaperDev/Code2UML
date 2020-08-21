package com.TextUML.UMLConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class UMLTextSplitter {

    private static char[] splitCharacters = {'(', ')', '{', '}',';', ' ' };

    static String[][] GetSplitUMLText(String text){
        String[] textLines = GetLines(text);
        System.out.println(textLines.length);
        textLines = RemoveLineBrakeCharactersFromLines(textLines);

        List<String> currentLine = new ArrayList<>();
        List<String[]> splitLines = new ArrayList<String[]>();
        String currentString = "";

        for(int line = 0; line < textLines.length; line ++) {
            for (int i = 0; i < textLines[line].length(); i++) {
                char character = textLines[line].charAt(i);

                if (IsSplitCharacter(character)) {
                    currentLine.add(currentString);
                    currentString = "";

                    if (character != ' ') {
                        currentLine.add(String.valueOf(character));
                    }
                } else {
                    currentString += String.valueOf(character);
                }
            }
            currentLine.add(currentString);
            RemoveEmptyStringsFrom(currentLine);

            splitLines.add(currentLine.toArray(new String[]{}));
            currentLine = new ArrayList<>();
        }

        return splitLines.toArray(new String[][]{});
    }

    private static String[] GetLines(String text){
        String[] lines = text.split("[" + System.getProperty("line.separator") + "]");
        return lines;
    }

    private static String[] RemoveLineBrakeCharactersFromLines(String[] lines){
        String[] output = new String[lines.length];
        for(int i = 0; i < lines.length; i ++){
            output[i] = lines[i].replaceAll("[" + System.getProperty("line.separator") + "]", "");
        }
        return output;
    }

    private static void RemoveEmptyStringsFrom(List<String> strings){
        strings.removeAll(Collections.singleton(""));
    }

    private static boolean IsSplitCharacter(char character){
        for(int i = 0; i < splitCharacters.length; i++){
            if(character == splitCharacters[i]){
                return true;
            }
        }
        return false;
    }

    private static List<String> AppendStringIfNotEmpty(List<String> stringList, String stringToAppend){
        if(stringToAppend != ""){
            stringList.add(stringToAppend);
        }
        return stringList;
    }
}
