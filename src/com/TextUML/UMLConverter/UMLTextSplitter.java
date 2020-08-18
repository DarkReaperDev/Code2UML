package com.TextUML.UMLConverter;

import java.util.ArrayList;
import java.util.List;

class UMLTextSplitter {

    private static char[] splitCharacters = {'(', ')', '{', '}',';', ' ' };

    static String[] GetSplitUMLText(String text){
        List<String> splitText = new ArrayList<String>();
        text = RemoveNewLineCharsFrom(text);
        String currentString = "";

        for(int i = 0; i < text.length(); i ++){
            char character = text.charAt(i);


            if(IsSplitCharacter(character)){
                splitText = AppendStringIfNotEmpty(splitText, currentString);
                currentString = "";

                if(character != ' ') {
                    splitText.add(String.valueOf(character));
                }
            }
            else{
                currentString += String.valueOf(character);
            }
        }
        splitText = AppendStringIfNotEmpty(splitText, currentString);

        return splitText.toArray(new String[]{});
    }

    private static List<String> AppendStringIfNotEmpty(List<String> stringList, String stringToAppend){
        if(stringToAppend != ""){
            stringList.add(stringToAppend);
        }
        return stringList;
    }

    private static String RemoveNewLineCharsFrom(String s){
        return s.replaceAll("[" + System.getProperty("line.separator") + "]", "");
    }

    private static boolean IsSplitCharacter(char character){
        for(int i = 0; i < splitCharacters.length; i++){
            if(character == splitCharacters[i]){
                return true;
            }
        }
        return false;
    }
}
