package com.TextUML.UMLDiagram.UMLDrawing;

import com.TextUML.UMLObjects.UMLClassObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UMLDrawer {

    private static final int MARGIN = 20;
    private static int currentXPos = MARGIN;
    private static int currentYPos = MARGIN;

    static UMLRootClassDraw[][] rootClassDrawsArray;

    public static void DrawUMLClasses(UMLClassObject[] classesToDraw, Graphics graphics) {
        UMLClassObject[][] rootClassesAndInterfaces = GetRootInterfacesAndClasses(classesToDraw);
        List<UMLRootClassDraw[]> rootClassDraws = new ArrayList<>();

        for(UMLClassObject[] rootObjects : rootClassesAndInterfaces){
            List<UMLRootClassDraw> objectDraws = new ArrayList<>();
            int height = 0;

            for(UMLClassObject rootObject : rootObjects){
                UMLRootClassDraw rootObjectDraw = new UMLRootClassDraw(rootObject, graphics);

                rootObjectDraw.CreateAt(currentXPos, currentYPos);
                objectDraws.add(rootObjectDraw);
                rootObjectDraw.Draw();

                currentXPos += rootObjectDraw.GetFullRect().width;
                if(rootObjectDraw.GetFullRect().height > height){
                    height = rootObjectDraw.GetFullRect().height;
                }
            }
            rootClassDraws.add(objectDraws.toArray(new UMLRootClassDraw[]{}));
            currentYPos += height + MARGIN;
            currentXPos = MARGIN;
        }
        rootClassDrawsArray = rootClassDraws.toArray(new UMLRootClassDraw[][]{});
    }

    //interfaces at index 0, classes at index 1
    private static UMLClassObject[][] GetRootInterfacesAndClasses(UMLClassObject[] classesToGetFrom) {
        List<UMLClassObject> rootClasses = new ArrayList<>();
        List<UMLClassObject> rootInterfaces = new ArrayList<>();

        for (UMLClassObject classObject : classesToGetFrom) {
            if (classObject.getUmlParentName() == "") {
                if(classObject.isInterface()){
                    rootInterfaces.add(classObject);
                }
                else {
                    rootClasses.add(classObject);
                }
            }
        }
        return new UMLClassObject[][]{
                rootInterfaces.toArray(new UMLClassObject[]{}),
                rootClasses.toArray(new UMLClassObject[]{})
        };
    }

    public static void DrawUMLRelations(){
        for(UMLRootClassDraw[] rootObjectDraws : rootClassDrawsArray) {
            for (UMLRootClassDraw rootObject : rootObjectDraws) {
                rootObject.DrawRelations();
            }
        }
    }

    public static void Reset() {
        currentXPos = MARGIN;
        currentYPos = MARGIN;
    }
}