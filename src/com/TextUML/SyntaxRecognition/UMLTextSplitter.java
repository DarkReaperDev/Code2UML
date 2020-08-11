package com.TextUML.SyntaxRecognition;

import java.util.ArrayList;
import java.util.List;

public class UMLTextSplitter {

    char[] split_character = {'(', ')', '{', '}',';','\\' };

    public String[] SplitUMLText(String text){

        List<String> splitted_text = new ArrayList<String>();
        String current_string = "";

        for(int i = 0; i < text.length(); i ++){
            char c = text.charAt(i);
            if(c == ' '){
                if(current_string != ""){
                    splitted_text.add(current_string);
                }
                current_string = "";
            }
        }

        return null;
    }
}
