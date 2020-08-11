package com.TextUML.UMLObject;

import java.util.List;

public class UMLClassObject {

    List<UMLMethodObject> uml_methods;
    List<UMLMemberObject> uml_members;

    public UMLClassObject(){

    }

    public void AddUMLMethod(UMLMethodObject uml_method){
        uml_methods.add(uml_method);
    }

    public void AddUMLMember(UMLMemberObject uml_member){
        uml_members.add(uml_member);
    }

    public UMLMethodObject GetUMLMethodByIndex(){
        return null;
    }

    public UMLMethodObject GetUMLMethodByName(){
        return null;
    }

    public UMLMemberObject GetUMLMemberByIndex(){
        return null;
    }

    public UMLMemberObject GetUMLMemberByName(){
        return null;
    }
}
