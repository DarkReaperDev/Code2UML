package com.TextUML.UMLKeywords;

public enum UMLBracketKeyword implements UMLKeyword {

    UMLBracketOpen("(", null),
    UMLBracketClose(")", null),
    UMLCurlyBracketOpen("{", UMLKeywordTags.OpenInnerPattern),
    UMLCurlyBracketClose("}", UMLKeywordTags.CloseInnerPattern);

    String string;
    UMLKeywordTags tag;

    UMLBracketKeyword(String string, UMLKeywordTags tag){
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
