package com.TextUML.UMLDiagram;

import com.TextUML.UMLObjects.UMLClassObject;
import com.TextUML.UMLObjects.UMLObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class UMLClassDraw {

    private UMLClassObject classObject;

    private Rectangle fullRectangle;
    private Rectangle classRectangle;
    private Rectangle subclassesRectangle;
    private Rectangle classSectionRectangle;
    private Rectangle memberSectionRectangle;
    private Rectangle methodSectionRectangle;

    private int characterWidth = 6;
    private int characterHeight = 9;
    private int lineSpacing = 5;

    private int subclassMargin = 20;

    private int paddingX = 5;
    private int paddingY = 5;

    private List<UMLClassDraw> subclassDraws = new ArrayList<UMLClassDraw>();

    public UMLClassDraw(UMLClassObject classObject, int posX, int posY){
        this.classObject = classObject;
        Initialize(posX, posY);
    }

    private void Initialize(int posX, int posY){
        int[] sectionHeights = GetSectionHeights();
        classRectangle = new Rectangle(posX, posY, GetClassWidth(), sectionHeights[3]);

        InitializeSubclasses();

        subclassesRectangle = AlignRectToCenterOfRect(new Rectangle(0, classRectangle.y + classRectangle.height + subclassMargin, GetSubclassesWidth(), GetSubclassesHeight()), classRectangle);
        fullRectangle = new Rectangle(GetFullRectX(), classRectangle.y, GetFullRectWidth(), GetFullRectHeight());

        classSectionRectangle = new Rectangle(classRectangle.x, classRectangle.y, classRectangle.width, sectionHeights[0]);
        memberSectionRectangle = new Rectangle(classRectangle.x, classSectionRectangle.y + classSectionRectangle.height, classRectangle.width, sectionHeights[1]);
        methodSectionRectangle = new Rectangle(classRectangle.x, memberSectionRectangle.y + memberSectionRectangle.height, classRectangle.width, sectionHeights[2]);
    }

    private void InitializeSubclasses(){
        int currentXPos = subclassMargin;

        System.out.println(classObject.getUmlSubclasses());
        for(UMLClassObject subclassObject : classObject.getUmlSubclasses()){
            UMLClassDraw subclassDraw = new UMLClassDraw(subclassObject ,currentXPos, classRectangle.y + classRectangle.height);
            subclassDraws.add(subclassDraw);

            currentXPos += subclassDraw.GetFullRect().width;
        }
    }

    private int[] GetSectionHeights(){
        //[0]height of class header, [1]height of member Section, [2] height of method section, [3] height of everything together
        int[] heights = new int[4];
        heights[0] = characterHeight + 2*paddingY;
        heights[1] = 2*paddingY + classObject.getUmlMembers().length * (characterHeight+ lineSpacing) - lineSpacing;
        heights[2] = 2*paddingY + classObject.getUmlMethods().length * (characterHeight+ lineSpacing) - lineSpacing;
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
        return longestStringSize * characterWidth + 2*paddingX;
    }

    private int GetSubclassesWidth(){
        int width = subclassMargin;
        for(UMLClassDraw subclassDraw: subclassDraws){
            width += subclassDraw.GetFullRect().width;
            width += subclassMargin;
        }
        return width;
    }

    private int GetSubclassesHeight(){
        int height = 0;
        for(UMLClassDraw subclassDraw: subclassDraws){
            if(subclassDraw.fullRectangle.height > height){
                height = subclassDraw.fullRectangle.height;
            }
        }
        return height + 2*subclassMargin;
    }

    private int GetFullRectX(){
        if(subclassesRectangle.width >= classRectangle.width){
            return subclassesRectangle.x;
        }
        else{
            return classRectangle.x;
        }
    }

    private int GetFullRectWidth(){
        if(subclassesRectangle.width >= classRectangle.width){
            return subclassesRectangle.width;
        }
        else{
            return classRectangle.width;
        }
    }

    private int GetFullRectHeight(){
        return classRectangle.height + subclassesRectangle.height;
    }


    private Rectangle AlignRectToCenterOfRect(Rectangle rectToAlign, Rectangle rectToAlignTo){
        rectToAlign.x = rectToAlignTo.x + rectToAlignTo.width /2 - rectToAlign.width /2;

        return rectToAlign;
    }

    public void Draw(Graphics graphics){
        DrawSubclasses(graphics);

        DrawUMLClassBorder(graphics);
        DrawUMLClassText(graphics);
        DrawSectionDividers(graphics);
    }

    private void DrawSubclasses(Graphics graphics){
        for(UMLClassDraw subclassDraw : subclassDraws){
            subclassDraw.Draw(graphics);
        }
    }

    private void DrawUMLClassBorder(Graphics graphics){
        graphics.drawRect(classRectangle.x, classRectangle.y, classRectangle.width, classRectangle.height);
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

    public Rectangle GetClassRect() {
        return classRectangle;
    }

    public Rectangle GetFullRect(){return fullRectangle;}

    public UMLClassObject GetClassObject(){
        return classObject;
    }
}
