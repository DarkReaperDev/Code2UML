package com.TextUML.UMLObjects;

import java.util.ArrayList;
import java.util.List;

public class UMLScriptObject {

    List<UMLClassObject> uml_classes = new ArrayList<>();


    public UMLScriptObject(){
        uml_classes = new ArrayList<UMLClassObject>();
    }

    public void AddUMLClass(UMLClassObject uml_class){
        uml_classes.add(uml_class);
    }

    public UMLClassObject GetUMLClassByIndex(){
        return null;
    }

    public UMLClassObject GetUMLClassByName(){
        return null;
    }

    public void AddUMLObject(UMLObject object){
        System.out.println(object.getClass());
    }

    public UMLClassObject[] GetUMLClassObjects(){
        return(uml_classes.toArray(new UMLClassObject[]{}));
    }
}
