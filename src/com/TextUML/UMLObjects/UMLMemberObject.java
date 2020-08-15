package com.TextUML.UMLObjects;

import com.TextUML.UMLKeywords.UMLModifierKeyword;
import com.TextUML.UMLKeywords.UMLValueTypeKeyword;

public class UMLMemberObject implements UMLObject{

    public String name;
    public UMLModifierKeyword modifier = UMLModifierKeyword.UMlPrivate;
    public UMLValueTypeKeyword value_type = UMLValueTypeKeyword.UMLString;

    public UMLMemberObject(){

    }

    public UMLMemberObject(String name){
        this.name = name;
    }

    public UMLMemberObject(String name, UMLModifierKeyword modifier){
        this.name = name;
        this.modifier = modifier;
    }

    public UMLMemberObject(String name, UMLValueTypeKeyword value_type){
        this.name = name;
        this.value_type = value_type;
    }

    public UMLMemberObject(String name, UMLModifierKeyword modifier, UMLValueTypeKeyword value_type){
        this.name = name;
        this.modifier = modifier;
        this.value_type = value_type;
    }
}
