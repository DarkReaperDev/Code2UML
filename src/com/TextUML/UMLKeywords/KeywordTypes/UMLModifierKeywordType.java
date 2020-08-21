package com.TextUML.UMLKeywords.KeywordTypes;

import com.TextUML.UMLKeywords.UMLKeywordTags;

public enum UMLModifierKeywordType implements UMLKeywordType {

    UMLPublic("public"),
    UMlPrivate("private");


    String string;

    UMLModifierKeywordType(String string){
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
