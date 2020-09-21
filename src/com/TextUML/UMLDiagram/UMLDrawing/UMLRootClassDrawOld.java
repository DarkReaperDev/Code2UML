package com.TextUML.UMLDiagram.UMLDrawing;

import com.TextUML.UMLObjects.UMLClassObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class UMLRootClassDrawOld {

    private UMLClassObject classObject;
    private UMLRootClassDrawOld parentClassDraw;
    private Graphics graphics;

    private Rectangle fullRectangle;
    private Rectangle subclassesRectangle;

    private UMLClassDraw mainClassDraw;

    private List<UMLRootClassDrawOld> subclassDraws = new ArrayList<UMLRootClassDrawOld>();

    public UMLRootClassDrawOld(UMLClassObject classObject, UMLRootClassDrawOld parentClassDraw, Graphics graphics){
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
        if(classObject.IsInterface()){return;}

        for(UMLClassObject subclassObject : classObject.GetUmlSubclasses()){
            UMLRootClassDrawOld subclassDraw = new UMLRootClassDrawOld(subclassObject, this, graphics);
            subclassDraws.add(subclassDraw);
        }
    }

    private int GetSubclassesWidth(){
        int width = 0;
        for(UMLRootClassDrawOld subclassDraw: subclassDraws){
            width += subclassDraw.GetFullRect().width;
        }
        return width;
    }

    private int GetSubclassesHeight(){
        int height = 0;
        for(UMLRootClassDrawOld subclassDraw: subclassDraws){
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

    public void SetPos(int x, int y){
        fullRectangle.x = x;
        fullRectangle.y = y;

        int classDrawPosX = fullRectangle.x + fullRectangle.width/2 - mainClassDraw.GetFullRect().width/2;
        int classDrawPosY = fullRectangle.y;
        mainClassDraw.SetPos(classDrawPosX, classDrawPosY);

        subclassesRectangle.y = mainClassDraw.GetFullRect().y + mainClassDraw.GetFullRect().height;
        subclassesRectangle.x = fullRectangle.x + fullRectangle.width/2 - subclassesRectangle.width/2;

        SetSubclassDrawsPos();
    }

    private void SetSubclassDrawsPos(){
        if(classObject.IsInterface()){return;}

        int currentXPos = subclassesRectangle.x;
        int currentYPos = subclassesRectangle.y;

        for(UMLRootClassDrawOld subClassDraw : subclassDraws){
            subClassDraw.SetPos(currentXPos, currentYPos);

            currentXPos += subClassDraw.GetFullRect().width;
        }
    }

    public void Draw(){
        mainClassDraw.Draw(graphics);
        DrawSubclasses(graphics);
    }

    private void DrawSubclasses(Graphics graphics){
        if(classObject.IsInterface()){return;}

        for(UMLRootClassDrawOld subclassDraw : subclassDraws){
            subclassDraw.Draw();
        }
    }

    public void DrawRelations(){
        mainClassDraw.DrawRelations(graphics);
        for(UMLRootClassDrawOld subclassDraw : subclassDraws){
            subclassDraw.DrawRelations();
        }
    }

    public Rectangle GetClassRect() {
        return mainClassDraw.GetFullRect();
    }

    public Rectangle GetFullRect(){return fullRectangle;}

    public UMLRootClassDrawOld GetParentDraw(){
        return parentClassDraw;
    }

    public UMLClassObject GetClassObject(){
        return classObject;
    }

    public UMLRootClassDrawOld[] GetSubclassDraws(){
        return subclassDraws.toArray(new UMLRootClassDrawOld[0]);
    }
}
