package com.TextUML.UMLDiagram.UMLDrawing;

import com.TextUML.UMLObjects.UMLClassObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UMLDrawer {

    private static final int MARGIN = 20;
    private static int currentXPos = MARGIN;
    private static int currentYPos = MARGIN;

    static UMLRootClassDraw[][] rootInterfaceAndClassDraws;

    public static void DrawUMLDiagram(UMLClassObject[] classesToDraw, Graphics graphics) {
        UMLClassObject[][] rootInterfacesAndClasses;
        UMLRootClassDraw[] rootInterfaceDraws;
        UMLRootClassDraw[] rootClassDraws;

        rootInterfacesAndClasses = GetRootInterfacesAndClasses(classesToDraw);

        rootInterfaceDraws = CreateRootInterfaces(rootInterfacesAndClasses[0], graphics);
        rootClassDraws = CreateRootClasses(rootInterfacesAndClasses[1], graphics);

        LayoutRootInterfacesByRootClasses(rootInterfaceDraws, rootClassDraws);

        for(UMLRootClassDraw rootClassDraw : rootClassDraws){
            rootClassDraw.Draw();
        }
        for(UMLRootClassDraw rootInterfaceDraw : rootInterfaceDraws){
            rootInterfaceDraw.Draw();
        }
        rootInterfaceAndClassDraws = new UMLRootClassDraw[][]{rootInterfaceDraws, rootClassDraws};
    }
    

    private static UMLRootClassDraw[] CreateRootInterfaces(UMLClassObject[] rootClassObjects, Graphics graphics){
        List<UMLRootClassDraw> objectDraws = new ArrayList<>();
        int height = 0;

        for(UMLClassObject rootObject : rootClassObjects) {
            UMLRootClassDraw rootObjectDraw = new UMLRootClassDraw(rootObject, null, graphics);

            rootObjectDraw.SetPos(currentXPos, currentYPos);
            objectDraws.add(rootObjectDraw);

            currentXPos += rootObjectDraw.GetFullRect().width;
            if (rootObjectDraw.GetFullRect().height > height) {
                height = rootObjectDraw.GetFullRect().height;
            }
        }
        currentYPos = height + MARGIN;
        currentXPos = MARGIN;
        return objectDraws.toArray(new UMLRootClassDraw[]{});
    }

    private static void LayoutRootInterfacesByRootClasses(UMLRootClassDraw[] rootInterfaceDraws, UMLRootClassDraw[] rootClassDraws){
        SetInterfaceXPosToAverageOfImplementersXPos(rootInterfaceDraws);
    }

    private static void SetInterfaceXPosToAverageOfImplementersXPos(UMLRootClassDraw[] rootInterfaceDraws){

        for(UMLRootClassDraw rootInterfaceDraw : rootInterfaceDraws){
            int xPos = 0;
            for(UMLClassObject subclass : rootInterfaceDraw.GetClassObject().getUmlSubclasses()){
                xPos = xPos + subclass.classDraw.GetFullRect().x + subclass.classDraw.GetFullRect().width / 2;
            }
            xPos = xPos / rootInterfaceDraw.GetClassObject().getUmlSubclasses().size();
            SetObjectDrawCenterXPos(rootInterfaceDraw, xPos);
            System.out.println(xPos);
        }
    }

    private static void SetObjectDrawCenterXPos(UMLRootClassDraw classDraw, int xPos){
        classDraw.SetPos(xPos - classDraw.GetFullRect().width / 2, classDraw.GetFullRect().y);
    }

    private static UMLRootClassDraw[] CreateRootClasses(UMLClassObject[] rootClasses, Graphics graphics){
        List<UMLRootClassDraw> objectDraws = new ArrayList<>();
        int height = 0;
        //calc y pos

        for(UMLClassObject rootObject : rootClasses) {
            UMLRootClassDraw rootObjectDraw = new UMLRootClassDraw(rootObject, null, graphics);

            rootObjectDraw.SetPos(currentXPos, currentYPos);
            objectDraws.add(rootObjectDraw);

            currentXPos += rootObjectDraw.GetFullRect().width;
            if (rootObjectDraw.GetFullRect().height > height) {
                height = rootObjectDraw.GetFullRect().height;
            }
        }
        return objectDraws.toArray(new UMLRootClassDraw[0]);
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
        for(UMLRootClassDraw[] rootObjectDraws : rootInterfaceAndClassDraws) {
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