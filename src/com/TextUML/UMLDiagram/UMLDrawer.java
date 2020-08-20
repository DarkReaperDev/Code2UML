package com.TextUML.UMLDiagram;

import com.TextUML.UMLObjects.UMLClassObject;

import java.awt.*;

class UMLDrawer {

    private static Font font;
    private static int margin = 25;

    private static int currentXPos = margin;
    private static int currentYPos = margin;

    public static void DrawUMLClass(UMLClassObject classToDraw, Graphics graphics){
        UMLClassDraw classDraw = new UMLClassDraw(classToDraw, currentXPos, currentYPos);
        classDraw.Draw(graphics);
    }


    public static void SetFont(Font font) {
        UMLDrawer.font = font;
    }

    public static void SetMargin(int margin) {
        UMLDrawer.margin = margin;
    }
}
