package com.TextUML.UMLDiagram;

import com.TextUML.UMLDiagram.UMLDrawing.UMLDrawer;
import com.TextUML.UMLObjects.UMLClassObject;

import javax.swing.*;
import java.awt.*;

class UMLDiagramPanel extends JPanel {

    private UMLClassObject[] classesToDraw;

    public UMLDiagramPanel(UMLClassObject[] classesToDraw){
        this.classesToDraw = classesToDraw;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        UMLDrawer.Reset();
        UMLDrawer.DrawUMLDiagram(classesToDraw, g);
        UMLDrawer.DrawUMLRelations();
    }

    public void Update(){
        repaint();
    }
}
