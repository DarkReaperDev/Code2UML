package com.TextUML.UMLDiagram;

import com.TextUML.UMLObjects.UMLClassObject;
import com.TextUML.UMLObjects.UMLMemberObject;
import com.TextUML.UMLObjects.UMLMethodObject;
import com.TextUML.UMLObjects.UMLObject;

import java.awt.*;

class UMLDrawer {

    private static Font font;
    private static int margin = 25;
    private static int padding = 5;
    private static int textSpacing = 12;

    private static int currentXPos = margin;
    private static int currentYPos = margin;

    public static void DrawUMLClass(UMLClassObject classToDraw, Graphics graphics){
        int[] sectionHeights = GetClassSectionHeights(classToDraw);
        int classHeight = sectionHeights[0] + sectionHeights[1] + sectionHeights[2];
        int classWidth = GetLongestFullStringLength(classToDraw);

        DrawUMLClassBorder(classWidth, classHeight, graphics);
        DrawUMLClassText(sectionHeights, classWidth, classToDraw, graphics);
        DrawDividerLines(sectionHeights, classWidth, graphics);
    }

    private static int GetLongestFullStringLength(UMLClassObject classObject){
        int longestStringSize = classObject.getName().length();
        UMLObject[] objectsToDraw = classObject.getUMLMembersAndMethods();

        for(UMLObject umlObject: objectsToDraw){
            if(umlObject.getFullString().length() > longestStringSize){
                longestStringSize = umlObject.getFullString().length();
            }
        }
        return longestStringSize * 6;
    }

    //[0]height of class header [1]height of member Section [2] height of method section
    private static int[] GetClassSectionHeights(UMLClassObject umlClassObject){
        int[] heights = new int[3];
        heights[0] = 20;
        heights[1] = 6 + umlClassObject.getUmlMembers().length * 14;
        heights[2] = 6 + umlClassObject.getUmlMethods().length * 14;
        return heights;
    }

    private static void DrawUMLClassBorder(int width, int height, Graphics graphics){
        graphics.drawRect(currentXPos, currentYPos, width, height);
    }

    private static void DrawUMLClassText(int[] sectionHeights, int classWidth, UMLClassObject umlClassObject, Graphics graphics){
        int textSpacing = 14;
        int currentSectionHeight = currentYPos;

        graphics.drawString(umlClassObject.getName(), currentXPos + classWidth/2 - (umlClassObject.getName().length()/2) * 6 , currentYPos + textSpacing);
        currentSectionHeight += sectionHeights[0];

        DrawUMLObjectStrings(umlClassObject.getUmlMembers(), currentXPos + padding, currentSectionHeight + textSpacing, textSpacing, graphics);
        currentSectionHeight += sectionHeights[1];
        DrawUMLObjectStrings(umlClassObject.getUmlMethods(), currentXPos + padding, currentSectionHeight + textSpacing, textSpacing, graphics);
    }

    private static void DrawUMLObjectStrings(UMLObject[] objectsToDraw, int startPosX, int startPosY, int spacing, Graphics graphics){
        for(int i = 0; i < objectsToDraw.length; i ++){
            graphics.drawString(objectsToDraw[i].getFullString(), startPosX, startPosY + spacing*i);
        }
    }

    private static void DrawDividerLines(int[] sectionHeights, int classWidth, Graphics graphics){
        int currentHeight = currentYPos;
        for(int i = 0; i < sectionHeights.length - 1; i++){
            currentHeight += sectionHeights[i];
            graphics.drawLine(currentXPos, currentHeight, currentXPos + classWidth, currentHeight);
        }

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
