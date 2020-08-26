package com.TextUML.UMLKeywords;

import com.TextUML.UMLKeywords.KeywordTypes.*;

public class ActiveUMLKeywordTypes {
    private static UMLKeywordType[] keywordArray = {
            UMLModifierKeywordType.UMLPublic,
            UMLModifierKeywordType.UMlPrivate,

            UMLValueTypeKeywordType.UMLString,
            UMLValueTypeKeywordType.UMLInt,
            UMLValueTypeKeywordType.UMLFloat,
            UMLValueTypeKeywordType.UMLBool,
            UMLValueTypeKeywordType.UMLVoid,

            UMLStructureKeywordType.UMLClass,

            UMLRelationKeywordType.UMLExtends,
            UMLRelationKeywordType.UMLImplements,

            UMLBracketKeywordType.UMLBracketOpen,
            UMLBracketKeywordType.UMLBracketClose,
            UMLBracketKeywordType.UMLCurlyBracketOpen,
            UMLBracketKeywordType.UMLCurlyBracketClose,

            UMLEndLineKeywordType.UMLSemiColon
    };

    public static UMLKeywordType GetKeywordTypeFromString(String keywordString){
        for(UMLKeywordType keyword : keywordArray){
            if(keyword.GetString().equals(keywordString)){
                return keyword;
            }
        }
        return new UMLNameKeywordType(keywordString);
    }
}
