package com.TextUML.UMLConverter;

import com.TextUML.UMLKeywords.*;
import java.util.*;

class UMLKeywordRecognizer {

    static UMLKeyword[] GetUMLKeywords(String[] keywordStringArray){

        List<UMLKeyword> keyword_list = new ArrayList<UMLKeyword>();

        for(String keywordString : keywordStringArray){
            keyword_list.add(ConvertToUMLKeyword(keywordString));
        }

        return keyword_list.toArray(new UMLKeyword[keyword_list.size()]);
    }

    private static UMLKeyword ConvertToUMLKeyword(String keywordString){
        UMLKeyword keyword = UMLKeywordList.GetKeywordForString(keywordString);
        if(keyword == null){
            keyword = new UMLNameKeyword(keywordString);
        }
        return keyword;
    }

}
