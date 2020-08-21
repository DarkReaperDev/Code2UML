package com.TextUML.UMLObjects;

import com.TextUML.UMLKeywords.KeywordTypes.UMLModifierKeywordType;
import com.TextUML.UMLKeywords.KeywordTypes.UMLValueTypeKeywordType;

public class UMLMemberObject implements UMLObject{

    String name;
    UMLModifierKeywordType modifier = UMLModifierKeywordType.UMlPrivate;
    UMLValueTypeKeywordType valueType = UMLValueTypeKeywordType.UMLString;

    public UMLMemberObject(String name){
        this.name = name;
    }

    public UMLMemberObject(String name, UMLModifierKeywordType modifier){
        this.name = name;
        this.modifier = modifier;
    }

    public UMLMemberObject(String name, UMLValueTypeKeywordType valueType){
        this.name = name;
        this.valueType = valueType;
    }

    public UMLMemberObject(String name, UMLModifierKeywordType modifier, UMLValueTypeKeywordType valueType){
        this.name = name;
        this.modifier = modifier;
        this.valueType = valueType;
    }

    public String getName() {
        return name;
    }

    public String getFullString(){
        return modifier.GetString() + " " + valueType.GetString() + " " + name;
    }

    public UMLModifierKeywordType getModifier() {
        return modifier;
    }

    public UMLValueTypeKeywordType getValueType() {
        return valueType;
    }
}
