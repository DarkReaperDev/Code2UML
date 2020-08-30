package com.TextUML.UMLDiagram;

import com.TextUML.UMLObjects.UMLClassObject;

import javax.swing.*;
import java.awt.*;

class UMLDiagramPanel extends JPanel {

    UMLClassObject[] classesToDraw;

    public UMLDiagramPanel(UMLClassObject[] classesToDraw){
        this.classesToDraw = classesToDraw;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        UMLDrawer.Reset();
        UMLDrawer.DrawUMLClass(classesToDraw, g);
    }

    public void Update(){
        repaint();
    }
}
