package com.TextUML.UMLConverter;

import com.TextUML.UMLKeywords.*;
import java.util.*;

class UMLKeywordRecognizer {

    static UMLKeyword[] GetUMLKeywords(String[] keywordStringArray){

        List<UMLKeyword> keywordList = new ArrayList<>();

        for(String keywordString : keywordStringArray){
            keywordList.add(ConvertToUMLKeyword(keywordString));
        }

        return keywordList.toArray(new UMLKeyword[keywordList.size()]);
    }

    private static UMLKeyword ConvertToUMLKeyword(String keywordString){
        UMLKeyword keyword = UMLKeywordList.GetKeywordForString(keywordString);
        if(keyword == null){
            keyword = new UMLNameKeyword(keywordString);
        }
        return keyword;
    }

}
