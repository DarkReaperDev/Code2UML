package com.TextUML.UMLObject;

import com.TextUML.UMLKeywords.UMLKeywordList;

public class UMLMemberObject {

    public String name;
    public UMLKeywordList.UMLModifier modiefier;
    public UMLKeywordList.UMLValueType value_type;

    public UMLMemberObject(){

    }

    public UMLMemberObject(String name){
        this.name = name;
    }

    public UMLMemberObject(String name, UMLKeywordList.UMLModifier modiefier){
        this.name = name;
        this.modiefier = modiefier;
    }

    public UMLMemberObject(String name, UMLKeywordList.UMLValueType value_type){
        this.name = name;
        this.value_type = value_type;
    }

    public UMLMemberObject(String name, UMLKeywordList.UMLModifier modiefier, UMLKeywordList.UMLValueType value_type){
        this.name = name;
        this.modiefier = modiefier;
        this.value_type = value_type;
    }
}
