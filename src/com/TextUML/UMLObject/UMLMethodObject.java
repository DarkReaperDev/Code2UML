package com.TextUML.UMLObject;

public class UMLMethodObject {

    public String name;
    public UMLTypes.UMLModiefier modiefier;
    public UMLTypes.UMLValueType return_type;

    public UMLMethodObject(){

    }

    public UMLMethodObject(String name){
        this.name = name;
    }

    public UMLMethodObject(String name, UMLTypes.UMLModiefier modiefier){
        this.name = name;
        this.modiefier = modiefier;
    }

    public UMLMethodObject(String name, UMLTypes.UMLValueType return_type){
        this.name = name;
        this.return_type = return_type;
    }

    public UMLMethodObject(String name, UMLTypes.UMLModiefier modiefier, UMLTypes.UMLValueType return_type){
        this.name = name;
        this.modiefier = modiefier;
        this.return_type = return_type;
    }
}
