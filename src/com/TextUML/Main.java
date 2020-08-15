package com.TextUML;

import com.TextUML.SyntaxRecognition.UMLKeywordRecognizer;
import com.TextUML.SyntaxRecognition.UMLPatternRecognizer;
import com.TextUML.SyntaxRecognition.UMLTextSplitter;
import com.TextUML.TextEditor.Editor;
import com.TextUML.UMLKeywords.UMLBracketKeyword;
import com.TextUML.UMLKeywords.UMLKeyword;
import com.TextUML.UMLObjects.UMLScriptObject;

public class Main {
    public static void main(String[] args) throws Exception {
        Editor editor = new Editor("editor", 500, 500);
        editor.Launch();

        String[] output = UMLTextSplitter.SplitUMLText("class helloWorld{ public int test; int test2;}");
        UMLKeyword[] output_keywords = UMLKeywordRecognizer.GetUMLKeywords(output);
        for(UMLKeyword keyword : output_keywords){
            System.out.println(keyword);
        }

        UMLScriptObject scriptObject = UMLPatternRecognizer.UMLConvertKeywordsToObject(output_keywords);

    }
}
