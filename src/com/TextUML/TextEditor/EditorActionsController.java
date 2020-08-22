package com.TextUML.TextEditor;

import com.TextUML.FileManager.FileManager;
import com.TextUML.UMLConverter.UMLConverter;
import com.TextUML.UMLDiagram.UMLDiagram;
import com.TextUML.UMLErrorHandling.UMLScriptError;
import com.TextUML.UMLErrorHandling.UMLScriptErrorHandler;
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
        try {
            UMLScriptObject scriptObject = UMLConverter.ConvertUMLTextToObject(script);

            UMLDiagram diagram = new UMLDiagram(scriptObject.GetUMLClassObjects(), "uml diagram", 1000, 1000);
            diagram.Launch();
            editor.SetUMLDiagram(diagram);
        }
        catch (UMLScriptError e){
            UMLScriptErrorHandler.HandleUMLScriptError(e);
        }
    }

    public void Save(){
        String path;
        if(editor.currentFilePath == "") {
            path = FileManager.LetUserChooseFilePath();
        }
        else{
            path = editor.currentFilePath;
            if(path == ""){
                return;
            }
        }
        FileManager.SaveTextAsFile(path, editor.GetScriptText());
        editor.currentFilePath = path;
        editor.SetTitle(path);
    }

    public void SaveAs(){
        String path = FileManager.LetUserChooseFilePath();
        if(path == ""){
            return;
        }
        FileManager.SaveTextAsFile(path, editor.GetScriptText());
        editor.currentFilePath = path;
        editor.SetTitle(path);
    }

    public void Load(){
        String path = FileManager.LetUserChooseFilePath();
        if(path == ""){
            return;
        }
        String fileText = FileManager.LoadTextFromFile(path);
        editor.SetScriptText(fileText);
        editor.currentFilePath = path;
        editor.SetTitle(path);
    }
}
