package com.TextUML.UMLErrorHandling;

public class UMLScriptError extends Exception{
    private int errorLineInScript;
    public UMLScriptError(String errorMessage, int errorLine){
        super(errorMessage);
        this.errorLineInScript = errorLine;
    }
    public int GetErrorLine(){
        return errorLineInScript;
    }
}
