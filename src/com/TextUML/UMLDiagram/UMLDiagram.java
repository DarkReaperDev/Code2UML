package com.TextUML.UMLDiagram;

import com.TextUML.UMLObjects.UMLClassObject;
import javax.swing.*;

public class UMLDiagram {
    private JFrame mainFrame;
    private UMLDiagramPanel diagramPanel;
    private UMLClassObject[] classesToDraw;

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

    public void SetSize(int width, int height){
        mainFrame.setSize(width, height);
    }

    public void SetTitle(String title){
        mainFrame.setTitle(title);
    }
}



