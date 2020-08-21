package com.TextUML.UMLKeywords;

import com.TextUML.UMLKeywords.KeywordTypes.UMLKeywordType;

public class UMLKeyword {
    private UMLKeywordType type;
    private int lineInScript = -1;

    public UMLKeyword(UMLKeywordType type, int lineInScript){
        this.type = type;
        this.lineInScript = lineInScript;
    }

    public UMLKeywordType GetType(){
        return type;
    }

    public int GetLineInUMLScript(){
        return lineInScript;
    }

    public String GetKeywordString(){
        return type.GetString();
    }
}
