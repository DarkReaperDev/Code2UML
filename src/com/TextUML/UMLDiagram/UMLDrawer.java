package com.TextUML.UMLDiagram;

import com.TextUML.UMLObjects.UMLClassObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class UMLDrawer {

    private static Font font;
    private static int margin = 25;
    private static List<UMLClassDraw> drawnClasses = new ArrayList<>();

    public static void DrawUMLClass(UMLClassObject classToDraw, Graphics graphics){
        UMLClassDraw classDraw = new UMLClassDraw(classToDraw, GetCurrentXPos(), GetCurrentYPos());
        classDraw.Draw(graphics);
        drawnClasses.add(classDraw);
    }

    public static void Reset(){
        drawnClasses = new ArrayList<>();
    }

    private static int GetCurrentXPos(){
        if(drawnClasses.isEmpty()){
            return margin;
        }
        else{
            Rectangle lastDrawnClassRect = drawnClasses.get(drawnClasses.size()-1).GetFullRect();
            return lastDrawnClassRect.x + lastDrawnClassRect.width + margin;
        }
    }

    private static int GetCurrentYPos(){
        if(drawnClasses.isEmpty()){
            return margin;
        }
        else{
            Rectangle lastDrawnClassRect = drawnClasses.get(drawnClasses.size()-1).GetFullRect();
            return lastDrawnClassRect.y;
        }
    }


    public static void SetFont(Font font) {
        UMLDrawer.font = font;
    }

    public static void SetMargin(int margin) {
        UMLDrawer.margin = margin;
    }
}
