package com.TextUML.TextEditor;

import javax.swing.*;

public class Editor {
    JFrame mainFrame;
    JTextArea mainTextArea;
    EditorMenuBar editorMenuBar;

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
        mainFrame = new JFrame();
        mainTextArea = new JTextArea();
        editorMenuBar = new EditorMenuBar();
    }

    public void Launch(){
        mainFrame.setJMenuBar(editorMenuBar);
        mainFrame.add(mainTextArea);
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
}
