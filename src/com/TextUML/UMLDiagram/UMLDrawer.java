package com.TextUML.UMLDiagram;

import com.TextUML.UMLObjects.UMLClassObject;

import java.awt.*;

class UMLDrawer {

    private static Font font;
    private static int margin = 25;
    private static int padding = 25;

    private static int currentXPos = margin;
    private static int currentYPos = margin;

    public static void DrawUMLClass(UMLClassObject classToDraw, Graphics graphics){
        int classSizeX = classToDraw.getName().length() * 8;
        int classSizeY = 20;

        int classNameOffsetX = 12;
        int classNameOffsetY = 14;

        graphics.setColor(Color.BLACK);
        graphics.drawRect(currentXPos, currentYPos, classSizeX, classSizeY);

        graphics.drawString(classToDraw.getName(), currentXPos + classNameOffsetX, currentYPos + classNameOffsetY);
    }


    public static void SetFont(Font font) {
        UMLDrawer.font = font;
    }

    public static void SetMargin(int margin) {
        UMLDrawer.margin = margin;
    }

    public static void SetPadding(int padding) {
        UMLDrawer.padding = padding;
    }




}
