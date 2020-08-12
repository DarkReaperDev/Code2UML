package com.TextUML.SyntaxRecognition;

import com.TextUML.UMLKeywords.*;

import java.util.*;

public class UMLKeywordRecognizer {

    public static UMLKeyword[] GetUMLKeywords(String[] uml_text){

        List<UMLKeyword> keyword_list = new ArrayList<UMLKeyword>();

        for(String s : uml_text){
            UMLKeyword keyword = GetUMLKeyword(s);
            if(keyword == null){
                //Append new name keyword containing s
            }
            else{
                keyword_list.add(keyword);
            }
        }

        return keyword_list.toArray(new UMLKeyword[keyword_list.size()]);
    }

    static UMLKeyword GetUMLKeyword(String s){
        return null;
    }

    public static HashMap<String, UMLKeyword> uml_keyword_strings = new HashMap<String, UMLKeyword>(){{
        put("public", UMLModifierKeyword.UMLPublic);
        put("private", UMLModifierKeyword.UMlPrivate);

        put("string", UMLValueTypeKeyword.UMLString);
        put("int", UMLValueTypeKeyword.UMLInt);
        put("float", UMLValueTypeKeyword.UMLFloat);
        put("bool", UMLValueTypeKeyword.UMLBool);
        put("null", UMLValueTypeKeyword.UMLNull);

        put("class", UMLStructureKeyword.UMLClass);

        put("(", UMLBracketKeyword.UMLBracketOpen);
        put(")", UMLBracketKeyword.UMLBracketClose);
        put("{", UMLBracketKeyword.UMLCurlyBracketOpen);
        put("}", UMLBracketKeyword.UMLCurlyBracketClose);
    }};

}
