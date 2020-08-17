package com.TextUML;

import com.TextUML.UMLDiagram.UMLDiagram;
import com.TextUML.UMLConverter.UMLConverter;
import com.TextUML.TextEditor.Editor;
import com.TextUML.UMLObjects.UMLScriptObject;

public class Main {
    public static void main(String[] args) throws Exception {
        Editor editor = new Editor("editor", 500, 500);
        editor.Launch();

        UMLScriptObject scriptObject = UMLConverter.ConvertUMLTextToObject("class helloWorld{ public int test; int test2;}");

        System.out.println(scriptObject.GetUMLClassObjects().length);

        UMLDiagram uml_diagram = new UMLDiagram(scriptObject.GetUMLClassObjects(), "UML Diagram", 400, 400);
        uml_diagram.Launch();
    }
}
