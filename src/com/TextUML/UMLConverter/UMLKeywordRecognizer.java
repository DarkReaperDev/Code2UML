package com.TextUML.UMLConverter;

import com.TextUML.UMLKeywords.*;
import com.TextUML.UMLKeywords.KeywordTypes.UMLKeywordType;
import com.TextUML.UMLKeywords.KeywordTypes.UMLNameKeywordType;

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
        UMLKeywordType keywordType = UMLKeywordList.GetKeywordTypeForString(keywordString);
        return new UMLKeyword(keywordType);
    }

}
