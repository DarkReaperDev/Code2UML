package com.TextUML.UMLObjects;


import com.TextUML.UMLKeywords.UMLModifierKeyword;
import com.TextUML.UMLKeywords.UMLValueTypeKeyword;

public class UMLMethodObject implements UMLObject{

    public String name;
    public UMLModifierKeyword modifier;
    public UMLValueTypeKeyword return_type;

    public UMLMethodObject(){

    }

    public UMLMethodObject(String name){
        this.name = name;
    }

    public UMLMethodObject(String name, UMLModifierKeyword modifier){
        this.name = name;
        this.modifier = modifier;
    }

    public UMLMethodObject(String name,UMLValueTypeKeyword return_type){
        this.name = name;
        this.return_type = return_type;
    }

    public UMLMethodObject(String name, UMLModifierKeyword modifier, UMLValueTypeKeyword return_type){
        this.name = name;
        this.modifier = modifier;
        this.return_type = return_type;
    }

    @Override
    public void Add(UMLObject object) {

    }
}
