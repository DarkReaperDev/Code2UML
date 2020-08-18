package com.TextUML.UMLKeywords;

public class UMLKeywordList {
    private static UMLKeyword[] keywordArray = {
            UMLModifierKeyword.UMLPublic,
            UMLModifierKeyword.UMlPrivate,

            UMLValueTypeKeyword.UMLString,
            UMLValueTypeKeyword.UMLInt,
            UMLValueTypeKeyword.UMLFloat,
            UMLValueTypeKeyword.UMLBool,
            UMLValueTypeKeyword.UMLVoid,

            UMLStructureKeyword.UMLClass,

            UMLBracketKeyword.UMLBracketOpen,
            UMLBracketKeyword.UMLBracketClose,
            UMLBracketKeyword.UMLCurlyBracketOpen,
            UMLBracketKeyword.UMLCurlyBracketClose,

            UMLEndLineKeyword.UMLSemiColon
    };

    public static UMLKeyword GetKeywordForString(String keywordString){
        for(UMLKeyword keyword : keywordArray){
            if(keyword.GetString().equals(keywordString)){
                return keyword;
            }
        }
        return null;
    }
}
