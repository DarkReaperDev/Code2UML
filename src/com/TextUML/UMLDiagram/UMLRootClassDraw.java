package com.TextUML.UMLDiagram;

import com.TextUML.UMLObjects.UMLClassObject;
import com.TextUML.UMLObjects.UMLObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class UMLRootClassDraw {

    private UMLClassObject classObject;

    private Rectangle fullRectangle;
    private Rectangle subclassesRectangle;

    private UMLClassDraw mainClassDraw;


    private int characterWidth = 6;
    private int characterHeight = 9;
    private int lineSpacing = 5;

    private int insideClassPadding = 5;

    private List<UMLRootClassDraw> subclassDraws = new ArrayList<UMLRootClassDraw>();

    public UMLRootClassDraw(UMLClassObject classObject){
        this.classObject = classObject;
        Initialize();
    }

    public void CreateAt(int x, int y){

        fullRectangle.x = x;
        fullRectangle.y = y;

        int classDrawPosX = fullRectangle.x + fullRectangle.width/2 - mainClassDraw.GetFullRect().width/2;
        int classDrawPosY = fullRectangle.y;

        mainClassDraw.CreateAt(classDrawPosX, classDrawPosY);

        subclassesRectangle.y = mainClassDraw.GetFullRect().y + mainClassDraw.GetFullRect().height;
        subclassesRectangle.x = fullRectangle.x + fullRectangle.width/2 - subclassesRectangle.width/2;
        
        SetSubclassDrawsPos();
    }

    private void SetSubclassDrawsPos(){
        int currentXPos = subclassesRectangle.x;
        int currentYPos = subclassesRectangle.y;

        for(UMLRootClassDraw subClassDraw : subclassDraws){
            subClassDraw.CreateAt(currentXPos, currentYPos);

            currentXPos += subClassDraw.GetFullRect().width;
        }
    }

    private void Initialize(){
        InitializeSubclasses();

        mainClassDraw = new UMLClassDraw(classObject);

        subclassesRectangle = new Rectangle(0, 0, GetSubclassesWidth(), GetSubclassesHeight());
        fullRectangle = new Rectangle(0, 0, GetFullRectWidth(), GetFullRectHeight());

    }

    private void InitializeSubclasses(){
        for(UMLClassObject subclassObject : classObject.getUmlSubclasses()){
            UMLRootClassDraw subclassDraw = new UMLRootClassDraw(subclassObject);
            subclassDraws.add(subclassDraw);
        }
    }

    private int GetSubclassesWidth(){
        int width = 0;
        for(UMLRootClassDraw subclassDraw: subclassDraws){
            width += subclassDraw.GetFullRect().width;
        }
        return width;
    }

    private int GetSubclassesHeight(){
        int height = 0;
        for(UMLRootClassDraw subclassDraw: subclassDraws){
            if(subclassDraw.fullRectangle.height > height){
                height = subclassDraw.fullRectangle.height;
            }
        }
        return height;
    }

    private int GetFullRectX(){
        if(subclassesRectangle.width >= mainClassDraw.GetFullRect().width){
            return subclassesRectangle.x;
        }
        else{
            return mainClassDraw.GetFullRect().x;
        }
    }

    private int GetFullRectWidth(){
        if(subclassesRectangle.width >= mainClassDraw.GetFullRect().width){
            return subclassesRectangle.width;
        }
        else{
            return mainClassDraw.GetFullRect().width;
        }
    }

    private int GetFullRectHeight(){
        return mainClassDraw.GetFullRect().height + subclassesRectangle.height;
    }

    public void Draw(Graphics graphics){
        mainClassDraw.Draw(graphics);
        DrawSubclasses(graphics);
    }

    private void DrawSubclasses(Graphics graphics){
        for(UMLRootClassDraw subclassDraw : subclassDraws){
            subclassDraw.Draw(graphics);
        }
    }

    public Rectangle GetClassRect() {
        return mainClassDraw.GetFullRect();
    }

    public Rectangle GetFullRect(){return fullRectangle;}

    public UMLClassObject GetClassObject(){
        return classObject;
    }
}
