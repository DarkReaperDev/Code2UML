package com.TextUML.UMLDiagram;

import com.TextUML.UMLObjects.UMLClassObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class UMLDrawer {

    static int currentXPos = 20;
    static int currentYPos = 20;

    static UMLRootClassDraw[] rootClassDrawsArray;

    public static void DrawUMLClasses(UMLClassObject[] classesToDraw, Graphics graphics) {
        UMLClassObject[] rootClasses = GetRootClasses(classesToDraw);
        List<UMLRootClassDraw> rootClassDraws = new ArrayList<>();

        for (UMLClassObject rootClass : rootClasses) {
            UMLRootClassDraw rootClassDraw = new UMLRootClassDraw(rootClass, graphics);
            rootClassDraw.CreateAt(currentXPos, currentYPos);
            rootClassDraws.add(rootClassDraw);
            currentXPos += rootClassDraw.GetFullRect().width;

            rootClassDraw.Draw();
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
        currentXPos = 20;
        currentYPos = 20;
    }
}