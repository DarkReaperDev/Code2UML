package com.TextUML.UMLKeywords.KeywordTypes;

import com.TextUML.UMLKeywords.UMLKeywordTags;

public class UMLNameKeywordType implements UMLKeywordType {

    String string;

    public UMLNameKeywordType(String string){
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
