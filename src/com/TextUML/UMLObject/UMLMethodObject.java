package com.TextUML.UMLObject;

import com.TextUML.UMLKeywords.UMLKeywordList;

public class UMLMethodObject {

    public String name;
    public UMLKeywordList.UMLModifier modiefier;
    public UMLKeywordList.UMLValueType return_type;

    public UMLMethodObject(){

    }

    public UMLMethodObject(String name){
        this.name = name;
    }

    public UMLMethodObject(String name, UMLKeywordList.UMLModifier modiefier){
        this.name = name;
        this.modiefier = modiefier;
    }

    public UMLMethodObject(String name, UMLKeywordList.UMLValueType return_type){
        this.name = name;
        this.return_type = return_type;
    }

    public UMLMethodObject(String name, UMLKeywordList.UMLModifier modiefier, UMLKeywordList.UMLValueType return_type){
        this.name = name;
        this.modiefier = modiefier;
        this.return_type = return_type;
    }
}
