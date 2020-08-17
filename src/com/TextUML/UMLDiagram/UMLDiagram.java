package com.TextUML.UMLDiagram;

import com.TextUML.UMLObjects.UMLClassObject;

import javax.swing.*;

public class UMLDiagram {
    JFrame mainFrame;
    UMLDiagramPanel diagramPanel;
    UMLClassObject[] classesToDraw;

    public UMLDiagram(UMLClassObject[] classesToDraw){
        this.classesToDraw = classesToDraw;
        InitializeComponents();
    }

    public UMLDiagram(UMLClassObject[] classesToDraw, String title){
        this.classesToDraw = classesToDraw;
        InitializeComponents();
        SetTitle(title);
    }

    public UMLDiagram(UMLClassObject[] classesToDraw, String title, int width, int height){
        this.classesToDraw = classesToDraw;
        InitializeComponents();
        SetTitle(title);
        SetSize(width, height);
    }

    void InitializeComponents(){
        mainFrame = new JFrame();
        diagramPanel = new UMLDiagramPanel(classesToDraw);

    }

    public void Launch() {
        mainFrame.add(diagramPanel);
        mainFrame.setVisible(true);
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



