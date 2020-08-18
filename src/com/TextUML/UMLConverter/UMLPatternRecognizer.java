package com.TextUML.UMLConverter;

import com.TextUML.UMLKeywords.*;
import com.TextUML.UMLObjects.UMLClassObject;
import com.TextUML.UMLObjects.UMLObject;
import com.TextUML.UMLObjects.UMLScriptObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class UMLPatternRecognizer {

    static UMLScriptObject ConvertUMLKeywordsToObject(UMLKeyword[] keywords){

        List<UMLKeyword> currentPattern = new ArrayList<UMLKeyword>();
        UMLScriptObject outputObject = new UMLScriptObject();
        Stack<UMLClassObject> currentOpenObjects = new Stack();

        for(UMLKeyword keyword : keywords){
            currentPattern.add(keyword);

            if(keyword.HasTag(UMLKeywordTags.OpenInnerPattern) || keyword.HasTag(UMLKeywordTags.EndPattern)){
                UMLObject object = GetObjectForPattern(currentPattern.toArray(new UMLKeyword[]{}));

                if(object.getClass() == UMLClassObject.class){
                    currentOpenObjects.push((UMLClassObject) object);
                }
                else{
                    currentOpenObjects.peek().Add(object);
                }
                currentPattern.clear();
            }

            else if(keyword == UMLBracketKeyword.UMLCurlyBracketClose){
                outputObject.AddUMLClass(currentOpenObjects.pop());
                currentPattern.clear();
            }
        }
        return outputObject;
    }

    private static UMLObject GetObjectForPattern(UMLKeyword[] currentPattern){
        int matchingPatternId = UMLPatterns.GetMatchingPatternsId(currentPattern);
        System.out.println(matchingPatternId);
        UMLObject object = UMLPatterns.ConvertKeywordsToUMLObjectUsingPattern(currentPattern, matchingPatternId);
        return object;
    }
}
