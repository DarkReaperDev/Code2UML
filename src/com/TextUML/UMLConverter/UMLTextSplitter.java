package com.TextUML.UMLConverter;

import javax.swing.text.DefaultEditorKit;
import java.util.ArrayList;
import java.util.List;

class UMLTextSplitter {

    private static char[] splitCharacters = {'(', ')', '{', '}',';' };

    static String[] SplitUMLText(String t){
        String text = t;
        List<String> splitText = new ArrayList<String>();
        text = text.replaceAll("[" + System.getProperty("line.separator") + "]", "");
        String currentString = "";

        //TODO: find cleaner way to check if current string is empty and only add it if not -> currently to much duplication
        for(int i = 0; i < text.length(); i ++){
            char character = text.charAt(i);


            if(character == ' '){
                if(currentString != ""){
                    splitText.add(currentString);
                }
                currentString = "";
            }
            else if(IsSplitCharacter(character)){
                if(currentString != ""){
                    splitText.add(currentString);
                    currentString = "";
                }
                splitText.add(String.valueOf(character));
            }
            else{
                currentString += String.valueOf(character);
            }
        }

        if(currentString != ""){
            splitText.add(currentString);
        }

        String[] split_text_array = new String[splitText.size()];
        for(String s : splitText){
            System.out.println(s);
        }
        return splitText.toArray(split_text_array);
    }

    private static String RemoveNewLineCharFrom(String s){
        return s.replace(System.getProperty("line.separator"), "");
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
