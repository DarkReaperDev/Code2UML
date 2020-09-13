package com.TextUML.UMLDiagram.UMLDrawing;

import com.TextUML.UMLObjects.UMLClassObject;
import com.TextUML.UMLObjects.UMLObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UMLClassDraw {

    private UMLClassObject classObject;
    private UMLRootClassDraw rootClassDraw;
    private FontMetrics fontMetrics;

    private Rectangle fullRectangle;
    private Rectangle classRectangle;
    private Rectangle classHeaderRectangle;
    private Rectangle memberSectionRectangle;
    private Rectangle methodSectionRectangle;

    private final int LINE_SPACING = 5;
    private final int TEXT_MARGIN = 5;
    private final int CLASS_RECT_MARGIN = 10;

    public UMLClassDraw(UMLClassObject classObject, UMLRootClassDraw rootClassDraw, FontMetrics fontMetrics){
        this.classObject = classObject;
        this.rootClassDraw = rootClassDraw;
        this.fontMetrics = fontMetrics;

        classObject.classDraw = this;
        Initialize();
    }

    private void Initialize(){
        int[] sectionHeights = GetSectionHeights();
        int classWidth = GetClassWidth();

        classHeaderRectangle = new Rectangle(0, 0, classWidth, sectionHeights[0]);
        memberSectionRectangle = new Rectangle(0, 0, classWidth, sectionHeights[1]);
        methodSectionRectangle = new Rectangle(0, 0, classWidth, sectionHeights[2]);
        classRectangle = new Rectangle(0, 0, classWidth, classHeaderRectangle.height + memberSectionRectangle.height + methodSectionRectangle.height);

        fullRectangle = new Rectangle(0, 0, classRectangle.width + 2* CLASS_RECT_MARGIN, classRectangle.height + 2* CLASS_RECT_MARGIN);
    }

    private int GetClassWidth(){
        String longestString = classObject.getFullString();
        UMLObject[] objectsToDraw = classObject.getUMLMembersAndMethods();

        for(UMLObject umlObject: objectsToDraw){
            if(umlObject.getFullString().length() > longestString.length()){
                longestString = umlObject.getFullString();
            }
        }
        return fontMetrics.stringWidth(longestString) + 2* TEXT_MARGIN;
    }

    private int[] GetSectionHeights(){
        //[0]height of class header, [1]height of member Section, [2] height of method section, [3] height of everything together
        int characterHeight = fontMetrics.getHeight();
        int[] heights = new int[4];
        heights[0] = characterHeight + 2* TEXT_MARGIN;
        heights[1] = 2* TEXT_MARGIN + classObject.getUmlMembers().length * (characterHeight+ LINE_SPACING) - LINE_SPACING;
        heights[2] = 2* TEXT_MARGIN + classObject.getUmlMethods().length * (characterHeight+ LINE_SPACING) - LINE_SPACING;
        heights[3] = heights[0] + heights[1] + heights[2];
        return heights;
    }

    public void CreateAt(int x, int y){
        fullRectangle.x = x;
        fullRectangle.y = y;

        classRectangle.x = fullRectangle.x + CLASS_RECT_MARGIN;
        classRectangle.y = fullRectangle.y + CLASS_RECT_MARGIN;

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
        DrawStringCenteredInRect(classObject.getFullString(), classHeaderRectangle, graphics);

        DrawUMLObjectStrings(classObject.getUmlMembers(), memberSectionRectangle, graphics);
        DrawUMLObjectStrings(classObject.getUmlMethods(), methodSectionRectangle, graphics);
    }

    private void DrawStringCenteredInRect(String string, Rectangle rect, Graphics graphics){
        int xPos = (int)rect.getCenterX() - fontMetrics.stringWidth(string)/2;
        int yPos = (int)rect.getCenterY() + fontMetrics.getHeight() / 2;
        graphics.drawString(string, xPos, yPos);
    }

    private void DrawUMLObjectStrings(UMLObject[] objectsToDraw, Rectangle rect, Graphics graphics){
        int characterHeight = fontMetrics.getHeight();
        for(int i = 0; i < objectsToDraw.length; i ++){
            graphics.drawString(objectsToDraw[i].getFullString(), rect.x + TEXT_MARGIN, rect.y + TEXT_MARGIN + characterHeight + (characterHeight + LINE_SPACING) * i);
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

    public UMLRootClassDraw[] GetRootClassesPath(){
        List<UMLRootClassDraw> path = new ArrayList<>();
        path.add(rootClassDraw);
        while(path.get(path.size() - 1).GetParentDraw() != null){
            path.add(path.get(path.size() - 1).GetParentDraw());
        }
        return path.toArray(new UMLRootClassDraw[]{});
    }

    public Rectangle GetFullRect(){return fullRectangle;}

    public UMLClassObject GetClassObject(){
        return classObject;
    }
}