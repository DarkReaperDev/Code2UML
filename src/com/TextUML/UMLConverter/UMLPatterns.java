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
            //0 -> class, name, {
            new UMLPattern(new Class<?>[]{UMLStructureKeywordType.UMLClass.getClass(), UMLNameKeywordType.class, UMLBracketKeywordType.UMLCurlyBracketOpen.getClass()}),

            //1 -> class, name, extends, name, {
            new UMLPattern(new Class<?>[]{UMLStructureKeywordType.UMLClass.getClass(), UMLNameKeywordType.class, UMLRelationKeywordType.UMLExtends.getClass(),
                    UMLNameKeywordType.class, UMLBracketKeywordType.UMLCurlyBracketOpen.getClass()}),

            //2 -> class, name, implements, name, {
            new UMLPattern(new Class<?>[]{UMLStructureKeywordType.UMLClass.getClass(), UMLNameKeywordType.class, UMLRelationKeywordType.UMLImplements.getClass(),
                    UMLNameKeywordType.class, UMLBracketKeywordType.UMLCurlyBracketOpen.getClass()}),

            //3 -> class, name, extends, name, implements, name, {
            new UMLPattern(new Class<?>[]{UMLStructureKeywordType.UMLClass.getClass(), UMLNameKeywordType.class, UMLRelationKeywordType.UMLExtends.getClass(),
                    UMLNameKeywordType.class, UMLRelationKeywordType.UMLImplements.getClass(), UMLNameKeywordType.class,  UMLBracketKeywordType.UMLCurlyBracketOpen.getClass()}),

            //4 -> value_type, name, ;
            new UMLPattern(new Class<?>[]{UMLValueTypeKeywordType.class, UMLNameKeywordType.class, UMLEndLineKeywordType.UMLSemiColon.getClass()}),

            //5 -> modifier, value_type, name, ;
            new UMLPattern(new Class<?>[]{UMLModifierKeywordType.class, UMLValueTypeKeywordType.class, UMLNameKeywordType.class, UMLEndLineKeywordType.UMLSemiColon.getClass()}),

            //6 -> value_type, name, (, ), ;
            new UMLPattern(new Class<?>[]{UMLValueTypeKeywordType.class, UMLNameKeywordType.class, UMLBracketKeywordType.UMLBracketOpen.getClass(),
                    UMLBracketKeywordType.UMLBracketClose.getClass(), UMLEndLineKeywordType.UMLSemiColon.getClass()}),

            //7 -> modifier, value_type, name, (, ), ;
            new UMLPattern(new Class<?>[]{UMLModifierKeywordType.class, UMLValueTypeKeywordType.class, UMLNameKeywordType.class, UMLBracketKeywordType.UMLBracketOpen.getClass(),
                    UMLBracketKeywordType.UMLBracketClose.getClass(), UMLEndLineKeywordType.UMLSemiColon.getClass()}),

            //8 -> interface, name, {
            new UMLPattern(new Class<?>[]{UMLStructureKeywordType.UMLInterface.getClass(), UMLNameKeywordType.class, UMLBracketKeywordType.UMLCurlyBracketOpen.getClass()}),

            //9 -> interface, name, extends, name, {
            new UMLPattern(new Class<?>[]{UMLStructureKeywordType.UMLInterface.getClass(), UMLNameKeywordType.class, UMLRelationKeywordType.UMLExtends.getClass(),
                    UMLNameKeywordType.class, UMLBracketKeywordType.UMLCurlyBracketOpen.getClass()}),
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
                return ConvertClass(keywords);
            }
            case 1:{
                return ConvertClassWithParent(keywords);
            }
            case 2:{
                return ConvertClassWithInterface(keywords);
            }
            case 3:{
                return ConvertClassWithParentAndInterface(keywords);
            }
            case 4:{
                return ConvertMember(keywords);
            }
            case 5:{
                return ConvertMemberWithModifier(keywords);
            }
            case 6:{
                return ConvertMethod(keywords);
            }
            case 7:{
                return ConvertMethodWithModifier(keywords);
            }
            case 8:{
                return ConvertInterface(keywords);
            }
            case 9:{
                return ConvertInterfaceWithParent(keywords);
            }
        }
        throw new UMLScriptSyntaxError("invalid syntax", keywords[keywords.length - 1].GetLineInUMLScript());
    }

    //class, name, {
    private static UMLObject ConvertClass(UMLKeyword[] keywords) {
        UMLClassObject object = new UMLClassObject(keywords[1].GetKeywordString());
        return object;
    }

    //class, name, extends, name, {
    private static UMLObject ConvertClassWithParent(UMLKeyword[] keywords) {
        UMLClassObject object = new UMLClassObject(keywords[1].GetKeywordString());
        object.setUmlParentName(keywords[3].GetKeywordString());
        return object;
    }

    //class, name, implements, name, {
    private static UMLObject ConvertClassWithInterface(UMLKeyword[] keywords) {
        UMLClassObject object = new UMLClassObject(keywords[1].GetKeywordString());
        object.setUmlInterfaceName(keywords[3].GetKeywordString());
        return object;
    }

    //class, name, extends, name, implements, name, {
    private static UMLObject ConvertClassWithParentAndInterface(UMLKeyword[] keywords) {
        UMLClassObject object = new UMLClassObject(keywords[1].GetKeywordString());
        object.setUmlParentName(keywords[3].GetKeywordString());
        object.setUmlInterfaceName((keywords[5].GetKeywordString()));
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

    //class, interface, {
    private static UMLObject ConvertInterface(UMLKeyword[] keywords) {
        UMLClassObject object = new UMLClassObject(keywords[1].GetKeywordString());
        object.setInterface(true);
        return object;
    }

    //interface, name, extends, name, {
    private static UMLObject ConvertInterfaceWithParent(UMLKeyword[] keywords) {
        UMLClassObject object = new UMLClassObject(keywords[1].GetKeywordString());
        object.setUmlParentName(keywords[3].GetKeywordString());
        object.setInterface(true);
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
