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
        Stack<UMLClassObject> current_open_classes = new Stack();

        for(UMLKeyword keyword : keywords){
            current_pattern.add(keyword);
            //TODO: Add tags to keywords for checking
            if(keyword == UMLBracketKeyword.UMLCurlyBracketOpen){
                int matching_pattern_id = UMLPatterns.GetMatchingPatternId(current_pattern.toArray(new UMLKeyword[]{}));
                System.out.println(matching_pattern_id);
                UMLObject object = UMLPatterns.ConvertKeywordsToUMLObjectUsingPattern(current_pattern.toArray(new UMLKeyword[]{}), matching_pattern_id);
                current_open_classes.push((UMLClassObject) object);
                System.out.println(object);

                current_pattern.clear();
            }
            else if(keyword == UMLEndLineKeyword.UMLSemiColon){
                int matching_pattern_id = UMLPatterns.GetMatchingPatternId(current_pattern.toArray(new UMLKeyword[]{}));
                System.out.println(matching_pattern_id);
                UMLObject object = UMLPatterns.ConvertKeywordsToUMLObjectUsingPattern(current_pattern.toArray(new UMLKeyword[]{}), matching_pattern_id);
                if(!current_open_classes.empty()){
                    current_open_classes.peek().Add(object);
                }

                System.out.println(object);

                current_pattern.clear();
            }

            else if(keyword == UMLBracketKeyword.UMLCurlyBracketClose){
                current_open_classes.pop();
                current_pattern.clear();
            }
        }

        return null;
    }
}
