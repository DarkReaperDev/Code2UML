package com.TextUML.UMLKeywords.KeywordTypes;

import com.TextUML.UMLKeywords.UMLKeywordTags;

public enum UMLStructureKeywordType implements UMLKeywordType {
    UMLClass("class");

    String string;

    UMLStructureKeywordType(String string){
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
