package com.TextUML;

import com.TextUML.SyntaxRecognition.UMLTextSplitter;
import com.TextUML.TextEditor.Editor;

public class Main {
    public static void main(String[] args) {
        Editor editor = new Editor("editor", 500, 500);
        editor.Launch();

        String[] output = UMLTextSplitter.SplitUMLText("public void helloWorld(){ int test;}");
        for(int i = 0; i < output.length; i++){
            System.out.println(output[i]);
        }
    }
}
