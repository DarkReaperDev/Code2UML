package com.TextUML.SyntaxRecognition;

import com.TextUML.UMLKeywords.*;
import com.TextUML.UMLObjects.UMLClassObject;
import com.TextUML.UMLObjects.UMLMemberObject;
import com.TextUML.UMLObjects.UMLMethodObject;
import com.TextUML.UMLObjects.UMLObject;

public class UMLPatterns {

    static UMLPattern[] patterns = {
            //class, name, {
            new UMLPattern(new Class<?>[]{UMLStructureKeyword.UMLClass.getClass(), UMLNameKeyword.class, UMLBracketKeyword.UMLCurlyBracketOpen.getClass()}),
            //value_type, name, ;
            new UMLPattern(new Class<?>[]{UMLValueTypeKeyword.class, UMLNameKeyword.class, UMLEndLineKeyword.UMLSemiColon.getClass()}),
            //modifier, value_type, name, ;
            new UMLPattern(new Class<?>[]{UMLModifierKeyword.class, UMLValueTypeKeyword.class, UMLNameKeyword.class, UMLEndLineKeyword.UMLSemiColon.getClass()}),
            //value_type, name, (, ), ;
            new UMLPattern(new Class<?>[]{UMLValueTypeKeyword.class, UMLNameKeyword.class, UMLBracketKeyword.UMLBracketOpen.getClass(), UMLBracketKeyword.UMLBracketClose.getClass(), UMLEndLineKeyword.UMLSemiColon.getClass()}),
            //modifier, value_type, name, (, ), ;
            new UMLPattern(new Class<?>[]{UMLModifierKeyword.class, UMLValueTypeKeyword.class, UMLNameKeyword.class, UMLBracketKeyword.UMLBracketOpen.getClass(), UMLBracketKeyword.UMLBracketClose.getClass(), UMLEndLineKeyword.UMLSemiColon.getClass()})
    };

    public static int GetMatchingPatternId(UMLKeyword[] keywords){

        for(int i = 0; i < patterns.length; i++){
            if(patterns[i].Matches(keywords)){
                return i;
            }
        }
        return -1;
    }

    public static UMLObject ConvertKeywordsToUMLObjectUsingPattern(UMLKeyword[] keywords, int pattern_id){
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
        return null;
    }

    //class, name
    static UMLObject ConvertClassDeclaration(UMLKeyword[] keywords) {
        UMLObject object = new UMLClassObject(keywords[1].GetString());
        return object;
    }

    //value_type, name, ;
    static UMLObject ConvertMember(UMLKeyword[] keywords) {
        UMLObject object = new UMLMemberObject(keywords[1].GetString(), (UMLValueTypeKeyword)keywords[0]);
        return object;
    }

    //modifier, value_type, name, ;
    static UMLObject ConvertMemberWithModifier(UMLKeyword[] keywords) {
        UMLObject object = new UMLMemberObject(keywords[2].GetString(), (UMLModifierKeyword) keywords[0], (UMLValueTypeKeyword)keywords[1]);
        return object;
    }

    //value_type, name, (
    static UMLObject ConvertMethod(UMLKeyword[] keywords) {
        UMLObject object = new UMLMethodObject(keywords[1].GetString(), (UMLValueTypeKeyword)keywords[0]);
        return object;
    }

    //modifier, value_type, name, (
    static UMLObject ConvertMethodWithModifier(UMLKeyword[] keywords) {
        UMLObject object = new UMLMethodObject(keywords[2].GetString(), (UMLModifierKeyword) keywords[0], (UMLValueTypeKeyword)keywords[1]);
        return object;
    }

}

class UMLPattern {
    Class<?>[] pattern_keywords;

    public UMLPattern(Class<?>[] keywords) {
        this.pattern_keywords = keywords;
    }

    public boolean Matches(UMLKeyword[] keywords) {
        if (keywords.length != pattern_keywords.length) {
            return false;
        }
        for (int i = 0; i < keywords.length; i++) {
            if (!(keywords[i].getClass() == pattern_keywords[i])) {
                return false;
            }
        }
        return true;
    }
}
