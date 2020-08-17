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

        for(UMLClassObject umlClass : classesToDraw) {
            UMLDrawer.DrawUMLClass(umlClass, g);
        }
    }

    public void Update(){
        repaint();
    }
}
