package com.TextUML.SyntaxRecognition;

import java.util.ArrayList;
import java.util.List;

public class UMLTextSplitter {

    static char[] split_characters = {'(', ')', '{', '}',';','\\' };

    public static String[] SplitUMLText(String text){

        List<String> split_text = new ArrayList<String>();
        String current_string = "";

        //TODO: find cleaner way to check if current string is empty and only add it if not -> currently to much duplication
        for(int i = 0; i < text.length(); i ++){
            char character = text.charAt(i);
            if(character == ' '){
                if(current_string != ""){
                    split_text.add(current_string);
                }
                current_string = "";
            }
            else if(IsSplitCharacter(character)){
                if(current_string != ""){
                    split_text.add(current_string);
                }
                current_string = String.valueOf(character);
            }
            else{
                current_string += String.valueOf(character);
            }
        }

        if(current_string != ""){
            split_text.add(current_string);
        }

        String[] split_text_array = new String[split_text.size()];
        return split_text.toArray(split_text_array);
    }

    static boolean IsSplitCharacter(char character){
        for(int i = 0; i < split_characters.length; i++){
            if(character == split_characters[i]){
                return true;
            }
        }
        return false;
    }

}
