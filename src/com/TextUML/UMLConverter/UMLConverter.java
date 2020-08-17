package com.TextUML.SyntaxRecognition;

import com.TextUML.UMLKeywords.UMLKeyword;
import com.TextUML.UMLObjects.UMLScriptObject;

public class UMLConverter {

    public static UMLScriptObject ConvertUMLTextToObject(String umlText){
        String[] output = UMLTextSplitter.SplitUMLText(umlText);
        UMLKeyword[] outputKeywords = UMLKeywordRecognizer.GetUMLKeywords(output);
        UMLScriptObject scriptObject = UMLPatternRecognizer.ConvertUMLKeywordsToObject(outputKeywords);
        return scriptObject;
    }
}
