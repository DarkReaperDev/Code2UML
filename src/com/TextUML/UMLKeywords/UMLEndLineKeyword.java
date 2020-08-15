package com.TextUML.UMLKeywords;

public enum UMLEndLineKeyword implements UMLKeyword{
    UMLSemiColon(";");

    String string;

    UMLEndLineKeyword(String string){
        this.string = string;
    }

    @Override
    public String GetString() {
        return string;
    }
}
