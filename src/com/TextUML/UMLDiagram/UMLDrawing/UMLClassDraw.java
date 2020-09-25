package com.TextUML.UMLDiagram.UMLDrawing;

import com.TextUML.UMLObjects.UMLClassObject;
import com.TextUML.UMLObjects.UMLMemberObject;
import com.TextUML.UMLObjects.UMLMethodObject;
import com.TextUML.Utilities.Strings.MultilineString;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UMLClassDraw {

    MultilineString classNameString;
    MultilineString classMembersStrings;
    MultilineString classMethodsStrings;
    boolean isInterfaceDraw;

    int[] subclassesIDs;

    private FontMetrics fontMetrics;

    private Rectangle fullRectangle;
    private Rectangle classRectangle;
    private Rectangle classHeaderRectangle;
    private Rectangle memberSectionRectangle;
    private Rectangle methodSectionRectangle;

    private final int LINE_SPACING = 5;
    private final int TEXT_MARGIN = 5;
    private final int CLASS_RECT_MARGIN = 10;

    UMLClassDraw(UMLClassObject classObject, FontMetrics metrics){
        this.fontMetrics = metrics;
        this.classNameString = classObject.getFullString();
        this.isInterfaceDraw = classObject.IsInterface();
        this.subclassesIDs = classObject.GetUmlSubclassesIDs();

        List<String> memberStrings = new ArrayList<>();
        for(UMLMemberObject member : classObject.GetUmlMembers()){
            memberStrings.add(member.getFullString());
        }
        classMembersStrings = new MultilineString(memberStrings.toArray(new String[0]));

        List<String> methodStrings = new ArrayList<>();
        for(UMLMethodObject method : classObject.GetUmlMethods()){
            methodStrings.add(method.getFullString());
        }
        classMethodsStrings = new MultilineString(methodStrings.toArray(new String[0]));

    }

    private void InitializeRectangles(){
        int[] sectionHeights = GetSectionHeights();
        int classWidth = GetClassWidth();

        classHeaderRectangle = new Rectangle(0, 0, classWidth, sectionHeights[0]);
        memberSectionRectangle = new Rectangle(0, 0, classWidth, sectionHeights[1]);
        methodSectionRectangle = new Rectangle(0, 0, classWidth, sectionHeights[2]);
        classRectangle = new Rectangle(0, 0, classWidth, classHeaderRectangle.height + memberSectionRectangle.height + methodSectionRectangle.height);

        fullRectangle = new Rectangle(0, 0, classRectangle.width + 2* CLASS_RECT_MARGIN, classRectangle.height + 2* CLASS_RECT_MARGIN);
    }

    private int[] GetSectionHeights(){
        //[0]height of class header, [1]height of member Section, [2] height of method section, [3] height of everything together
        int characterHeight = fontMetrics.getHeight();
        int[] heights = new int[4];
        heights[0] = 2* TEXT_MARGIN + classNameString.GetSize() * (characterHeight+ LINE_SPACING) - LINE_SPACING;
        heights[1] = 2* TEXT_MARGIN + classMembersStrings.GetSize() * (characterHeight+ LINE_SPACING) - LINE_SPACING;
        heights[2] = 2* TEXT_MARGIN + classMethodsStrings.GetSize() * (characterHeight+ LINE_SPACING) - LINE_SPACING;
        heights[3] = heights[0] + heights[1] + heights[2];
        return heights;
    }

    private int GetClassWidth(){
        int longestStringSize = 0;

        for(MultilineString s : new MultilineString[]{classNameString, classMembersStrings, classMethodsStrings}){
            int longestSizeOfS = s.GetLongestStringSize(fontMetrics);
            if(longestSizeOfS > longestStringSize){
                longestStringSize = longestSizeOfS;
            }
        }
        return longestStringSize + 2* TEXT_MARGIN;
    }

    public void SetPos(Point pos){
        fullRectangle.x = pos.x;
        fullRectangle.y = pos.y;

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
        DrawUMLClass(graphics);
        DrawUMLClassText(graphics);
    }

    private void DrawUMLClass(Graphics graphics){
        graphics.drawRect(classRectangle.x, classRectangle.y, classRectangle.width, classRectangle.height);
        graphics.drawLine(memberSectionRectangle.x, memberSectionRectangle.y, memberSectionRectangle.x + memberSectionRectangle.width, memberSectionRectangle.y);
        graphics.drawLine(methodSectionRectangle.x, methodSectionRectangle.y, methodSectionRectangle.x + methodSectionRectangle.width, methodSectionRectangle.y);
    }

    private void DrawUMLClassText(Graphics graphics){
        DrawStringCenteredInRect(classNameString, classHeaderRectangle, graphics);

        DrawStringCenteredInRect(classMembersStrings, memberSectionRectangle, graphics);
        DrawStringCenteredInRect(classMethodsStrings, methodSectionRectangle, graphics);
    }

    private void DrawStringCenteredInRect(String string, Rectangle rect, Graphics graphics){
        int xPos = (int)rect.getCenterX() - fontMetrics.stringWidth(string)/2;
        int yPos = (int)rect.getCenterY() + fontMetrics.getHeight() / 2;
        graphics.drawString(string, xPos, yPos);
    }

    private void DrawStringCenteredInRect(MultilineString string, Rectangle rect, Graphics graphics){
        int xPos = (int)rect.getCenterX() - string.GetLongestStringSize(fontMetrics)/2;
        int yPos = (int)rect.getCenterY() + ((fontMetrics.getHeight() + LINE_SPACING) * string.GetSize() - LINE_SPACING) / 2;
        DrawMultilineString(string, new Point(xPos, yPos), graphics);
    }

    private void DrawMultilineString(MultilineString string, Point pos, Graphics graphics){
        Point currentPos = pos;
        for(String s : string.GetAsArray()){
            graphics.drawString(s, currentPos.x, currentPos.y);
            currentPos.y += fontMetrics.getHeight() + LINE_SPACING;
        }
    }

    public void DrawRelations(){

    }
}