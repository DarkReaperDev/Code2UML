package com.TextUML;

import com.TextUML.SyntaxRecognition.UMLKeywordRecognizer;
import com.TextUML.SyntaxRecognition.UMLTextSplitter;
import com.TextUML.TextEditor.Editor;
import com.TextUML.UMLKeywords.UMLKeyword;

public class Main {
    public static void main(String[] args) {
        Editor editor = new Editor("editor", 500, 500);
        editor.Launch();

        String[] output = UMLTextSplitter.SplitUMLText("public void helloWorld(){ int test;}");
        UMLKeyword[] output_keywords = UMLKeywordRecognizer.GetUMLKeywords(output);
        for (UMLKeyword keyword : output_keywords){
            System.out.println(keyword);
        }
    }
}
