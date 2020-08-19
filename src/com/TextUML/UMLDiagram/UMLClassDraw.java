package com.TextUML.UMLDiagram;

import com.TextUML.UMLObjects.UMLClassObject;
import com.TextUML.UMLObjects.UMLObject;

import java.awt.*;

class UMLClassDraw {

    UMLClassObject classObject;

    Rectangle fullClassRectangle;
    Rectangle classSectionRectangle;
    Rectangle memberSectionRectangle;
    Rectangle methodSectionRectangle;

    int characterWidth = 6;
    int characterHeight = 9;
    int lineSpacing = 5;

    int paddingX = 5;
    int paddingY = 5;


    public UMLClassDraw(UMLClassObject classObject, int posX, int posY){
        this.classObject = classObject;
        Initialize(posX, posY);
    }

    void Initialize(int posX, int posY){
        int[] sectionHeights = GetSectionHeights();
        fullClassRectangle = new Rectangle(posX, posY, GetWidth(), sectionHeights[3]);

        classSectionRectangle = new Rectangle(fullClassRectangle.x, fullClassRectangle.y, fullClassRectangle.width, sectionHeights[0]);
        memberSectionRectangle = new Rectangle(fullClassRectangle.x, classSectionRectangle.y + classSectionRectangle.height, fullClassRectangle.width, sectionHeights[1]);
        methodSectionRectangle = new Rectangle(fullClassRectangle.x, memberSectionRectangle.y + memberSectionRectangle.height, fullClassRectangle.width, sectionHeights[2]);
    }

    private int GetWidth(){
        int longestStringSize = classObject.getName().length();
        UMLObject[] objectsToDraw = classObject.getUMLMembersAndMethods();

        for(UMLObject umlObject: objectsToDraw){
            if(umlObject.getFullString().length() > longestStringSize){
                longestStringSize = umlObject.getFullString().length();
            }
        }
        return longestStringSize * characterWidth + 2*paddingX;
    }

    //[0]height of class header [1]height of member Section [2] height of method section [3] height of everything together
    private int[] GetSectionHeights(){
        int[] heights = new int[4];
        heights[0] = characterHeight + 2*paddingY;
        heights[1] = 2*paddingY + classObject.getUmlMembers().length * (characterHeight+ lineSpacing) - lineSpacing;
        heights[2] = 2*paddingY + classObject.getUmlMethods().length * (characterHeight+ lineSpacing) - lineSpacing;
        heights[3] = heights[0] + heights[1] + heights[2];
        return heights;
    }

    public void Draw(Graphics graphics){
        DrawUMLClassBorder(graphics);
        DrawUMLClassText(graphics);
        DrawSectionDividers(graphics);
    }


    private void DrawUMLClassBorder(Graphics graphics){
        graphics.drawRect(fullClassRectangle.x, fullClassRectangle.y, fullClassRectangle.width, fullClassRectangle.height);
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
            graphics.drawString(objectsToDraw[i].getFullString(), rect.x + paddingX, rect.y + paddingY + characterHeight + (characterHeight + lineSpacing) * i);
        }
    }

    private void DrawSectionDividers(Graphics graphics){
        graphics.drawLine(memberSectionRectangle.x, memberSectionRectangle.y, memberSectionRectangle.x + memberSectionRectangle.width, memberSectionRectangle.y);
        graphics.drawLine(methodSectionRectangle.x, methodSectionRectangle.y, methodSectionRectangle.x + methodSectionRectangle.width, methodSectionRectangle.y);
    }
}
