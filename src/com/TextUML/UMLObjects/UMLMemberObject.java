package com.TextUML.UMLObjects;

import com.TextUML.UMLKeywords.UMLModifierKeyword;
import com.TextUML.UMLKeywords.UMLValueTypeKeyword;

public class UMLMemberObject implements UMLObject{

    String name;
    UMLModifierKeyword modifier = UMLModifierKeyword.UMlPrivate;
    UMLValueTypeKeyword valueType = UMLValueTypeKeyword.UMLString;

    public UMLMemberObject(String name){
        this.name = name;
    }

    public UMLMemberObject(String name, UMLModifierKeyword modifier){
        this.name = name;
        this.modifier = modifier;
    }

    public UMLMemberObject(String name, UMLValueTypeKeyword valueType){
        this.name = name;
        this.valueType = valueType;
    }

    public UMLMemberObject(String name, UMLModifierKeyword modifier, UMLValueTypeKeyword valueType){
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

    public UMLModifierKeyword getModifier() {
        return modifier;
    }

    public UMLValueTypeKeyword getValueType() {
        return valueType;
    }
}
