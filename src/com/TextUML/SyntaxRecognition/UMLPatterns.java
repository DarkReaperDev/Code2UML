package com.TextUML.SyntaxRecognition;

import com.TextUML.UMLKeywords.*;
import com.TextUML.UMLObjects.UMLObject;

public class UMLPatterns {

    static UMLPattern[] patterns = {
            //class, name
            new UMLPattern(new Class<?>[]{UMLStructureKeyword.UMLClass.getClass(), UMLNameKeyword.class}),
            //value_type, name, ;
            new UMLPattern(new Class<?>[]{UMLValueTypeKeyword.class, UMLNameKeyword.class, UMLEndLineKeyword.UMLSemiColon.getClass()}),
            //modifier, value_type, name, ;
            new UMLPattern(new Class<?>[]{UMLModifierKeyword.class, UMLValueTypeKeyword.class, UMLNameKeyword.class, UMLEndLineKeyword.UMLSemiColon.getClass()}),
            //value_type, name, (
            new UMLPattern(new Class<?>[]{UMLValueTypeKeyword.class, UMLNameKeyword.class, UMLBracketKeyword.UMLBracketOpen.getClass()}),
            //modifier, value_type, name, (
            new UMLPattern(new Class<?>[]{UMLModifierKeyword.class, UMLValueTypeKeyword.class, UMLNameKeyword.class, UMLBracketKeyword.UMLBracketOpen.getClass()})
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
        //is class?
        if(keywords[0] == UMLStructureKeyword.UMLClass){

        }
        //is member or method?
        else if(keywords[0].getClass() == UMLValueTypeKeyword.class){
            //is method?
            if(keywords[-1] == UMLBracketKeyword.UMLBracketOpen){

            }
            //is member?
            else if (keywords[-1] == UMLEndLineKeyword.UMLSemiColon){

            }
        }
        return null;
    }


    UMLObject CreateUMLObjectFrom

}

class UMLPattern {
    Class<?>[] pattern_keywords;

    public UMLPattern(Class<?>[] keywords){
        this.pattern_keywords = keywords;
    }

    public boolean Matches(UMLKeyword[] keywords){
        if(keywords.length != pattern_keywords.length){
            return false;
        }
        for(int i = 0; i < keywords.length; i++){
            if(!(keywords[i].getClass() == pattern_keywords[i])){
                return false;
            }
        }
        return true;
    }
}
