package com.TextUML.TextEditor;

import com.TextUML.UMLDiagram.UMLDiagram;

import javax.swing.*;

public class Editor {
    JFrame mainFrame;
    JTextArea mainTextArea;
    EditorMenuBar editorMenuBar;
    EditorActionsController actionsController;
    UMLDiagram umlDiagram = null;

    String currentFilePath = "";

    public Editor(){
        InitializeComponents();
    }

    public Editor(String title){
        InitializeComponents();
        SetTitle(title);
    }

    public Editor(String title, int width, int height){
        InitializeComponents();
        SetTitle(title);
        SetSize(width, height);
    }

    void InitializeComponents(){
        actionsController = new EditorActionsController(this);
        mainFrame = new JFrame();
        mainTextArea = new JTextArea();
        editorMenuBar = new EditorMenuBar(actionsController);
    }

    public void Launch(){
        mainFrame.setJMenuBar(editorMenuBar);
        mainFrame.add(mainTextArea);
        SetTitle("new file");
        mainFrame.show();
    }

    public void Close(){

    }

    public void SetSize(int width, int height){
        mainFrame.setSize(width, height);
    }

    public void SetTitle(String title){
        mainFrame.setTitle(title);
    }

    public String GetScriptText(){
        return mainTextArea.getText();
    }

    public void SetScriptText(String text){
        mainTextArea.setText(text);
    }

    public void SetUMLDiagram(UMLDiagram diagram){
        this.umlDiagram = diagram;
    }
}
