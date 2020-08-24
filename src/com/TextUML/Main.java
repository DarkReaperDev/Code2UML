package com.TextUML;

import com.TextUML.UMLDiagram.UMLDiagram;
import com.TextUML.UMLConverter.UMLConverter;
import com.TextUML.TextEditor.Editor;
import com.TextUML.UMLObjects.UMLScriptObject;

public class Main {
    public static void main(String[] args){
        Editor editor = new Editor("editor", 500, 500);
        editor.Launch();
    }
}
