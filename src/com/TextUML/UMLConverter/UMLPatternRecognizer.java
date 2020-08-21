package com.TextUML.UMLConverter;

import com.TextUML.UMLErrorHandling.UMLScriptError;
import com.TextUML.UMLErrorHandling.UMLScriptScopeError;
import com.TextUML.UMLErrorHandling.UMLScriptSyntaxError;
import com.TextUML.UMLKeywords.*;
import com.TextUML.UMLKeywords.KeywordTypes.UMLBracketKeywordType;
import com.TextUML.UMLKeywords.KeywordTypes.UMLKeywordType;
import com.TextUML.UMLObjects.UMLClassObject;
import com.TextUML.UMLObjects.UMLObject;
import com.TextUML.UMLObjects.UMLScriptObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class UMLPatternRecognizer {

    static UMLScriptObject ConvertUMLKeywordsToObject(UMLKeyword[] keywords) throws UMLScriptError {

        List<UMLKeyword> currentPattern = new ArrayList<UMLKeyword>();
        UMLScriptObject outputObject = new UMLScriptObject();
        Stack<UMLClassObject> currentOpenObjects = new Stack();

        for(UMLKeyword keyword : keywords){
            currentPattern.add(keyword);

            if(keyword.GetType().HasTag(UMLKeywordTags.OpenInnerPattern) || keyword.GetType().HasTag(UMLKeywordTags.EndPattern)){
                UMLObject object = GetObjectForPattern(currentPattern.toArray(new UMLKeyword[]{}));

                if(object.getClass() == UMLClassObject.class){
                    if(!currentOpenObjects.empty()){
                        throw new UMLScriptScopeError("declaring a class inside another class is not allowed");
                    }
                    currentOpenObjects.push((UMLClassObject) object);
                }
                else{
                    if(currentOpenObjects.empty()){
                        throw new UMLScriptScopeError("declaring a member or method outside of a class is not allowed");
                    }
                    currentOpenObjects.peek().Add(object);
                }
                currentPattern.clear();
            }

            else if(keyword.GetType() == UMLBracketKeywordType.UMLCurlyBracketClose){
                if(currentOpenObjects.empty() || currentPattern.size() > 1){
                    throw new UMLScriptSyntaxError("invalid Syntax");
                }
                outputObject.AddUMLClass(currentOpenObjects.pop());
                currentPattern.clear();
            }
        }
        if(!currentPattern.isEmpty()){
            throw new UMLScriptSyntaxError("invalid Syntax");
        }
        return outputObject;
    }

    private static UMLObject GetObjectForPattern(UMLKeyword[] currentPattern) throws UMLScriptSyntaxError {
        int matchingPatternId = UMLPatterns.GetMatchingPatternsId(currentPattern);
        System.out.println(matchingPatternId);
        UMLObject object = UMLPatterns.ConvertKeywordsToUMLObjectUsingPattern(currentPattern, matchingPatternId);
        return object;
    }
}
