package com.TextUML.UMLObjects;

import java.util.ArrayList;
import java.util.List;

public class UMLClassObject implements UMLObject {

    List<UMLMethodObject> uml_methods = new ArrayList<>();
    List<UMLMemberObject> uml_members = new ArrayList<>();
    String name;

    public UMLClassObject(String name){
        this.name = name;
    }

    public void Add(UMLObject object){
        if(object.getClass() == UMLMemberObject.class){
            uml_members.add((UMLMemberObject) object);
        }
        else if(object.getClass() == UMLMethodObject.class){
            uml_methods.add((UMLMethodObject) object);
        }
    }

    public String getName() {
        return name;
    }
}
