package com.TextUML.UMLKeywords;

import com.TextUML.UMLKeywords.KeywordTypes.*;

public class UMLKeywordList {
    private static UMLKeywordType[] keywordArray = {
            UMLModifierKeywordType.UMLPublic,
            UMLModifierKeywordType.UMlPrivate,

            UMLValueTypeKeywordType.UMLString,
            UMLValueTypeKeywordType.UMLInt,
            UMLValueTypeKeywordType.UMLFloat,
            UMLValueTypeKeywordType.UMLBool,
            UMLValueTypeKeywordType.UMLVoid,

            UMLStructureKeywordType.UMLClass,

            UMLBracketKeywordType.UMLBracketOpen,
            UMLBracketKeywordType.UMLBracketClose,
            UMLBracketKeywordType.UMLCurlyBracketOpen,
            UMLBracketKeywordType.UMLCurlyBracketClose,

            UMLEndLineKeywordType.UMLSemiColon
    };

    public static UMLKeywordType GetKeywordTypeForString(String keywordString){
        for(UMLKeywordType keyword : keywordArray){
            if(keyword.GetString().equals(keywordString)){
                return keyword;
            }
        }
        return new UMLNameKeywordType(keywordString);
    }
}
