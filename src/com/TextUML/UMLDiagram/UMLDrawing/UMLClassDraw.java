package com.TextUML.UMLDiagram.UMLDrawing;

import com.TextUML.UMLObjects.UMLClassObject;
import com.TextUML.UMLObjects.UMLMemberObject;
import com.TextUML.UMLObjects.UMLMethodObject;
import com.TextUML.UMLObjects.UMLObject;
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
}