package com.TextUML.UMLKeywords.KeywordTypes;

import com.TextUML.UMLKeywords.UMLKeywordTags;

public enum UMLRelationKeywordType implements UMLKeywordType{

    UMLExtends("extends", null),
    UMLImplements("implements", null);


    String string;
    UMLKeywordTags tag;

    UMLRelationKeywordType(String string, UMLKeywordTags tag){
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
