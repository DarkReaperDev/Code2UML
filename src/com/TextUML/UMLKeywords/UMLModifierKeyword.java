package com.TextUML.UMLKeywords;

public enum UMLModifierKeyword implements UMLKeyword {

    UMLPublic("public"),
    UMlPrivate("private");


    String string;

    UMLModifierKeyword(String string){
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
