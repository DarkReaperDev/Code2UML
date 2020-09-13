package com.TextUML.UMLDiagram.UMLDrawing;

import com.TextUML.UMLObjects.UMLClassObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class UMLRootClassDraw {

    private UMLClassObject classObject;
    private UMLRootClassDraw parentClassDraw;
    private Graphics graphics;

    private Rectangle fullRectangle;
    private Rectangle subclassesRectangle;

    private UMLClassDraw mainClassDraw;

    private List<UMLRootClassDraw> subclassDraws = new ArrayList<UMLRootClassDraw>();

    public UMLRootClassDraw(UMLClassObject classObject, UMLRootClassDraw parentClassDraw, Graphics graphics){
        this.classObject = classObject;
        this.parentClassDraw = parentClassDraw;
        this.graphics = graphics;
        Initialize();
    }

    private void Initialize(){
        InitializeSubclasses();

        mainClassDraw = new UMLClassDraw(classObject,this,  graphics.getFontMetrics());
        subclassesRectangle = new Rectangle(0, 0, GetSubclassesWidth(), GetSubclassesHeight());
        fullRectangle = new Rectangle(0, 0, GetFullRectWidth(), GetFullRectHeight());
    }

    private void InitializeSubclasses(){
        if(classObject.isInterface()){return;}

        for(UMLClassObject subclassObject : classObject.getUmlSubclasses()){
            UMLRootClassDraw subclassDraw = new UMLRootClassDraw(subclassObject, this, graphics);
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
        if(classObject.isInterface()){return;}

        int currentXPos = subclassesRectangle.x;
        int currentYPos = subclassesRectangle.y;

        for(UMLRootClassDraw subClassDraw : subclassDraws){
            subClassDraw.CreateAt(currentXPos, currentYPos);

            currentXPos += subClassDraw.GetFullRect().width;
        }
    }

    public void Draw(){
        mainClassDraw.Draw(graphics);
        DrawSubclasses(graphics);
    }

    private void DrawSubclasses(Graphics graphics){
        if(classObject.isInterface()){return;}

        for(UMLRootClassDraw subclassDraw : subclassDraws){
            subclassDraw.Draw();
        }
    }

    public void DrawRelations(){
        mainClassDraw.DrawRelations(graphics);
        for(UMLRootClassDraw subclassDraw : subclassDraws){
            subclassDraw.DrawRelations();
        }
    }

    public Rectangle GetClassRect() {
        return mainClassDraw.GetFullRect();
    }

    public Rectangle GetFullRect(){return fullRectangle;}

    public UMLRootClassDraw GetParentDraw(){
        return parentClassDraw;
    }

    public UMLClassObject GetClassObject(){
        return classObject;
    }
}
