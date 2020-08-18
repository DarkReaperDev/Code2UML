package com.TextUML.UMLKeywords;

public enum UMLEndLineKeyword implements UMLKeyword{
    UMLSemiColon(";", UMLKeywordTags.EndPattern);

    String string;
    UMLKeywordTags tag;

    UMLEndLineKeyword(String string, UMLKeywordTags tag){
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
