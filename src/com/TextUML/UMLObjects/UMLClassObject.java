package com.TextUML.UMLObjects;

import java.util.ArrayList;
import java.util.List;

public class UMLClassObject implements UMLObject {


    List<UMLMethodObject> umlMethods = new ArrayList<>();
    List<UMLMemberObject> umlMembers = new ArrayList<>();
    String name;

    public UMLClassObject(String name){
        this.name = name;
    }

    public void Add(UMLObject object){
        if(object.getClass() == UMLMemberObject.class){
            umlMembers.add((UMLMemberObject) object);
        }
        else if(object.getClass() == UMLMethodObject.class){
            umlMethods.add((UMLMethodObject) object);
        }
    }

    public String getName() {
        return name;
    }

    public UMLMethodObject[] getUmlMethods() {
        return umlMethods.toArray(new UMLMethodObject[]{});
    }

    public UMLMemberObject[] getUmlMembers() {
        return umlMembers.toArray(new UMLMemberObject[]{});
    }

}
