package com.TextUML.UMLDiagram;

import com.TextUML.UMLObjects.UMLClassObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class UMLDrawer {

    static int currentXPos = 20;
    static int currentYPos = 20;

    public static void DrawUMLClass(UMLClassObject[] classesToDraw, Graphics graphics) {
        UMLClassObject[] rootClasses = GetRootClasses(classesToDraw);
        List<UMLClassDraw> rootClassDraws = new ArrayList<>();

        for (UMLClassObject rootClass : rootClasses) {
            UMLClassDraw rootClassDraw = new UMLClassDraw(rootClass, currentXPos, currentYPos);
            rootClassDraws.add(rootClassDraw);
            currentXPos += rootClassDraw.GetFullRect().width;

            rootClassDraw.Draw(graphics);
        }
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

    private static UMLClassDraw GetClassDrawByClassObject(UMLClassObject objectToGetWith, UMLClassDraw[] ArrayToGetFrom) {
        for (UMLClassDraw classDraw : ArrayToGetFrom) {
            if (classDraw.GetClassObject().getName() == objectToGetWith.getName()) {
                System.out.println("ok");
                return classDraw;
            }
        }
        return null;
    }

    public static void Reset() {
        currentXPos = 20;
        currentYPos = 20;
    }
}