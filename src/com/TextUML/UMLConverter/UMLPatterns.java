package com.TextUML.UMLConverter;

import com.TextUML.UMLErrorHandling.UMLScriptSyntaxError;
import com.TextUML.UMLKeywords.KeywordTypes.*;
import com.TextUML.UMLKeywords.UMLKeyword;
import com.TextUML.UMLObjects.UMLClassObject;
import com.TextUML.UMLObjects.UMLMemberObject;
import com.TextUML.UMLObjects.UMLMethodObject;
import com.TextUML.UMLObjects.UMLObject;

import java.util.function.Function;

class UMLPatterns {
    static UMLPattern[] patterns = {
            //structure, name, {
            new UMLPattern(new Class<?>[]{UMLStructureKeywordType.UMLClass.getClass(), UMLNameKeywordType.class, UMLBracketKeywordType.UMLCurlyBracketOpen.getClass()},
                    UMLPatterns::ConvertClass),

            //structure, name, extends, name, {
            new UMLPattern(new Class<?>[]{UMLStructureKeywordType.UMLClass.getClass(), UMLNameKeywordType.class, UMLRelationKeywordType.UMLExtends.getClass(),
                    UMLNameKeywordType.class, UMLBracketKeywordType.UMLCurlyBracketOpen.getClass()},
                    UMLPatterns::ConvertClassWithParent),

            //structure, name, implements, name, {
            new UMLPattern(new Class<?>[]{UMLStructureKeywordType.UMLClass.getClass(), UMLNameKeywordType.class, UMLRelationKeywordType.UMLImplements.getClass(),
                    UMLNameKeywordType.class, UMLBracketKeywordType.UMLCurlyBracketOpen.getClass()},
                    UMLPatterns::ConvertClassWithInterface),

            //structure, name, extends, name, implements, name, {
            new UMLPattern(new Class<?>[]{UMLStructureKeywordType.UMLClass.getClass(), UMLNameKeywordType.class, UMLRelationKeywordType.UMLExtends.getClass(),
                    UMLNameKeywordType.class, UMLRelationKeywordType.UMLImplements.getClass(), UMLNameKeywordType.class,  UMLBracketKeywordType.UMLCurlyBracketOpen.getClass()},
                    UMLPatterns::ConvertClassWithParentAndInterface),

            //value_type, name, ;
            new UMLPattern(new Class<?>[]{UMLValueTypeKeywordType.class, UMLNameKeywordType.class, UMLEndLineKeywordType.UMLSemiColon.getClass()},
                    UMLPatterns::ConvertMember),

            //modifier, value_type, name, ;
            new UMLPattern(new Class<?>[]{UMLModifierKeywordType.class, UMLValueTypeKeywordType.class, UMLNameKeywordType.class, UMLEndLineKeywordType.UMLSemiColon.getClass()},
                    UMLPatterns::ConvertMemberWithModifier),

            //value_type, name, (, ), ;
            new UMLPattern(new Class<?>[]{UMLValueTypeKeywordType.class, UMLNameKeywordType.class, UMLBracketKeywordType.UMLBracketOpen.getClass(),
                    UMLBracketKeywordType.UMLBracketClose.getClass(), UMLEndLineKeywordType.UMLSemiColon.getClass()},
                    UMLPatterns::ConvertMethod),

            //modifier, value_type, name, (, ), ;
            new UMLPattern(new Class<?>[]{UMLModifierKeywordType.class, UMLValueTypeKeywordType.class, UMLNameKeywordType.class, UMLBracketKeywordType.UMLBracketOpen.getClass(),
                    UMLBracketKeywordType.UMLBracketClose.getClass(), UMLEndLineKeywordType.UMLSemiColon.getClass()},
                    UMLPatterns::ConvertMethodWithModifier)
    };

    static UMLObject ConvertToUMLObject(UMLKeyword[] keywords) throws UMLScriptSyntaxError {
        for(int i = 0; i < patterns.length; i++){
            if(patterns[i].Matches(keywords)){
                return patterns[i].ConvertToUMLObject(keywords);
            }
        }
        throw new UMLScriptSyntaxError("invalid syntax", keywords[keywords.length - 1].GetLineInUMLScript());
    }


    //structure, name, {
    private static UMLObject ConvertClass(UMLKeyword[] keywords) {
        UMLClassObject object = new UMLClassObject(keywords[1].GetKeywordString());

        object.setInterface(keywords[0].GetType() == UMLStructureKeywordType.UMLInterface);
        return object;
    }

    //structure, name, extends, name, {
    private static UMLObject ConvertClassWithParent(UMLKeyword[] keywords) {
        UMLClassObject object = new UMLClassObject(keywords[1].GetKeywordString());

        object.setUmlParentName(keywords[3].GetKeywordString());
        object.setInterface(keywords[0].GetType() == UMLStructureKeywordType.UMLInterface);
        return object;
    }

    //structure, name, implements, name, {
    private static UMLObject ConvertClassWithInterface(UMLKeyword[] keywords) {
        UMLClassObject object = new UMLClassObject(keywords[1].GetKeywordString());

        object.setUmlInterfaceName(keywords[3].GetKeywordString());
        object.setInterface(keywords[0].GetType() == UMLStructureKeywordType.UMLInterface);
        return object;
    }

    //structure, name, extends, name, implements, name, {
    private static UMLObject ConvertClassWithParentAndInterface(UMLKeyword[] keywords) {
        UMLClassObject object = new UMLClassObject(keywords[1].GetKeywordString());

        object.setUmlParentName(keywords[3].GetKeywordString());
        object.setUmlInterfaceName((keywords[5].GetKeywordString()));
        object.setInterface(keywords[0].GetType() == UMLStructureKeywordType.UMLInterface);
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
        private Function<UMLKeyword[], UMLObject> conversionMethod;

        public UMLPattern(Class<?>[] keywords, Function<UMLKeyword[], UMLObject> conversionMethod) {
            this.patternKeywords = keywords;
            this.conversionMethod = conversionMethod;
        }

        public boolean Matches(UMLKeyword[] keywords) {
            if (keywords.length != patternKeywords.length) {
                return false;
            }
            for (int i = 0; i < keywords.length; i++) {
                System.out.println(keywords[i].GetType());
                if (!(keywords[i].GetType().getClass() == patternKeywords[i])) {
                    return false;
                }
            }
            return true;
        }

        public UMLObject ConvertToUMLObject(UMLKeyword[] keywords){
            return conversionMethod.apply(keywords);
        }
    }
}