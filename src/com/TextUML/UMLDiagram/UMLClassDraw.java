package com.TextUML.UMLDiagram;

import com.TextUML.UMLObjects.UMLClassObject;
import com.TextUML.UMLObjects.UMLObject;

import java.awt.*;

public class UMLClassDraw {

    private UMLClassObject classObject;
    private FontMetrics fontMetrics;

    private Rectangle fullRectangle;
    private Rectangle classRectangle;
    private Rectangle classHeaderRectangle;
    private Rectangle memberSectionRectangle;
    private Rectangle methodSectionRectangle;

    private int lineSpacing = 5;

    private int insideClassPadding = 5;
    private int margin = 10;

    public UMLClassDraw(UMLClassObject classObject, FontMetrics fontMetrics){
        this.classObject = classObject;
        classObject.classDraw = this;
        this.fontMetrics = fontMetrics;
        Initialize();
    }

    private void Initialize(){
        int[] sectionHeights = GetSectionHeights();
        int classWidth = GetClassWidth();
        classHeaderRectangle = new Rectangle(0, 0, classWidth, sectionHeights[0]);
        memberSectionRectangle = new Rectangle(0, 0, classWidth, sectionHeights[1]);
        methodSectionRectangle = new Rectangle(0, 0, classWidth, sectionHeights[2]);
        classRectangle = new Rectangle(0, 0, classWidth, classHeaderRectangle.height + memberSectionRectangle.height + methodSectionRectangle.height);

        fullRectangle = new Rectangle(0, 0, classRectangle.width + 2*margin, classRectangle.height + 2*margin);
    }

    private int GetClassWidth(){
        String longestString = classObject.getFullString();
        UMLObject[] objectsToDraw = classObject.getUMLMembersAndMethods();

        for(UMLObject umlObject: objectsToDraw){
            if(umlObject.getFullString().length() > longestString.length()){
                longestString = umlObject.getFullString();
            }
        }
        return fontMetrics.stringWidth(longestString) + 2* insideClassPadding;
    }

    private int[] GetSectionHeights(){
        //[0]height of class header, [1]height of member Section, [2] height of method section, [3] height of everything together
        int characterHeight = fontMetrics.getHeight();
        int[] heights = new int[4];
        heights[0] = characterHeight + 2* insideClassPadding;
        heights[1] = 2*insideClassPadding + classObject.getUmlMembers().length * (characterHeight+ lineSpacing) - lineSpacing;
        heights[2] = 2*insideClassPadding + classObject.getUmlMethods().length * (characterHeight+ lineSpacing) - lineSpacing;
        heights[3] = heights[0] + heights[1] + heights[2];
        return heights;
    }

    public void CreateAt(int x, int y){
        fullRectangle.x = x;
        fullRectangle.y = y;

        classRectangle.x = fullRectangle.x + margin;
        classRectangle.y = fullRectangle.y + margin;

        classHeaderRectangle.x = classRectangle.x;
        classHeaderRectangle.y = classRectangle.y;
        memberSectionRectangle.x = classRectangle.x;
        memberSectionRectangle.y = classRectangle.y + classHeaderRectangle.height;
        methodSectionRectangle.x = classRectangle.x;
        methodSectionRectangle.y = memberSectionRectangle.y + memberSectionRectangle.height;
    }

    public void Draw(Graphics graphics){
        DrawUMLClassBorder(graphics);
        DrawUMLClassText(graphics);
        DrawSectionDividers(graphics);
    }

    private void DrawUMLClassBorder(Graphics graphics){
        graphics.drawRect(classRectangle.x, classRectangle.y, classRectangle.width, classRectangle.height);
    }

    private void DrawUMLClassText(Graphics graphics){

        DrawStringCentered(classObject.getFullString(), classHeaderRectangle, graphics);

        DrawUMLObjectStrings(classObject.getUmlMembers(), memberSectionRectangle, graphics);
        DrawUMLObjectStrings(classObject.getUmlMethods(), methodSectionRectangle, graphics);
    }

    private void DrawStringCentered(String string, Rectangle rect, Graphics graphics){
        int xPos = (int)rect.getCenterX() - fontMetrics.stringWidth(string)/2;
        int yPos = (int)rect.getCenterY() + fontMetrics.getHeight() / 2;
        graphics.drawString(string, xPos, yPos);
    }

    private void DrawUMLObjectStrings(UMLObject[] objectsToDraw, Rectangle rect, Graphics graphics){
        int characterHeight = fontMetrics.getHeight();
        for(int i = 0; i < objectsToDraw.length; i ++){
            graphics.drawString(objectsToDraw[i].getFullString(), rect.x + insideClassPadding, rect.y + insideClassPadding + characterHeight + (characterHeight + lineSpacing) * i);
        }
    }

    private void DrawSectionDividers(Graphics graphics){
        graphics.drawLine(memberSectionRectangle.x, memberSectionRectangle.y, memberSectionRectangle.x + memberSectionRectangle.width, memberSectionRectangle.y);
        graphics.drawLine(methodSectionRectangle.x, methodSectionRectangle.y, methodSectionRectangle.x + methodSectionRectangle.width, methodSectionRectangle.y);
    }

    public void DrawRelations(Graphics graphics){

        for(UMLClassObject umlSubclass : classObject.getUmlSubclasses()){
            int fromX = umlSubclass.classDraw.classRectangle.x + umlSubclass.classDraw.classRectangle.width/2;
            int fromY = umlSubclass.classDraw.classRectangle.y;
            int toX = this.classRectangle.x + this.classRectangle.width/2;
            int toY = this.classRectangle.y + this.classRectangle.height;

            DrawRelationLine(fromX, fromY, toX, toY, graphics);
        }
    }

    private void DrawRelationLine(int fromX, int fromY, int toX, int toY, Graphics graphics){
        if(this.classObject.isInterface()){
            System.out.println("works");
            Graphics2D g2d = (Graphics2D) graphics.create();

            Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
            g2d.setStroke(dashed);
            g2d.drawLine(fromX, fromY, toX, toY);

            g2d.dispose();
        }
        else {
            graphics.drawLine(fromX, fromY, toX, toY);
        }
    }

    public Rectangle GetFullRect(){return fullRectangle;}

    public UMLClassObject GetClassObject(){
        return classObject;
    }
}