package com.TextUML.UMLKeywords;

public enum UMLBracketKeyword implements UMLKeyword {

    UMLBracketOpen("("),
    UMLBracketClose(")"),
    UMLCurlyBracketOpen("{"),
    UMLCurlyBracketClose("}");

    String string;

    UMLBracketKeyword(String string){
        this.string = string;
    }

    @Override
    public String GetString() {
        return string;
    }

}
