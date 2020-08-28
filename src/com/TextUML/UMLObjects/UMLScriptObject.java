package com.TextUML.UMLObjects;

import java.util.ArrayList;
import java.util.List;

public class UMLScriptObject {

    List<UMLClassObject> umlClasses = new ArrayList<>();


    public UMLScriptObject(){
        umlClasses = new ArrayList<UMLClassObject>();
    }

    public void LinkUMLClassesAndCreateMissing(){
        int count = umlClasses.size(); //counter for loop needs to be declared before, cause list size changes while loop is running

        for(int i = 0; i < count; i ++){
            if(umlClasses.get(i).umlParentName != ""){
                umlClasses.get(i).ParentClass = GetClassObjectOrCreateNew(umlClasses.get(i).umlParentName);
            }
            if(umlClasses.get(i).umlInterfaceName != ""){
                umlClasses.get(i).ImplementedInterface = GetClassObjectOrCreateNew(umlClasses.get(i).umlInterfaceName);
            }
        }
    }

    public UMLClassObject GetClassObjectOrCreateNew(String objectName){
        UMLClassObject object = GetClassByName(objectName);
        if(object == null){
            object = new UMLClassObject(objectName);
            umlClasses.add(object);
        }
        return object;
    }

    public UMLClassObject GetClassByName(String name){
        for(UMLClassObject classObject : umlClasses){
            if(classObject.name == name){
                return classObject;
            }
        }
        return null;
    }

    public void AddUMLClass(UMLClassObject uml_class){
        umlClasses.add(uml_class);
    }
    
    public UMLClassObject[] GetUMLClassObjects(){
        return(umlClasses.toArray(new UMLClassObject[]{}));
    }
}
