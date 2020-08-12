package com.TextUML.UMLObject;

import com.TextUML.UMLKeywords.UMLTypes;

public class UMLMethodObject {

    public String name;
    public UMLTypes.UMLModifier modiefier;
    public UMLTypes.UMLValueType return_type;

    public UMLMethodObject(){

    }

    public UMLMethodObject(String name){
        this.name = name;
    }

    public UMLMethodObject(String name, UMLTypes.UMLModifier modiefier){
        this.name = name;
        this.modiefier = modiefier;
    }

    public UMLMethodObject(String name, UMLTypes.UMLValueType return_type){
        this.name = name;
        this.return_type = return_type;
    }

    public UMLMethodObject(String name, UMLTypes.UMLModifier modiefier, UMLTypes.UMLValueType return_type){
        this.name = name;
        this.modiefier = modiefier;
        this.return_type = return_type;
    }
}
