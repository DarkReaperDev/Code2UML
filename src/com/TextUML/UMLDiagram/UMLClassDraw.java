package com.TextUML.UMLDiagram;

import com.TextUML.UMLObjects.UMLClassObject;
import com.TextUML.UMLObjects.UMLObject;

import java.awt.*;

public class UMLClassDraw {

    private UMLClassObject classObject;

    private Rectangle fullRectangle;
    private Rectangle classSectionRectangle;
    private Rectangle memberSectionRectangle;
    private Rectangle methodSectionRectangle;


    private int characterWidth = 6;
    private int characterHeight = 9;
    private int lineSpacing = 5;

    private int insideClassPadding = 5;

    public UMLClassDraw(UMLClassObject classObject){
        this.classObject = classObject;
        Initialize();
    }

    private void Initialize(){
        int[] sectionHeights = GetSectionHeights();
        int classWidth = GetClassWidth();
        fullRectangle = new Rectangle(0, 0, classWidth, sectionHeights[3]);

        classSectionRectangle = new Rectangle(0, 0, classWidth, sectionHeights[0]);
        memberSectionRectangle = new Rectangle(0, 0, classWidth, sectionHeights[1]);
        methodSectionRectangle = new Rectangle(0, 0, classWidth, sectionHeights[2]);
    }

    public void CreateAt(int x, int y){
        fullRectangle.x = x;
        fullRectangle.y = y;

        classSectionRectangle.x = fullRectangle.x;
        classSectionRectangle.y = fullRectangle.y;
        memberSectionRectangle.x = fullRectangle.x;
        memberSectionRectangle.y = fullRectangle.y + classSectionRectangle.height;
        methodSectionRectangle.x = fullRectangle.x;
        methodSectionRectangle.y = memberSectionRectangle.y + memberSectionRectangle.height;
    }

    private int[] GetSectionHeights(){
        //[0]height of class header, [1]height of member Section, [2] height of method section, [3] height of everything together
        int[] heights = new int[4];
        heights[0] = characterHeight + 2* insideClassPadding;
        heights[1] = 2*insideClassPadding + classObject.getUmlMembers().length * (characterHeight+ lineSpacing) - lineSpacing;
        heights[2] = 2*insideClassPadding + classObject.getUmlMethods().length * (characterHeight+ lineSpacing) - lineSpacing;
        heights[3] = heights[0] + heights[1] + heights[2];
        return heights;
    }

    private int GetClassWidth(){
        int longestStringSize = classObject.getFullString().length();
        UMLObject[] objectsToDraw = classObject.getUMLMembersAndMethods();

        for(UMLObject umlObject: objectsToDraw){
            if(umlObject.getFullString().length() > longestStringSize){
                longestStringSize = umlObject.getFullString().length();
            }
        }
        return longestStringSize * characterWidth + 2* insideClassPadding;
    }

    public void Draw(Graphics graphics){
        DrawUMLClassBorder(graphics);
        DrawUMLClassText(graphics);
        DrawSectionDividers(graphics);
    }

    private void DrawUMLClassBorder(Graphics graphics){
        graphics.drawRect(fullRectangle.x, fullRectangle.y, fullRectangle.width, fullRectangle.height);
    }

    private void DrawUMLClassText(Graphics graphics){

        DrawStringCentered(classObject.getFullString(), classSectionRectangle, graphics);

        DrawUMLObjectStrings(classObject.getUmlMembers(), memberSectionRectangle, graphics);
        DrawUMLObjectStrings(classObject.getUmlMethods(), methodSectionRectangle, graphics);
    }

    private void DrawStringCentered(String string, Rectangle rect, Graphics graphics){
        int xPos = (int)rect.getCenterX() - string.length() / 2 * characterWidth;
        int yPos = (int)rect.getCenterY() + characterHeight / 2;
        graphics.drawString(string, xPos, yPos);
    }

    private void DrawUMLObjectStrings(UMLObject[] objectsToDraw, Rectangle rect, Graphics graphics){
        for(int i = 0; i < objectsToDraw.length; i ++){
            graphics.drawString(objectsToDraw[i].getFullString(), rect.x + insideClassPadding, rect.y + insideClassPadding + characterHeight + (characterHeight + lineSpacing) * i);
        }
    }

    private void DrawSectionDividers(Graphics graphics){
        graphics.drawLine(memberSectionRectangle.x, memberSectionRectangle.y, memberSectionRectangle.x + memberSectionRectangle.width, memberSectionRectangle.y);
        graphics.drawLine(methodSectionRectangle.x, methodSectionRectangle.y, methodSectionRectangle.x + methodSectionRectangle.width, methodSectionRectangle.y);
    }

    public Rectangle GetFullRect(){return fullRectangle;}

    public UMLClassObject GetClassObject(){
        return classObject;
    }
}