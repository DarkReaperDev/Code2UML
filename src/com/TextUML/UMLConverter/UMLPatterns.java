package com.TextUML.UMLConverter;

import com.TextUML.UMLErrorHandling.UMLScriptSyntaxError;
import com.TextUML.UMLKeywords.KeywordTypes.*;
import com.TextUML.UMLKeywords.UMLKeyword;
import com.TextUML.UMLObjects.UMLClassObject;
import com.TextUML.UMLObjects.UMLMemberObject;
import com.TextUML.UMLObjects.UMLMethodObject;
import com.TextUML.UMLObjects.UMLObject;

class UMLPatterns {
    static UMLPattern[] patterns = {
            //class, name, {
            new UMLPattern(new Class<?>[]{UMLStructureKeywordType.UMLClass.getClass(), UMLNameKeywordType.class, UMLBracketKeywordType.UMLCurlyBracketOpen.getClass()}),
            //value_type, name, ;
            new UMLPattern(new Class<?>[]{UMLValueTypeKeywordType.class, UMLNameKeywordType.class, UMLEndLineKeywordType.UMLSemiColon.getClass()}),
            //modifier, value_type, name, ;
            new UMLPattern(new Class<?>[]{UMLModifierKeywordType.class, UMLValueTypeKeywordType.class, UMLNameKeywordType.class, UMLEndLineKeywordType.UMLSemiColon.getClass()}),
            //value_type, name, (, ), ;
            new UMLPattern(new Class<?>[]{UMLValueTypeKeywordType.class, UMLNameKeywordType.class, UMLBracketKeywordType.UMLBracketOpen.getClass(), UMLBracketKeywordType.UMLBracketClose.getClass(), UMLEndLineKeywordType.UMLSemiColon.getClass()}),
            //modifier, value_type, name, (, ), ;
            new UMLPattern(new Class<?>[]{UMLModifierKeywordType.class, UMLValueTypeKeywordType.class, UMLNameKeywordType.class, UMLBracketKeywordType.UMLBracketOpen.getClass(), UMLBracketKeywordType.UMLBracketClose.getClass(), UMLEndLineKeywordType.UMLSemiColon.getClass()})
    };

    static int GetMatchingPatternsId(UMLKeyword[] keywords) throws UMLScriptSyntaxError {

        for(int i = 0; i < patterns.length; i++){
            if(patterns[i].Matches(keywords)){
                return i;
            }
        }
        throw new UMLScriptSyntaxError("invalid syntax", keywords[keywords.length - 1].GetLineInUMLScript());
    }

    static UMLObject ConvertKeywordsToUMLObjectUsingPattern(UMLKeyword[] keywords, int pattern_id) throws UMLScriptSyntaxError{
        switch (pattern_id){
            case 0:{
                return ConvertClassDeclaration(keywords);
            }
            case 1:{
                return ConvertMember(keywords);
            }
            case 2:{
                return ConvertMemberWithModifier(keywords);
            }
            case 3:{
                return ConvertMethod(keywords);
            }
            case 4:{
                return ConvertMethodWithModifier(keywords);
            }
        }
        throw new UMLScriptSyntaxError("invalid syntax", keywords[keywords.length - 1].GetLineInUMLScript());
    }

    //class, name
    private static UMLObject ConvertClassDeclaration(UMLKeyword[] keywords) {
        UMLObject object = new UMLClassObject(keywords[1].GetKeywordString());
        return object;
    }

    //value_type, name, ;
    private static UMLObject ConvertMember(UMLKeyword[] keywords) {
        UMLObject object = new UMLMemberObject(keywords[1].GetKeywordString(), (UMLValueTypeKeywordType)keywords[0].GetType());
        return object;
    }

    //modifier, value_type, name, ;
    private static UMLObject ConvertMemberWithModifier(UMLKeyword[] keywords) {
        UMLObject object = new UMLMemberObject(keywords[2].GetKeywordString(), (UMLModifierKeywordType) keywords[0].GetType(), (UMLValueTypeKeywordType)keywords[1].GetType());
        return object;
    }

    //value_type, name, (
    private static UMLObject ConvertMethod(UMLKeyword[] keywords) {
        UMLObject object = new UMLMethodObject(keywords[1].GetKeywordString(), (UMLValueTypeKeywordType)keywords[0].GetType());
        return object;
    }

    //modifier, value_type, name, (
    private static UMLObject ConvertMethodWithModifier(UMLKeyword[] keywords) {
        UMLObject object = new UMLMethodObject(keywords[2].GetKeywordString(), (UMLModifierKeywordType) keywords[0].GetType(), (UMLValueTypeKeywordType)keywords[1].GetType());
        return object;
    }

    private static class UMLPattern {
        private Class<?>[] patternKeywords;

        public UMLPattern(Class<?>[] keywords) {
            this.patternKeywords = keywords;
        }

        public boolean Matches(UMLKeyword[] keywords) {
            if (keywords.length != patternKeywords.length) {
                return false;
            }
            for (int i = 0; i < keywords.length; i++) {
                if (!(keywords[i].GetType().getClass() == patternKeywords[i])) {
                    return false;
                }
            }
            return true;
        }
    }
}
