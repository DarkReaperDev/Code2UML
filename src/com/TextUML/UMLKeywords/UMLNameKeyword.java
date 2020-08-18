package com.TextUML.UMLKeywords;

public class UMLNameKeyword implements UMLKeyword {

    String string;

    public UMLNameKeyword(String string){
        this.string = string;
    }

    @Override
    public String GetString() {
        return string;
    }

    @Override
    public boolean HasTag(UMLKeywordTags tag) {
        return false;
    }
}
