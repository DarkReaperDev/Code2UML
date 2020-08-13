package com.TextUML.UMLObject;


import com.TextUML.UMLKeywords.UMLModifierKeyword;
import com.TextUML.UMLKeywords.UMLValueTypeKeyword;

public class UMLMethodObject {

    public String name;
    public UMLModifierKeyword modiefier;
    public UMLValueTypeKeyword return_type;

    public UMLMethodObject(){

    }

    public UMLMethodObject(String name){
        this.name = name;
    }

    public UMLMethodObject(String name, UMLModifierKeyword modifier){
        this.name = name;
        this.modiefier = modifier;
    }

    public UMLMethodObject(String name,UMLValueTypeKeyword return_type){
        this.name = name;
        this.return_type = return_type;
    }

    public UMLMethodObject(String name, UMLModifierKeyword modifier, UMLValueTypeKeyword return_type){
        this.name = name;
        this.modiefier = modifier;
        this.return_type = return_type;
    }
}
