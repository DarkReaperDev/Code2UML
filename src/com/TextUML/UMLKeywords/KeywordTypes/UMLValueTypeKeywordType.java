package com.TextUML.UMLKeywords.KeywordTypes;

import com.TextUML.UMLKeywords.UMLKeywordTags;

public enum UMLValueTypeKeywordType implements UMLKeywordType {

    UMLString("string"),
    UMLInt("int"),
    UMLFloat("float"),
    UMLBool("bool"),
    UMLVoid("void");

    String string;

    UMLValueTypeKeywordType(String string){
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
