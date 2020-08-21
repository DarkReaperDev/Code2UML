package com.TextUML.UMLKeywords.KeywordTypes;

import com.TextUML.UMLKeywords.UMLKeywordTags;

public enum UMLEndLineKeywordType implements UMLKeywordType {
    UMLSemiColon(";", UMLKeywordTags.EndPattern);

    String string;
    UMLKeywordTags tag;

    UMLEndLineKeywordType(String string, UMLKeywordTags tag){
        this.string = string;
        this.tag = tag;
    }

    @Override
    public String GetString() {
        return string;
    }

    @Override
    public boolean HasTag(UMLKeywordTags tag) {
        return this.tag == tag;
    }
}
