package com.TextUML.UMLObjects;

import com.TextUML.UMLKeywords.KeywordTypes.UMLModifierKeywordType;
import com.TextUML.UMLKeywords.KeywordTypes.UMLValueTypeKeywordType;
import com.TextUML.Utilities.Strings.MultilineString;

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

    public String GetName() {
        return name;
    }

    public MultilineString getFullString(){
        return new MultilineString(modifier.GetString() + " " + valueType.GetString() + " " + name);
    }

    public UMLModifierKeywordType getModifier() {
        return modifier;
    }

    public UMLValueTypeKeywordType getValueType() {
        return valueType;
    }
}
