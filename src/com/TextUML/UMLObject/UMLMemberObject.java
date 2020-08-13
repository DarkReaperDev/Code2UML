package com.TextUML.UMLObject;

import com.TextUML.UMLKeywords.UMLModifierKeyword;
import com.TextUML.UMLKeywords.UMLValueTypeKeyword;

public class UMLMemberObject {

    public String name;
    public UMLModifierKeyword modiefier;
    public UMLValueTypeKeyword value_type;

    public UMLMemberObject(){

    }

    public UMLMemberObject(String name){
        this.name = name;
    }

    public UMLMemberObject(String name, UMLModifierKeyword modifier){
        this.name = name;
        this.modiefier = modifier;
    }

    public UMLMemberObject(String name, UMLValueTypeKeyword value_type){
        this.name = name;
        this.value_type = value_type;
    }

    public UMLMemberObject(String name, UMLModifierKeyword modifier, UMLValueTypeKeyword value_type){
        this.name = name;
        this.modiefier = modifier;
        this.value_type = value_type;
    }
}
