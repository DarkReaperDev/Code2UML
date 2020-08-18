package com.TextUML.UMLObjects;


import com.TextUML.UMLKeywords.UMLModifierKeyword;
import com.TextUML.UMLKeywords.UMLValueTypeKeyword;

public class UMLMethodObject implements UMLObject{

    String name;
    UMLModifierKeyword modifier = UMLModifierKeyword.UMlPrivate;
    UMLValueTypeKeyword returnType = UMLValueTypeKeyword.UMLVoid;

    public UMLMethodObject(){

    }

    public UMLMethodObject(String name){
        this.name = name;
    }

    public UMLMethodObject(String name, UMLModifierKeyword modifier){
        this.name = name;
        this.modifier = modifier;
    }

    public UMLMethodObject(String name,UMLValueTypeKeyword returnType){
        this.name = name;
        this.returnType = returnType;
    }

    public UMLMethodObject(String name, UMLModifierKeyword modifier, UMLValueTypeKeyword returnType){
        this.name = name;
        this.modifier = modifier;
        this.returnType = returnType;
    }

    public String getName() {
        return name;
    }

    public String getFullString(){
        return modifier.GetString() + " " + returnType.GetString() + " " + name + "()";
    }

    public UMLModifierKeyword getModifier() {
        return modifier;
    }

    public UMLValueTypeKeyword getReturnType() {
        return returnType;
    }
}
