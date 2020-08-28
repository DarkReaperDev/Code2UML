package com.TextUML.UMLConverter;

import com.TextUML.UMLErrorHandling.UMLScriptError;
import com.TextUML.UMLKeywords.UMLKeyword;
import com.TextUML.UMLObjects.UMLScriptObject;

public class UMLConverter {

    public static UMLScriptObject ConvertUMLTextToObject(String umlText) throws UMLScriptError {
        String[][] output = UMLTextSplitter.GetSplitUMLText(umlText);
        UMLKeyword[] outputKeywords = UMLKeywordRecognizer.GetUMLKeywords(output);
        UMLScriptObject scriptObject = UMLPatternRecognizer.ConvertUMLKeywordsToObject(outputKeywords);
        scriptObject.LinkUMLClassesAndCreateMissing();
        return scriptObject;
    }
}
