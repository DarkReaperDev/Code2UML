package com.TextUML.DrawingSystem;

import com.TextUML.UMLObjects.UMLClassObject;

import javax.swing.*;
import java.awt.*;

public class UMLDiagramPanel extends JPanel {

    UMLClassObject[] classes_to_draw;

    public UMLDiagramPanel(UMLClassObject[] classes_to_draw){
        this.classes_to_draw = classes_to_draw;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        for(UMLClassObject uml_class : classes_to_draw) {
            DrawClass(uml_class, g);
        }
    }

    void DrawClass(UMLClassObject class_to_draw, Graphics g){
        System.out.println("ok");
        g.setColor(Color.black);
        g.drawRect(25, 25, 50, 100);

    }
}
