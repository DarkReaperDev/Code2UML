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

    private int insideClassPadding = 5;

    private List<UMLClassDraw> subclassDraws = new ArrayList<UMLClassDraw>();

    public UMLClassDraw(UMLClassObject classObject){
        this.classObject = classObject;
        Initialize();
    }

    public void CreateAt(int x, int y){
        fullRectangle.x = x;
        fullRectangle.y = y;

        classRectangle.x = fullRectangle.x + fullRectangle.width/2 - classRectangle.width/2;
        classRectangle.y = fullRectangle.y;

        classSectionRectangle.x = classRectangle.x;
        classSectionRectangle.y = classRectangle.y;
        memberSectionRectangle.x = classRectangle.x;
        memberSectionRectangle.y = classRectangle.y + classSectionRectangle.height;
        methodSectionRectangle.x = classRectangle.x;
        methodSectionRectangle.y = memberSectionRectangle.y + memberSectionRectangle.height;

        subclassesRectangle.y = classRectangle.y + classRectangle.height;
        subclassesRectangle.x = fullRectangle.x + fullRectangle.width/2 - subclassesRectangle.width/2;

        SetSubclassDrawsPos();
    }

    private void SetSubclassDrawsPos(){
        int currentXPos = subclassesRectangle.x;
        int currentYPos = subclassesRectangle.y;

        for(UMLClassDraw subClassDraw : subclassDraws){
            subClassDraw.CreateAt(currentXPos, currentYPos);

            currentXPos += subClassDraw.GetFullRect().width;
        }
    }

    private void Initialize(){
        int[] sectionHeights = GetSectionHeights();
        int mainClassWidth = GetClassWidth();
        classRectangle = new Rectangle(0, 0, mainClassWidth, sectionHeights[3]);

        InitializeSubclasses();

        subclassesRectangle = new Rectangle(0, 0, GetSubclassesWidth(), GetSubclassesHeight());
        fullRectangle = new Rectangle(0, 0, GetFullRectWidth(), GetFullRectHeight());

        classSectionRectangle = new Rectangle(0, 0, mainClassWidth, sectionHeights[0]);
        memberSectionRectangle = new Rectangle(0, 0, mainClassWidth, sectionHeights[1]);
        methodSectionRectangle = new Rectangle(0, 0, mainClassWidth, sectionHeights[2]);
    }

    private void InitializeSubclasses(){
        System.out.println(classObject.getUmlSubclasses());
        for(UMLClassObject subclassObject : classObject.getUmlSubclasses()){
            UMLClassDraw subclassDraw = new UMLClassDraw(subclassObject);
            subclassDraws.add(subclassDraw);
        }
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

    private int GetSubclassesWidth(){
        int width = 0;
        for(UMLClassDraw subclassDraw: subclassDraws){
            width += subclassDraw.GetFullRect().width;
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
        return height;
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
            graphics.drawString(objectsToDraw[i].getFullString(), rect.x + insideClassPadding, rect.y + insideClassPadding + characterHeight + (characterHeight + lineSpacing) * i);
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
