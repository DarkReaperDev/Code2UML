package com.TextUML.UMLConverter;

import com.TextUML.UMLKeywords.*;
import com.TextUML.UMLKeywords.KeywordTypes.UMLKeywordType;
import com.TextUML.UMLKeywords.KeywordTypes.UMLNameKeywordType;

import java.util.*;

class UMLKeywordRecognizer {

    static UMLKeyword[] GetUMLKeywords(String[][] keywordStringLinesArray){

        List<UMLKeyword> keywordList = new ArrayList<>();

        for(int line = 0; line < keywordStringLinesArray.length; line ++){
            for(String keywordString : keywordStringLinesArray[line]){
                System.out.println(keywordString);
                keywordList.add(ConvertToUMLKeyword(keywordString, line + 1)); //line + 1 because text area lines start at 1, but the lines array starts at 0
            }
        }

        return keywordList.toArray(new UMLKeyword[keywordList.size()]);
    }

    private static UMLKeyword ConvertToUMLKeyword(String keywordString, int keywordScriptLine){
        UMLKeywordType keywordType = UMLKeywordList.GetKeywordTypeForString(keywordString);
        return new UMLKeyword(keywordType, keywordScriptLine);
    }

}
