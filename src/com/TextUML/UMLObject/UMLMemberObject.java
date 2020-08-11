package com.TextUML.UMLObject;

public class UMLMemberObject {

    public String name;
    public UMLTypes.UMLModiefier modiefier;
    public UMLTypes.UMLValueType value_type;

    public UMLMemberObject(){

    }

    public UMLMemberObject(String name){
        this.name = name;
    }

    public UMLMemberObject(String name, UMLTypes.UMLModiefier modiefier){
        this.name = name;
        this.modiefier = modiefier;
    }

    public UMLMemberObject(String name, UMLTypes.UMLValueType value_type){
        this.name = name;
        this.value_type = value_type;
    }

    public UMLMemberObject(String name, UMLTypes.UMLModiefier modiefier, UMLTypes.UMLValueType value_type){
        this.name = name;
        this.modiefier = modiefier;
        this.value_type = value_type;
    }
}
