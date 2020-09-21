package com.TextUML.UMLObjects;


import com.TextUML.UMLKeywords.KeywordTypes.UMLModifierKeywordType;
import com.TextUML.UMLKeywords.KeywordTypes.UMLValueTypeKeywordType;

public class UMLMethodObject implements UMLObject{

    String name;
    UMLModifierKeywordType modifier = UMLModifierKeywordType.UMlPrivate;
    UMLValueTypeKeywordType returnType = UMLValueTypeKeywordType.UMLVoid;

    public UMLMethodObject(){

    }

    public UMLMethodObject(String name){
        this.name = name;
    }

    public UMLMethodObject(String name, UMLModifierKeywordType modifier){
        this.name = name;
        this.modifier = modifier;
    }

    public UMLMethodObject(String name, UMLValueTypeKeywordType returnType){
        this.name = name;
        this.returnType = returnType;
    }

    public UMLMethodObject(String name, UMLModifierKeywordType modifier, UMLValueTypeKeywordType returnType){
        this.name = name;
        this.modifier = modifier;
        this.returnType = returnType;
    }

    public String GetName() {
        return name;
    }

    public String getFullString(){
        return modifier.GetString() + " " + returnType.GetString() + " " + name + "()";
    }

    public UMLModifierKeywordType getModifier() {
        return modifier;
    }

    public UMLValueTypeKeywordType getReturnType() {
        return returnType;
    }
}
