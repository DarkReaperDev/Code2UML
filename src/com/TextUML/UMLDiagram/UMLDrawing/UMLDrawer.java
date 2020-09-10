package com.TextUML.UMLDiagram.UMLDrawing;

import com.TextUML.UMLObjects.UMLClassObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UMLDrawer {

    private static final int MARGIN = 20;
    private static int currentXPos = MARGIN;
    private static int currentYPos = MARGIN;

    static UMLRootClassDraw[] rootClassDrawsArray;

    public static void DrawUMLClasses(UMLClassObject[] classesToDraw, Graphics graphics) {
        UMLClassObject[] rootClasses = GetRootClasses(classesToDraw);
        List<UMLRootClassDraw> rootClassDraws = new ArrayList<>();

        for (UMLClassObject rootClass : rootClasses) {
            UMLRootClassDraw rootClassDraw = new UMLRootClassDraw(rootClass, graphics);

            rootClassDraw.CreateAt(currentXPos, currentYPos);
            rootClassDraws.add(rootClassDraw);
            rootClassDraw.Draw();

            currentXPos += rootClassDraw.GetFullRect().width;
        }
        rootClassDrawsArray = rootClassDraws.toArray(new UMLRootClassDraw[]{});
    }

    private static UMLClassObject[] GetRootClasses(UMLClassObject[] classesToGetFrom) {
        List<UMLClassObject> rootClasses = new ArrayList<>();
        for (UMLClassObject classObject : classesToGetFrom) {
            if (classObject.getUmlParentName() == "") {
                rootClasses.add(classObject);
            }
        }
        return rootClasses.toArray(new UMLClassObject[]{});
    }

    public static void DrawUMLRelations(){
        for(UMLRootClassDraw rootClass : rootClassDrawsArray){
            rootClass.DrawRelations();
        }
    }

    public static void Reset() {
        currentXPos = MARGIN;
        currentYPos = MARGIN;
    }
}