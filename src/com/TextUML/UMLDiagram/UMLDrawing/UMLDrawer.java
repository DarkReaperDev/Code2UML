package com.TextUML.UMLDiagram.UMLDrawing;

import com.TextUML.UMLObjects.UMLClassObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UMLDrawer {

    private static final int MARGIN = 20;
    private static int currentXPos = MARGIN;
    private static int currentYPos = MARGIN;

    static UMLRootClassDraw[][] rootInterfaceAndClassDraws;

    public static void DrawUMLDiagram(UMLClassObject[] classesToDraw, Graphics graphics) {
        System.out.println("start drawing");
        UMLClassObject[][] rootInterfacesAndClasses;
        UMLRootClassDraw[] rootInterfaceDraws;
        UMLRootClassDraw[] rootClassDraws;

        rootInterfacesAndClasses = GetRootInterfacesAndClasses(classesToDraw);

        rootInterfaceDraws = CreateRootInterfaces(rootInterfacesAndClasses[0], graphics);
        rootClassDraws = CreateRootClasses(rootInterfacesAndClasses[1], graphics);

        LayoutRootInterfacesByImplementers(rootInterfaceDraws);



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

    private static void LayoutRootInterfacesByImplementers(UMLRootClassDraw[] rootInterfaceDraws){
        SetInterfaceXPosToAverageOfImplementersXPos(rootInterfaceDraws);
        rootInterfaceDraws = SortRootObjectsByFullRectX(rootInterfaceDraws);

        ArrayList<UMLRootClassDraw>[] drawsGroups = WrapRootObjectsInLists(rootInterfaceDraws);

        int lastStepDrawsGroupsLength;
        do{
            lastStepDrawsGroupsLength = drawsGroups.length;
            drawsGroups = AdjustRootDrawPos(drawsGroups);
        }
        while(lastStepDrawsGroupsLength != drawsGroups.length); // if they are same length, no changes were made -> adjusting is finished

        AdjustDrawsToScreenBorder(rootInterfaceDraws);
    }

    private static void SetInterfaceXPosToAverageOfImplementersXPos(UMLRootClassDraw[] rootInterfaceDraws){

        for(UMLRootClassDraw rootInterfaceDraw : rootInterfaceDraws){
            if(rootInterfaceDraw.GetClassObject().getUmlSubclasses().size() == 0){
                break;
            }
            int xPos = 0;
            for(UMLClassObject subclass : rootInterfaceDraw.GetClassObject().getUmlSubclasses()){
                xPos = xPos + subclass.classDraw.GetFullRect().x + subclass.classDraw.GetFullRect().width / 2;
            }
            xPos = xPos / rootInterfaceDraw.GetClassObject().getUmlSubclasses().size();
            SetObjectDrawCenterXPos(rootInterfaceDraw, xPos);
        }
    }

    private static void SetObjectDrawCenterXPos(UMLRootClassDraw classDraw, int xPos){
        classDraw.SetPos(xPos - classDraw.GetFullRect().width / 2, classDraw.GetFullRect().y);
    }

    private static UMLRootClassDraw[] SortRootObjectsByFullRectX(UMLRootClassDraw[] classesToSort){
        List<UMLRootClassDraw> sortedClasses = new ArrayList<>();
        sortedClasses.add(classesToSort[0]);
        for(int i = 0; i < classesToSort.length; i++){
            for(int x = 0; x < sortedClasses.size(); x++){
                if(classesToSort[i].GetFullRect().x < sortedClasses.get(x).GetFullRect().x){
                    sortedClasses.add(x, classesToSort[i]);
                    break;
                }
            }
            if(!sortedClasses.contains(classesToSort[i])){
                sortedClasses.add(classesToSort[i]);
            }
        }
        return sortedClasses.toArray(new UMLRootClassDraw[0]);
    }

    private static ArrayList<UMLRootClassDraw>[] WrapRootObjectsInLists(UMLRootClassDraw[] rootObjects){
        ArrayList<ArrayList<UMLRootClassDraw>> wrappedObjects = new ArrayList<>();

        for(UMLRootClassDraw rootObject : rootObjects){
            wrappedObjects.add(new ArrayList<>(Arrays.asList(new UMLRootClassDraw[]{rootObject})));
        }
        return wrappedObjects.toArray(new ArrayList[0]);
    }

    private static ArrayList<UMLRootClassDraw>[] AdjustRootDrawPos(ArrayList<UMLRootClassDraw>[] drawsGroups){
        ArrayList<ArrayList<UMLRootClassDraw>> adjustedObjects = new ArrayList<>();

        for(int i = 0; i < drawsGroups.length; i++){
            adjustedObjects.add(drawsGroups[i]);

            if(i < drawsGroups.length - 1) {

                int leftGroupRightEdgeX = drawsGroups[i].get(drawsGroups[i].size() - 1).GetFullRect().x + drawsGroups[i].get(drawsGroups[i].size() - 1).GetFullRect().width;
                int rightGroupLeftEdgeX = drawsGroups[i + 1].get(0).GetFullRect().x;

                if (leftGroupRightEdgeX > rightGroupLeftEdgeX) {
                    MoveDrawsGroupsAway(drawsGroups[i], drawsGroups[i + 1]);
                    adjustedObjects.get(adjustedObjects.size() - 1).addAll(drawsGroups[i + 1]);
                    i++;
                }
            }
        }
        return adjustedObjects.toArray(new ArrayList[0]);
    }

    private static void MoveDrawsGroupsAway(ArrayList<UMLRootClassDraw> leftGroup, ArrayList<UMLRootClassDraw> rightGroup){
        int dist = leftGroup.get(leftGroup.size() - 1).GetFullRect().x + leftGroup.get(leftGroup.size() - 1).GetFullRect().width - rightGroup.get(0).GetFullRect().x;

        MoveDrawsGroup(leftGroup, -dist / 2);
        MoveDrawsGroup(rightGroup, dist / 2);
    }

    private static void MoveDrawsGroup(ArrayList<UMLRootClassDraw> group, int dist){
        for(UMLRootClassDraw draw : group){
            draw.SetPos(draw.GetFullRect().x + dist, draw.GetFullRect().y);
        }
    }

    private static void AdjustDrawsToScreenBorder(UMLRootClassDraw[] rootDraws){
        if(rootDraws[0].GetFullRect().x < MARGIN){
            rootDraws[0].SetPos(MARGIN, rootDraws[0].GetFullRect().y);

            for(int i = 0; i < rootDraws.length - 1; i++){
                if(rootDraws[i].GetFullRect().x + rootDraws[i].GetFullRect().width > rootDraws[i + 1].GetFullRect().x){
                    rootDraws[i + 1].SetPos(rootDraws[i].GetFullRect().x + rootDraws[i].GetFullRect().width, rootDraws[i + 1].GetFullRect().y);
                }
                else{
                    break;
                }
            }
        }
    }

    private static UMLRootClassDraw[] CreateRootClasses(UMLClassObject[] rootClasses, Graphics graphics){
        List<UMLRootClassDraw> objectDraws = new ArrayList<>();
        int height = 0;

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