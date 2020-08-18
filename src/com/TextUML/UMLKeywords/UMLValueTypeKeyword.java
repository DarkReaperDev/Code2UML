package com.TextUML.UMLKeywords;

public enum UMLValueTypeKeyword implements UMLKeyword{

    UMLString("string"),
    UMLInt("int"),
    UMLFloat("float"),
    UMLBool("bool"),
    UMLVoid("void");

    String string;

    UMLValueTypeKeyword(String string){
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
