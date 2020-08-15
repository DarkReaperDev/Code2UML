package com.TextUML.UMLKeywords;

public enum UMLStructureKeyword implements UMLKeyword{
    UMLClass("class");

    String string;

    UMLStructureKeyword(String string){
        this.string = string;
    }

    @Override
    public String GetString() {
        return string;
    }
}
