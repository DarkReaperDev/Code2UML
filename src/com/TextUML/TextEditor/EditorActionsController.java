package com.TextUML.TextEditor;

import com.TextUML.UMLConverter.UMLConverter;
import com.TextUML.UMLDiagram.UMLDiagram;
import com.TextUML.UMLObjects.UMLScriptObject;

class EditorActionsController {
    Editor editor;


    public EditorActionsController(Editor editor){
        this.editor = editor;
    }

    public void CloseEditor(){
        System.exit(0);
    }

    public void CreateDiagram(){
        String script = editor.GetScriptText();
        System.out.println(script);
        UMLScriptObject scriptObject = UMLConverter.ConvertUMLTextToObject(script);

        UMLDiagram diagram = new UMLDiagram(scriptObject.GetUMLClassObjects(), "uml diagram", 1000, 1000);
        diagram.Launch();
        editor.SetUMLDiagram(diagram);
    }
}
