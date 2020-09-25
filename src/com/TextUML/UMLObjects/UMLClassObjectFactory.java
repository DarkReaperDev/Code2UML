package com.TextUML.UMLObjects;

import java.util.ArrayList;

public class UMLClassObjectFactory {

    private static ArrayList<UMLClassObject> classObjects = new ArrayList<>();

    public static UMLClassObject CreateNewClassObject(String name){
        int newID = classObjects.size();
        UMLClassObject object = new UMLClassObject(name, newID);
        classObjects.add(object);
        return object;
    }

    public UMLClassObject[] GetAllCreatedObjects(){
        return classObjects.toArray(new UMLClassObject[0]);
    }
}
