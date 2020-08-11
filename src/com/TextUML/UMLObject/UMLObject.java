package com.TextUML.UMLObject;

import java.util.ArrayList;
import java.util.List;

public class UMLObject {

    List<UMLClassObject> uml_classes;


    public UMLObject(){
        uml_classes = new ArrayList<UMLClassObject>();
    }

    public void AddUMLClass(UMLClassObject uml_class){
        uml_classes.add(uml_class);
    }

    public UMLClassObject GetUMLClassByIdex(){
        return null;
    }

    public UMLClassObject GetUMLClassByName(){
        return null;
    }

}
