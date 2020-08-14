package com.TextUML.SyntaxRecognition;

import com.TextUML.UMLKeywords.*;
import com.TextUML.UMLObjects.UMLClassObject;
import com.TextUML.UMLObjects.UMLObject;
import com.TextUML.UMLObjects.UMLScriptObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class UMLPatternRecognizer {

    static UMLKeyword[] pattern_ending_keywords = {UMLBracketKeyword.UMLBracketOpen, UMLBracketKeyword.UMLCurlyBracketOpen, UMLEndLineKeyword.UMLSemiColon};

    //TODO: maybe change to not using class types
    public static UMLScriptObject UMLConvertKeywordsToObject(UMLKeyword[] keywords) throws Exception {

        List<UMLKeyword> current_pattern = new ArrayList<UMLKeyword>();
        UMLScriptObject uml_object = new UMLScriptObject();
        Stack<UMLObject> current_open_objects = new Stack();

        for(UMLKeyword keyword : keywords){
            current_pattern.add(keyword);
            //TODO: Add tags to keywords for checking
            if(keyword == UMLBracketKeyword.UMLBracketOpen || keyword == UMLBracketKeyword.UMLCurlyBracketOpen){
                int matching_pattern_id = UMLPatterns.GetMatchingPatternId(current_pattern.toArray(new UMLKeyword[]{}));
                System.out.println(matching_pattern_id);
                UMLObject object = UMLPatterns.ConvertKeywordsToUMLObjectUsingPattern(current_pattern.toArray(new UMLKeyword[]{}), matching_pattern_id);
                current_open_objects.push(object);
                System.out.println(object);

                current_pattern.clear();
            }
            else if(keyword == UMLEndLineKeyword.UMLSemiColon){
                int matching_pattern_id = UMLPatterns.GetMatchingPatternId(current_pattern.toArray(new UMLKeyword[]{}));
                UMLObject object = UMLPatterns.ConvertKeywordsToUMLObjectUsingPattern(current_pattern.toArray(new UMLKeyword[]{}), matching_pattern_id);
                if(!current_open_objects.empty()){
                    current_open_objects.peek().Add(object);
                }

                System.out.println(object);

                current_pattern.clear();

            }
        }

        return null;
    }
}
